package org.example.urbanpassplatform.controller;

import lombok.Data;
import org.example.urbanpassplatform.entity.User;
import org.example.urbanpassplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController() {
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/insert")
    public User insertUser(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @GetMapping("/findAll")
    public List<User> getUser() {
        return userRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
    }
}
