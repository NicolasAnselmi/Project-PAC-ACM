package com.models.user;

import org.springframework.stereotype.Service;

@Service
public class ServiceAutenticazione {
	
	public boolean logIn(String username, String password) {
		return true;
	}
	
	public boolean logOut(String email) {
		return true;
	}
	
	public boolean createUser(String email, String username, String password) {
		return true;
	}
	
	public boolean deleteUser(String email) {
		return true;
	}
	
	public String getAllUsers() {
		return null;
	}
	
}
