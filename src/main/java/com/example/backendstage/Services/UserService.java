package com.example.backendstage.Services;

import com.example.backendstage.Models.User;
import com.example.backendstage.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import com.example.backendstage.exception.NotFoundException;


import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Méthode pour enregistrer un nouvel utilisateur dans la base de données


    // Méthode pour récupérer un utilisateur par son ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer un utilisateur par son adresse e-mail
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Méthode pour récupérer tous les utilisateurs de la base de données
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Méthode pour supprimer un utilisateur par son ID
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
    public User getUserbyID(Long id) throws NotFoundException {
        List<User> users = userRepository.findAll();
        User user = null;
        for(User con : users) {
            if(con.getId()==id) {
                user=con;
            }
        }
        if(user ==null) {
            throw new NotFoundException(String.format("User not found for id =%s" ,id));
        }
        return user;
    }

    public void activateUser (Long id) throws NotFoundException {
        User user = getUserbyID(id);
        user.setAccountVerified(true);
        userRepository.save(user);
    }

    public void desactivateUser (Long id) throws NotFoundException {
        User user = getUserbyID(id);
        user.setAccountVerified(false);
        userRepository.save(user);
    }

}
