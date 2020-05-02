package com.certimetergroup.formazione.security.entrypoint;

import com.certimetergroup.formazione.enumeration.ResponseErrorEnum;
import com.certimetergroup.formazione.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Value("${security.authentication.basic.api.realm}")
    String AUTH_BASIC_API_REALM;



    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) throws IOException {
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() );

        response.setStatus( HttpStatus.UNAUTHORIZED.value() );
        response.setContentType( MediaType.APPLICATION_JSON_VALUE );
        response.getOutputStream().println( new ObjectMapper().writeValueAsString(
                new Response(ResponseErrorEnum.ERR_4)
        ));
    }


    @Override
    public void afterPropertiesSet() {
        setRealmName(AUTH_BASIC_API_REALM);
        super.afterPropertiesSet();
    }
}

