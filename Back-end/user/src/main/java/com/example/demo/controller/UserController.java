package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping()
	public ResponseEntity<User> getDetails(@RequestParam(value = "user_id", required = true) int user_id){
		User user = userService.getDetails(user_id);
		if(user == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<Boolean> updateAmount(@RequestBody User user){
		boolean result = userService.updateAmount(user);
		if(!result) return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		else return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
