package com.freudas.dev.service;

import com.freudas.dev.exception.BadRequestException;
import com.freudas.dev.exception.ResourceNotFoundException;
import com.freudas.dev.model.AuthProvider;
import com.freudas.dev.model.User;
import com.freudas.dev.repository.UserRepository;
import com.freudas.dev.security.TokenProvider;
import com.freudas.dev.util.Constants;
import com.freudas.dev.util.HelperFunctions;
import com.freudas.dev.util.MailUtils;
import com.freudas.dev.viewmodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements IUserService{
    final private UserRepository userRepository;
    final private AuthenticationManager authenticationManager;
    final private PasswordEncoder passwordEncoder;
    final private TokenProvider tokenProvider;
    final private IBigFiveService bigFiveService;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder, TokenProvider tokenProvider,
                       IBigFiveService bigFiveService, MailUtils mailUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.bigFiveService = bigFiveService;
    }

    @Override
    public User getUserById(long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "ID", id));
    }

    @Override
    public UserDetailsViewModel getUserDetails(long id) {
        User user = this.getUserById(id);
        UserDetailsViewModel viewModel = new UserDetailsViewModel();
        BeanUtils.copyProperties(user, viewModel);
        return viewModel;
    }

    @Override
    public AuthResponse loginUser(LoginViewModel loginViewModel) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginViewModel.getEmail(),
                        loginViewModel.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return new AuthResponse(token);
    }

    @Override
    public ApiResponse registerUser(SignUpViewModel signUpViewModel) {
        if(userRepository.existsByEmail(signUpViewModel.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpViewModel.getName());
        user.setEmail(signUpViewModel.getEmail());
        user.setProvider(AuthProvider.local);
        if (signUpViewModel.getRole() != null && signUpViewModel.getRole().equalsIgnoreCase("admin")){
            user.setRole(Constants.ADMIN_USER_ROLE);
        } else {
            user.setRole(Constants.LIFE_USER_ROLE);
        }
        user.setPassword(passwordEncoder.encode(signUpViewModel.getPassword()));
        user.setAdminBlocked(false);
        user.setLoginBlocked(true);
        user.setEmailVerified(false);
        String resetKey = HelperFunctions.generateRandomString(8);
        user.setResetKey(resetKey);
        User result = userRepository.save(user);
        this.bigFiveService.addQuestionsToUser(result);
        return new ApiResponse(true, resetKey);
    }

    @Override
    public ApiResponse resetPassword(PasswordResetViewModel viewModel) {
        User user = this.userRepository.findByEmail(viewModel.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User", "Email", viewModel.getEmail()));
        if(user.getResetKey().equals(viewModel.getResetKey())){
            user.setResetKey(null);
            user.setPassword(passwordEncoder.encode(viewModel.getPassword()));
            user.setLoginBlocked(false);
            this.userRepository.saveAndFlush(user);
            return new ApiResponse(true, "Password reset successful");
        } else {
            return new ApiResponse(false, "Bad request");
        }
    }

    @Override
    public ApiResponse forgotPassword(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", email));
        String resetKey = HelperFunctions.generateRandomString(8);
        user.setResetKey(resetKey);
        user.setLoginBlocked(true);
        this.userRepository.saveAndFlush(user);
        return new ApiResponse(true, resetKey);
    }

    @Override
    public ApiResponse changeUserImage(long userId, UpdateUserViewModel viewModel) {
        User user = this.getUserById(userId);
        user.setImageUrl(viewModel.getImageUrl());
        this.userRepository.saveAndFlush(user);
        return new ApiResponse(true, "Image added successfully!");
    }

    @Override
    public ApiResponse verifyEmail(String email, String resetKey) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email", email));
        if (user.getResetKey().equals(resetKey)) {
            user.setEmailVerified(true);
            user.setResetKey(null);
            this.userRepository.saveAndFlush(user);
            return new ApiResponse(true, "Email verified successfully!");
        } else {
            return new ApiResponse(false, "Email verification failed!");
        }
    }

    @Override
    public List<User> getAllWorkUsers() {
        return this.userRepository.findAllWorkUsers();
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll().stream().filter(user -> !(user.isAdminBlocked() && user.isLoginBlocked())).collect(Collectors.toList());
    }
}
