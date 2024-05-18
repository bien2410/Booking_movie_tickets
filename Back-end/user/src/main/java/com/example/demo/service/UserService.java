package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDAO;
import com.example.demo.model.User;

@Service
public class UserService {
	@Autowired
	private UserDAO userDAO;
	
	public User getDetails(int user_id) {
		User user = userDAO.getDetails(user_id);
		return user;
	}
	public boolean updateAmount(User user) {
		int result = userDAO.updateAmount(user);
		if(result == 0) return false;
		return true;
	}
}
