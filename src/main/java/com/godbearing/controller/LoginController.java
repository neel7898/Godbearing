package com.godbearing.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.godbearing.dto.UserDto;

@RestController
public class LoginController {
	
	@PostMapping("/login")
	public UserDto loginUser(@RequestBody UserDto userDto) {
		if(userDto.getName().equals("admin") && userDto.getPassword().equals("1234")) {
			userDto.setLoginSuccess(true);
		}else {
			userDto.setLoginSuccess(false);
		}
		userDto.setPassword("");
		return userDto;
	}
	
	
}
