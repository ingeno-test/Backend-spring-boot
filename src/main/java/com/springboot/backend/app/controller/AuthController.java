package com.springboot.backend.app.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.backend.app.model.User;
import com.springboot.backend.app.repository.UserRepository;


@RestController
@RequestMapping(path = "/api")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;

	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/auth/login")
	HashMap<String, String> auth(@RequestBody User user) {
		HashMap<String, String> token =new HashMap<String, String>();
		try {
			User temp = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
			token.put("username", temp.getUsername());
			token.put("token", temp.getToken());
		} catch (NullPointerException e) {
			token.put("token", null);
		}
		
		return token;
	}

}
