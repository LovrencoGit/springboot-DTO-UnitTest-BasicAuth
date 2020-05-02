package com.certimetergroup.formazione.security.configuration;

import com.certimetergroup.formazione.security.entrypoint.MyBasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Value("${security.authentication.basic.api.users.realm}")
    String AUTH_BASIC_API_USERS_REALM;
    @Value("${security.authentication.basic.api.users.username}")
    String AUTH_BASIC_API_USERS_USERNAME;
    @Value("${security.authentication.basic.api.users.password}")
    String AUTH_BASIC_API_USERS_PASSWORD;
    @Value("${security.authentication.basic.api.users.role}")
    String AUTH_BASIC_API_USERS_ROLE;



    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(AUTH_BASIC_API_USERS_USERNAME)
                .password(passwordEncoder().encode(AUTH_BASIC_API_USERS_PASSWORD))
                .roles(AUTH_BASIC_API_USERS_ROLE);

        //auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
        //auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                
                .antMatchers("/users/**").hasRole(AUTH_BASIC_API_USERS_ROLE)
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().realmName(AUTH_BASIC_API_USERS_REALM).authenticationEntryPoint(authenticationEntryPoint)

            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.

        //http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
    }


    //*** To allow Pre-flight [OPTIONS] request from browser ***/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }


    /*********************************************************************************************************/
    /*********************************************************************************************************/
    /*********************************************************************************************************/


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
