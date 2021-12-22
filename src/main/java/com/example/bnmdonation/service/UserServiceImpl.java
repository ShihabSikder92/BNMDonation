package com.example.bnmdonation.service;

import com.example.bnmdonation.model.User;
import com.example.bnmdonation.repository.UserRepository;
import com.example.bnmdonation.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(), registrationDto.getEmail(),
                registrationDto.getPassword());

        return userRepository.save(user);
    }
}
