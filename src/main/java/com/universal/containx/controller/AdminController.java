package com.universal.containx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universal.containx.dao.LoginRequest;
import com.universal.containx.dao.UserDto;
import com.universal.containx.repository.UserRepository;
import com.universal.containx.service.UserService;

@RestController
public class AdminController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/register")
	public String registerAdmin(@RequestBody UserDto dto) throws Exception{
		this.userService.signup(dto);
		return "Success !";
		
		
		
	}
	
	public void registerUser(){
		
	}
	
	@RequestMapping("/sign-in")
	public String loginUser(@RequestBody LoginRequest request ) {
		return userService.login(request);
		
		
	}

}
