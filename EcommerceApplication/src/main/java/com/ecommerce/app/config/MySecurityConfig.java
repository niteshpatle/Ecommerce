package com.ecommerce.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecommerce.app.services.CustomUserDetailsService;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired 
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired JwtAuthenticationFilter authFilter;

	//define which url you want to permit and access API
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
		 //A CSRF token is a secure random token that is used to prevent CSRF attacks.
		 //The token needs to be unique per user session and should be of large
		 //random value to make it difficult to guess.
		 //A CSRF secure application assigns a unique CSRF token for every user session.
		 .csrf()//Cross-site request forgery
		 .disable()
		 .cors()
		 .disable()
		 .authorizeRequests()
		 .antMatchers("/token").permitAll()
		 .anyRequest().authenticated()
		 .and()
		 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
		 http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	//which authentication use with the help of AuthenticationManagerBuilder
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
