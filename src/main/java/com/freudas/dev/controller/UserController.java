package com.freudas.dev.controller;

import com.freudas.dev.exception.ResourceNotFoundException;
import com.freudas.dev.model.User;
import com.freudas.dev.security.CurrentUser;
import com.freudas.dev.security.UserPrincipal;
import com.freudas.dev.service.IUserService;
import com.freudas.dev.viewmodel.ApiResponse;
import com.freudas.dev.viewmodel.UpdateUserViewModel;
import com.freudas.dev.viewmodel.UserDetailsViewModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    final private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public UserDetailsViewModel getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return this.userService.getUserDetails(userPrincipal.getId());
    }
    @PutMapping("/profile")
    public ApiResponse updateProfileImage(@CurrentUser UserPrincipal userPrincipal, @RequestBody UpdateUserViewModel viewModel) {
        return this.userService.changeUserImage(userPrincipal.getId(), viewModel);
    }
}
