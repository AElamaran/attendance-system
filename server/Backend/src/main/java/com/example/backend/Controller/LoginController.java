package com.example.backend.Controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
@RestController
public class LoginController {

    @Autowired
    private UserService service;



//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody User user) {
//        try {
//
//            String response = service.verify(user);
//            return ResponseEntity.ok(response);
//        } catch (BadCredentialsException e) {
//
//            return ResponseEntity.status(401).body("Invalid username or password");
//        }
//    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:5500")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        try {
            String token = service.verify(user);
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid Credentials"));
        }
    }



}

