package com.dev0kch.mybook.service;

import com.dev0kch.mybook.model.Mobinaute;
import com.dev0kch.mybook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Mobinaute user =  userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getNomUtilisateur(), user.getMotPasse(), new ArrayList<>());
    }



}
