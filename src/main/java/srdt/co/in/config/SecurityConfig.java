/*
package srdt.co.in.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration 
@Order(value=101)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

     @Override 
     protected void configure(HttpSecurity http) throws Exception {
    	 
         http.authorizeRequests()
         .antMatchers("/createlogin*")
         .permitAll();
     } 
}

*/