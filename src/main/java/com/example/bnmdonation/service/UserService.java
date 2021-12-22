package com.example.bnmdonation.service;

import com.example.bnmdonation.model.User;
import com.example.bnmdonation.web.dto.UserRegistrationDto;

public interface UserService{
    User save(UserRegistrationDto registrationDto);
}
