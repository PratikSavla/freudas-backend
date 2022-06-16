package com.freudas.dev.controller;

import com.freudas.dev.service.IUserService;
import com.freudas.dev.viewmodel.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    final private IUserService userService;

    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginViewModel loginViewModel) {
        return ResponseEntity.ok(this.userService.loginUser(loginViewModel));
    }
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpViewModel signUpViewModel) {
        return ResponseEntity.ok(this.userService.registerUser(signUpViewModel));
    }
    @PostMapping("/forgot-password/{email}")
    public ResponseEntity<ApiResponse> forgotPassword(@PathVariable String email) {
        return ResponseEntity.ok(this.userService.forgotPassword(email));
    }
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody PasswordResetViewModel viewModel) {
        return ResponseEntity.ok(this.userService.resetPassword(viewModel));
    }
    @GetMapping("/verify-email")
    public ResponseEntity<ApiResponse> verifyEmail(@RequestParam String email, @RequestParam String resetKey) {
        return ResponseEntity.ok(this.userService.verifyEmail(email, resetKey));
    }
}
