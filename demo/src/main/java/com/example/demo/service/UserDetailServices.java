package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.Users;
import com.example.demo.repository.userRepository;
import java.util.Optional;


@Service
public class UserDetailServices implements  UserDetailsService{

   @Autowired userRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<Users> user=userRepository.findByUsername(username);
        if(user.isPresent()){
            var usrObj=user.get();
            return org.springframework.security.core.userdetails.User.builder()
            .username(usrObj.getUsername())
            .password(usrObj.getPassword())
            .roles(getRoles(usrObj.getRole()))
            .build();
        }
        else{
            throw new UsernameNotFoundException(username);
        }
    }     
    
    public String[] getRoles(String roles){
        if(roles==null) return new String[]{"USER"};

        return roles.split(",");
    }
}