package srdt.co.in.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	    @Autowired
	    private UserDetailsService userDetailsService;

	    @Bean
	    protected AuthenticationManager getAuthenticationManager() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Bean
	    PasswordEncoder passwordEncoder() {
	        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	    }
	    
	    @Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring()
			.antMatchers("/api/userlogin/getlogins","/api/userlogin/validateuser","/route/test","/api-docs**")
			.antMatchers(
			        "/swagger-resources/**",
			        "/api-docs-ui.html**",
			        "/webjars/**",
			        "/swagger-ui/**")
			.antMatchers("/api/userlogin/synclogins");
		}
	    
	    @Override 
	    protected void configure(HttpSecurity http) throws Exception {	        
	         
	         http
	         .requestMatchers()
	         .antMatchers("/api/userlogin/getlogins")
	         .antMatchers(HttpMethod.POST, "/api/userlogin/validateuser")
	         .antMatchers("/route/test")
	         .antMatchers("/unif/**")
	         .antMatchers(HttpMethod.POST,"/api/userlogin/synclogins")
	         .and()
	         .authorizeRequests()
	         .antMatchers("/api/userlogin/getlogins")
	         .permitAll()
	         .antMatchers(HttpMethod.POST, "/api/userlogin/validateuser")
	         .permitAll()
	         .antMatchers("/route/test")
	         .permitAll()
	         .antMatchers("/unif/**")
	         .permitAll()
	         .antMatchers(HttpMethod.POST,"/api/userlogin/synclogins")
	         .permitAll()
	         ;
	     } 

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }    
	   
}

