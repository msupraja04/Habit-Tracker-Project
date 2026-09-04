package com.example.habittracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.habittracker.model.User;
import com.example.habittracker.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDate;
import com.example.habittracker.dto.LoginResponseDTO;
import com.example.habittracker.dto.EditProfileDTO;
import com.example.habittracker.dto.ChangePasswordDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public User registerUser(User user)
    {
        try
        {
            BCryptPasswordEncoder encoder =
                    new BCryptPasswordEncoder();

            String encryptedPassword =
                    encoder.encode(user.getPassword());

            user.setPassword(encryptedPassword);
            user.setCreatedAt(LocalDate.now());

            user.setProfileImage("default-avatar.png");

            return userRepository.save(user);
        }
        catch(Exception e)
        {
            e.printStackTrace();

            return null;
        }
    }
    
    public User getProfile(int id)
    {
        return userRepository.findById(id);
    }
    
   // public String loginUser(User user)
    public LoginResponseDTO loginUser(User user)
    {
        LoginResponseDTO response =
                new LoginResponseDTO();

        User existingUser =
                userRepository.findByEmail(
                        user.getEmail());

        if(existingUser != null)
        {
            BCryptPasswordEncoder encoder =
                    new BCryptPasswordEncoder();

            if(encoder.matches(
                    user.getPassword(),
                    existingUser.getPassword()))
            {
                response.setId(
                        existingUser.getId());

                response.setName(
                        existingUser.getName());

                response.setEmail(
                        existingUser.getEmail());

                response.setMessage(
                        "Login Successful");

                return response;
            }
            else
            {
                response.setMessage(
                        "Invalid Password");

                return response;
            }
        }

        response.setMessage(
                "User Not Found");

        return response;
    }
    
    public User updateProfile(
    		int userId,
    		EditProfileDTO dto)
    		{
    		    User user =
    		    userRepository.findById(userId);

    		    if(user == null)
    		    {
    		        return null;
    		    }

    		    user.setName(dto.getName());

    		    user.setEmail(dto.getEmail());

    		    return userRepository.save(user);
    		}
    public String changePassword(
    		int userId,
    		ChangePasswordDTO dto)
    		{
    		    User user =
    		    userRepository.findById(userId);

    		    if(user == null)
    		    {
    		        return "User Not Found";
    		    }

    		    BCryptPasswordEncoder encoder =
    		    new BCryptPasswordEncoder();

    		    if(!encoder.matches(
    		            dto.getOldPassword(),
    		            user.getPassword()))
    		    {
    		        return "Current Password Incorrect";
    		    }

    		    user.setPassword(
    		    encoder.encode(dto.getNewPassword()));

    		    userRepository.save(user);

    		    return "Password Updated Successfully";
    		}
    public User uploadPhoto(
    		int userId,
    		MultipartFile file) throws IOException
    		{
    	System.out.println("Upload API Called");
    		    User user =
    		    userRepository.findById(userId);

    		    if(user == null)
    		    {
    		        return null;
    		    }

    		    String fileName =
    		    System.currentTimeMillis()
    		    + "_"
    		    + file.getOriginalFilename();

    		    String uploadDir =
    		    "C:/Users/LENOVO/Desktop/Habit Tracker front end/uploads/";

    		    File folder =
    		    new File(uploadDir);

    		    if(!folder.exists())
    		    {
    		        folder.mkdirs();
    		    }

    		    file.transferTo(
    		    new File(uploadDir + fileName));

    		    user.setProfileImage(fileName);

    		    return userRepository.save(user);
    		}
    
    public User removePhoto(
    		int userId)
    		{
    		    User user =
    		    userRepository.findById(userId);

    		    if(user == null)
    		    {
    		        return null;
    		    }

    		    user.setProfileImage(
    		    "default-avatar.png");

    		    return userRepository.save(user);
    		}
}