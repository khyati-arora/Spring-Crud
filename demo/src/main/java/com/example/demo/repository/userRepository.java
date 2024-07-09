package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.example.demo.Users;

@Repository
public interface userRepository extends CrudRepository< Users, Long>{
    Optional<Users> findByUsername(String username);
}
