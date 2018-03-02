package com.endsound.error;

import com.endsound.error.bean.ForbiddenErrorBean;
import com.endsound.error.bean.InternalServerErrorBean;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GeneralErrorControllerAdvice {
    private final static Logger logger = LogManager.getLogger(GeneralErrorControllerAdvice.class);

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ForbiddenErrorBean accessDeniedHandler(AccessDeniedException e){
        return new ForbiddenErrorBean()
                .setMessage(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public InternalServerErrorBean generalHandler(Exception e, HttpServletRequest request){
        UUID uuid = UUID.randomUUID();
        Timestamp current = Timestamp.from(Instant.now());
        logRequestException(request, e, uuid, current);

        return new InternalServerErrorBean()
                .setCode(uuid)
                .setTime(current)
                .setMessage(e.getMessage());
    }

    protected void logRequestException(HttpServletRequest request, Throwable throwable, UUID uuid, Timestamp current){
        String msg = String.format("\n::%s Exception Occurred::\n%s\n%s",
                throwable.getClass().getName(),
                genRequestLog(request),
                genExceptionLog(throwable, uuid, current));
        logger.error(msg, throwable);
    }

    protected String genRequestLog(HttpServletRequest request){
        Gson gson = new Gson();

        String msg = String.format("\n%s: %s\nHeaders: %s\nParams: %s",
                request.getMethod(),
                request.getRequestURL(),
                gson.toJson(
                        Collections.list(request.getHeaderNames()).parallelStream()
                                .collect(Collectors.toMap(header -> header, request::getHeader))),
                gson.toJson(request.getParameterMap()));

        return msg;
    }

    protected String genExceptionLog(Throwable throwable, UUID code, Timestamp time){
        String msg = String.format("\nError Code: %s\nError Time: %s\nError Message:",
                code,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(time),
                throwable.getMessage());

        return msg;
    }
}
