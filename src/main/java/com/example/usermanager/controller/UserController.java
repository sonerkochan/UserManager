package com.example.usermanager.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

import com.example.usermanager.model.User;
import com.example.usermanager.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
@Controller
@RequestMapping("/users")
@Tag(
        name = "User Management",
        description = "Provides operations to manage users, including creating new users, viewing user details, updating existing users, and deleting users. These APIs are designed for administrative purposes."
)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "List all users", description = "Retrieve a list of all registered users in the system.")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-list";
    }

    @GetMapping("/new")
    @Operation(summary = "New user form", description = "Displays a form to create a new user.")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping
    @Operation(summary = "Save a new user", description = "Adds a new user to the system database.")
    public String saveUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/details/{id}")
    @Operation(summary = "View user details", description = "Displays the details of a specific user, identified by their ID.")
    public String viewUser(
            @Parameter(description = "ID of the user to view") @PathVariable Long id,
            Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        model.addAttribute("user", user);
        return "user-details";
    }

    @GetMapping("/edit/{id}")
    @Operation(summary = "Edit user form", description = "Displays a form to edit the details of an existing user.")
    public String editUserForm(
            @Parameter(description = "ID of the user to edit") @PathVariable Long id,
            Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update a user", description = "Updates the details of a specific user, identified by their ID.")
    public String updateUser(
            @Parameter(description = "ID of the user to update") @PathVariable Long id,
            @ModelAttribute User user) {
        userService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setId(id);
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "Delete a user", description = "Deletes a specific user from the system, identified by their ID.")
    public String deleteUser(
            @Parameter(description = "ID of the user to delete") @PathVariable Long id) {
        userService.getUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        userService.deleteUser(id);
        return "redirect:/users";
    }
}