package com.freudas.dev.service;

import com.freudas.dev.model.User;
import com.freudas.dev.viewmodel.*;

import java.util.List;

public interface IUserService {
    User getUserById(long id);
    UserDetailsViewModel getUserDetails(long id);
    AuthResponse loginUser(LoginViewModel loginViewModel);
    ApiResponse registerUser(SignUpViewModel signUpViewModel);
    ApiResponse resetPassword(PasswordResetViewModel viewModel);
    ApiResponse forgotPassword(String email);
    ApiResponse changeUserImage(long userId, UpdateUserViewModel viewModel);
    ApiResponse verifyEmail(String email, String resetKey);
    List<User> getAllWorkUsers();
    List<User> getAllUsers();
}
