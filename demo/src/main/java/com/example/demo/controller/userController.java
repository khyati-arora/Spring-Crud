package com.example.demo.controller;

import com.example.demo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.userService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@CrossOrigin(origins = "*")
public class userController {
    
    @Autowired private userService userSer;

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

    @PutMapping("/update/{id}")
    public Users updateUser(@RequestBody Users user ,@PathVariable Long id){
      return userSer.updateUserLogic(user,id);
    }    
   
    @DeleteMapping("/{id}")
    public String deleteDB(@PathVariable Long id){
     userSer.deleteLogic(id);
     return "Deleted Successfully";
    }

  
    @PatchMapping("/update/{id}")
    public String patchUpdate(@PathVariable Long id,@RequestBody String fName){
        userSer.changeFirstName(id,fName);
        return "Updated Successfully";
    }
    
}
