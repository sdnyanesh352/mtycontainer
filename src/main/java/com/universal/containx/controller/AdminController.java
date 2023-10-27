package com.universal.containx.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/sign-in")
	public ResponseEntity<Map<String, Object>> loginUser(@RequestBody LoginRequest request) {
	    System.out.println(request.toString() + " time: " + LocalDateTime.now());
	    if(request.getLoginType().equals("googleAuth")) {
	    	System.out.println(" logged in with gauth");
	    	
	    }
	 
	    // You can include additional data in the response if needed
	    Map<String, Object> response = new HashMap();
	    response.put("success", true);
	    response.put("message", "Success!");
	    // Add any other data you want to include in the response
	    userService.login(request);
	 
	    return ResponseEntity.ok(response);
	}

}
