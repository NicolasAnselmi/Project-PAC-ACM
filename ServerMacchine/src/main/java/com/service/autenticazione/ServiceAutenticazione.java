package com.autenticazione;

import com.user.Dipendente;

import java.util.Collection;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.LoginAuthenticator;

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
	
	public Collection<Dipendente> getAllUsers() {
		return null;
	}
	
}
