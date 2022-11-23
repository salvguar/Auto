package com.comdata.autoapi.service;

import org.springframework.http.ResponseEntity;

import com.comdata.autoapi.utility.UserLogin;
import com.comdata.autoservice.model.User;

public interface UserService {
	
	public ResponseEntity<Object> login(UserLogin userLogin);
	
	public boolean createUser(User user);
}
