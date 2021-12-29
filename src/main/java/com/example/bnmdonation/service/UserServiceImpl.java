package com.example.bnmdonation.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.bnmdonation.model.BloodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bnmdonation.model.Role;
import com.example.bnmdonation.model.User;
import com.example.bnmdonation.repository.UserRepository;
import com.example.bnmdonation.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		User newUser = new User();
		newUser.setEmail(registrationDto.getEmail());
		newUser.setFirstName(registrationDto.getFirstName());
		newUser.setLastName(registrationDto.getLastName());
		newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		newUser.setRoles(Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(newUser);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = userRepository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	public User getUserbyID(Long id){
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()){
			return user.get();
		}
		return null;
	}
	public User getUserbyEmail(String email){
		User user = userRepository.findByEmail(email);
		return user;
	}

}
