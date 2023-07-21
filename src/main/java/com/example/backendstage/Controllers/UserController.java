package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Models.User;
import com.example.backendstage.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //pour enregistrer un nouvel user
    @PostMapping("/Save")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    //pour récupérer tous les users
    @GetMapping("/User")
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    //pour supprimer un user par son ID
    @DeleteMapping("/{id}")
    public void  deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
