package pawnbolo.com.pawnbolo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pawnbolo.com.pawnbolo.models.AuthRequest;
import pawnbolo.com.pawnbolo.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody AuthRequest request) {
        try {
            authService.signUp(request.getEmail(), request.getPassword(), request.getName(), request.getPawnStoreId());
            return ResponseEntity.status(201).body("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Signup failed: " + e.getMessage());
        }
    }
}

