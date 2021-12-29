package com.example.bnmdonation.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.bnmdonation.model.User;
import com.example.bnmdonation.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
//	public void save(User user);
}
