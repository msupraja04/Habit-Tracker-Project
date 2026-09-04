package com.example.habittracker.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import com.example.habittracker.model.User;
import com.example.habittracker.service.UserService;
import com.example.habittracker.dto.LoginResponseDTO;
import com.example.habittracker.dto.EditProfileDTO;
import com.example.habittracker.dto.ChangePasswordDTO;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user)
    {
        return userService.registerUser(user);
    }
    
    @PostMapping("/login")
    public LoginResponseDTO loginUser(
    @RequestBody User user)
    {
        return userService.loginUser(user);
    }
    @GetMapping("/profile/{id}")
    public User getProfile(
    @PathVariable int id)
    {
        return userService.getProfile(id);
    }
    @PutMapping("/update-profile/{userId}")
    public User updateProfile(
    @PathVariable int userId,
    @RequestBody EditProfileDTO dto)
    {
        return userService.updateProfile(
        userId,
        dto);
    }
    @PutMapping("/change-password/{userId}")
    public String changePassword(
    @PathVariable int userId,
    @RequestBody ChangePasswordDTO dto)
    {
        return userService.changePassword(
        userId,
        dto);
    }
    @PostMapping("/upload-photo/{userId}")
    public User uploadPhoto(
    @PathVariable int userId,
    @RequestParam("file")
    MultipartFile file)
    throws Exception
    {
        return userService.uploadPhoto(
        userId,
        file);
    }
    
    @PutMapping("/remove-photo/{userId}")
    public User removePhoto(
    @PathVariable int userId)
    {
        return userService.removePhoto(
        userId);
    }
}