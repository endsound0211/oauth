import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan({"com.endsound", "dao"})
public class TestConfiguration {
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer(){
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setLocations(
                new ClassPathResource("database.properties")
        );

        propertyPlaceholderConfigurer.setFileEncoding("UTF-8");
        propertyPlaceholderConfigurer.setIgnoreResourceNotFound(true);
        return propertyPlaceholderConfigurer;
    }
}
