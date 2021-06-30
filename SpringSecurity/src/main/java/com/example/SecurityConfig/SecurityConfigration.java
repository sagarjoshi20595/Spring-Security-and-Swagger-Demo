package com.example.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.Service.UserDetailServices;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigration extends WebSecurityConfigurerAdapter{
 	@Autowired
	private UserDetailServices UserDetailsservice;
	
 	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(UserDetailsservice).passwordEncoder(encryptpwd());

	}
 	
 	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
	    return new MyAuthenticationSuccessHandler();
	}
	
	@Bean
	public BCryptPasswordEncoder encryptpwd() {
		return new BCryptPasswordEncoder();
	}
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	         .antMatchers("/loginpage","/registerpage","/register","/forgotpassword","/changepassword","/webjars/**","/**/*.js", "/**/*.css","/**/*.jpg","/**/*.png").permitAll()
	          .antMatchers("/swagger-ui.html").access("hasRole('user')")
	    	  .anyRequest().authenticated().and()
	          .formLogin().loginPage("/loginpage").successHandler(myAuthenticationSuccessHandler()).permitAll().and()
	          .headers().frameOptions().disable().and()
	          .csrf().disable();
	    }
}
