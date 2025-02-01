package com.example.backend.Controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService service;



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {

            String response = service.verify(user);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {

            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }


}

