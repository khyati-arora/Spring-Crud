package com.example.demo.controller;

import com.example.demo.Users;
import com.example.demo.repository.userRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import com.example.demo.service.userService;
import com.example.demo.utils.JwtService;

import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "*")
public class userController {
    
   @Autowired 
    private AuthenticationManager authenticationManager;

    @Autowired private userService userSer;
    @Autowired
    JwtService jwtService;

    final private userRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    public userController(userRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/")
    public Long getAllCount() {
        return userSer.getAllUserCount();
    }

    @GetMapping("/users")
    public List<Users> getAll() {
        return userSer.getAllUser();
    }

    @GetMapping("/users/{id}")
    public Users getUserWithID(@PathVariable Long id) {
        return userSer.getID(id);
    }

    @PostMapping("/add")
    public String addUser(@RequestBody Users user ) {
        userSer.addNewUser(user);
        return "User Added Successfully";
    }

    
   
    @DeleteMapping("/{id}")
    public String deleteDB(@PathVariable Long id){
     userSer.deleteLogic(id);
     return "Deleted Successfully";
    }



    @PostMapping(path="/register/user")
    public ResponseEntity<Users> createNewUser(@RequestBody Users user, HttpServletResponse response){
        System.out.println("post request made");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users u=userRepository.save(user);
        response.setHeader("authToken", jwtService.generateToken(user.getUsername()));
        return new ResponseEntity<>(u, HttpStatus.CREATED);
    }

    @PostMapping(path = "/authenticate")
    public String auhenticateAndGetToken(@RequestBody Users user){
    
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()){
            return "Login successful";
        }
        else throw new UsernameNotFoundException("Wrong credentials!");
    }

    
    
}
