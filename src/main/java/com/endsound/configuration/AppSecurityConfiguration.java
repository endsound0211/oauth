package com.endsound.configuration;

import com.endsound.security.entryPoint.JwtAuthenticationEntryPoint;
import com.endsound.security.filter.JwtAuthenticationFilter;
import com.endsound.security.filter.JwtAuthenticationTokenFilter;
import com.endsound.security.filter.JwtCommonTokenFilter;
import com.endsound.security.filter.JwtExceptionFilter;
import com.endsound.security.handler.JwtAccessDeniedHandler;
import com.endsound.security.provider.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfiguration implements WebMvcConfigurer{
    @Value("#{'${security.cors}'.split(',')}")
    private List<String> cors;

    @Configuration
    @Order(2)
    public static class BackendApiSecurityConfiguration extends WebSecurityConfigurerAdapter{
        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
        @Autowired
        private UrlBasedCorsConfigurationSource corsSource;
        @Autowired
        private JwtAccessDeniedHandler jwtAccessDeniedHandler;
        @Autowired
        private JwtExceptionFilter jwtExceptionFilter;
        @Autowired
        private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.mvcMatcher("/backend/api/**")
                    .httpBasic().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .cors().configurationSource(corsSource).and()
                    .csrf().disable()
                    .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler).and()
                    .addFilterBefore(jwtExceptionFilter, AbstractPreAuthenticatedProcessingFilter.class)
                    .addFilterAfter(jwtAuthenticationTokenFilter, AbstractPreAuthenticatedProcessingFilter.class);
        }
    }

    @Configuration
    @Order(1)
    public static class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
        @Autowired
        private UrlBasedCorsConfigurationSource corsSource;
        @Autowired
        private JwtAccessDeniedHandler jwtAccessDeniedHandler;
        @Autowired
        private JwtExceptionFilter jwtExceptionFilter;
        @Autowired
        private JwtCommonTokenFilter jwtCommonTokenFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.mvcMatcher("/api/**")
                    .httpBasic().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .cors().configurationSource(corsSource).and()
                    .csrf().disable()
                    .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler).and()
                    .addFilterBefore(jwtExceptionFilter, AbstractPreAuthenticatedProcessingFilter.class)
                    .addFilterAfter(jwtCommonTokenFilter, AbstractPreAuthenticatedProcessingFilter.class);
        }
    }

    @Configuration
    public static class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter{
        @Autowired
        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
        @Autowired
        private UrlBasedCorsConfigurationSource corsSource;
        @Autowired
        private JwtAuthenticationFilter jwtAuthenticationFilter;
        @Autowired
        private JwtExceptionFilter jwtExceptionFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.mvcMatcher("/jwt/**")
                    .authorizeRequests().anyRequest().permitAll().and()
                    .httpBasic().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .cors().configurationSource(corsSource).and()
                    .csrf().disable()
                    .addFilterBefore(jwtExceptionFilter, AbstractPreAuthenticatedProcessingFilter.class)
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        }

    }

    @Bean
    public AuthenticationManager authenticationManager(
            @Qualifier("jwtAuthenticationProvider")JwtAuthenticationProvider jwtAuthenticationProvider
    ){
        return new ProviderManager(Arrays.asList(jwtAuthenticationProvider));
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            @Qualifier("authenticationManager")AuthenticationManager authenticationManager,
            @Qualifier("jwtAuthenticationSuccessHandler")AuthenticationSuccessHandler successHandler,
            @Qualifier("jwtAuthenticationFailureHandler")AuthenticationFailureHandler failureHandler
    ){
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        jwtAuthenticationFilter.setAuthenticationFailureHandler(failureHandler);
        return jwtAuthenticationFilter;
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsSource(){
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration());
        return corsConfigurationSource;
    }

    @Bean
    public CorsConfiguration corsConfiguration(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedOrigins(cors);
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        return corsConfiguration;
    }
}
