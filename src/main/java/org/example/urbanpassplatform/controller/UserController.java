package org.example.urbanpassplatform.controller;

import org.example.urbanpassplatform.controller.resource.SignInResource;
import org.example.urbanpassplatform.entity.User;
import org.example.urbanpassplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    public String getSHA256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public User signUp(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(getSHA256(user.getPassword()));
        user.setTickets(new ArrayList<>());
        user.setRoles(new ArrayList<>());
        return userRepository.save(user);
    }
    @PostMapping("/signin")
    public User signIn(@RequestBody SignInResource user) {

        User existingUser = userRepository.findByUsername(user.username());
        if (existingUser == null) {
            throw new RuntimeException("User not found");
        }
        if (!getSHA256(user.password()).equals(existingUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return existingUser;
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
