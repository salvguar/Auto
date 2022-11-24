package com.comdata.autoapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.comdata.autoapi.utility.AuthResponse;
import com.comdata.autoapi.utility.JwtTokenUtil;
import com.comdata.autoapi.utility.UserLogin;
import com.comdata.autoservice.model.User;
import com.comdata.autoservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private AuthenticationManager authManager;
	private JwtTokenUtil jwtToken;
	private UserRepository repository;
	
	public UserServiceImpl(AuthenticationManager authManager, JwtTokenUtil jwtTokenUtil, UserRepository repository) {
		this.authManager = authManager;
		this.jwtToken = jwtTokenUtil;
		this.repository = repository;
	}
	
	@Override
	public ResponseEntity<Object> login(UserLogin userLogin) {
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword())
					);
			
			User user = (User) authentication.getPrincipal();
			String accessToken = jwtToken.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
			return ResponseEntity.ok(response);
			
		}catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@Override
	public boolean createUser(User user) {
		try {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			
			User newUser = new User(user.getEmail(), encodedPassword);
			repository.save(newUser);
			return true;
		}catch (Exception ex) {
			 return false;
		}
	}

}
