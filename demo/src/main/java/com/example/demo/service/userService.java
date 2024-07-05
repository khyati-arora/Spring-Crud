package com.example.demo.service;

import com.example.demo.repository.userRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Users;

@Service
public class userService {
   @Autowired private userRepository userRep;

   public List < Users > getAllUser(){
    return (List<Users>) userRep.findAll();
   }        


   public Users getID(Long id){
      return userRep.findById(id).get();
   }


   public void addNewUser(Users user){
     userRep.save(user);
     System.out.println("success");
   }
 
   public Long getAllUserCount(){
      return userRep.count();
   }

   public Users updateUserLogic(Users newUser, Long id){
      Users userDB = getID(id);
      userDB.setFirstName(newUser.getFirstName());
      userDB.setLastName(newUser.getLastName());
      userDB.setEmail(newUser.getEmail());
      return userRep.save(userDB);
   }

   public void deleteLogic(Long id){
     userRep.deleteById(id);
   }

   public void changeFirstName(Long id,String fName){
      Users userDB = getID(id);
      userDB.setFirstName(fName);
      userRep.save(userDB);
   }

}
