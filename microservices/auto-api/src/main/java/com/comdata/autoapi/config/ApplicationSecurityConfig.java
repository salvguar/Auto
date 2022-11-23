package com.comdata.autoapi.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.comdata.autoapi.utility.JwtTokenFilter;
import com.comdata.autoservice.repository.UserRepository;

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private UserRepository repository;
	private JwtTokenFilter filter;
	
	public ApplicationSecurityConfig(UserRepository userRepository, JwtTokenFilter jwtTokenFilter) {
		this.repository = userRepository;
		this.filter = jwtTokenFilter;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> repository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User" + username + " not found."))
				);
	}
	
	@Override	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.exceptionHandling().authenticationEntryPoint(
				(request, response, ex) -> {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
							ex.getMessage());
				}
		);
		http.authorizeRequests().antMatchers("/auth/login/register").permitAll();
		http.authorizeRequests().antMatchers("/auth/login").permitAll()
		.anyRequest().authenticated();
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
