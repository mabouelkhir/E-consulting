package com.example.backendstage.Controllers;

import com.example.backendstage.Models.*;


import com.example.backendstage.Repositories.*;
import com.example.backendstage.Requests.RegisterRequest;
import com.example.backendstage.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    OperateurRepository operateurRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AgentRepository agentRepository;
    @Autowired
    CandidatRepository candidatRepository;
    @Autowired
    EmployeurRepository employeurRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //pour enregistrer un nouvel user
    @PostMapping("/Save")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("error: email is already in use!");
        }
        // Create new user's account
        User user = new User(signUpRequest.getFirstName(),signUpRequest.getLastName() ,signUpRequest.getEmail(),
                signUpRequest.getPassword(),signUpRequest.getCreatedAt());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.Candidat)
                    .orElseThrow(() -> new RuntimeException("error: role is not found."));
            roles.add(userRole);
            if(candidatRepository.findByEmail(signUpRequest.getEmail())==null){
                Candidat candidat = new Candidat();
                candidat.setNom(signUpRequest.getLastName());
                candidat.setPrenom(signUpRequest.getFirstName());
                candidat.setEmail(signUpRequest.getEmail());
                candidat.setCreatedAt(LocalDateTime.now());
                candidatRepository.save(candidat);
            }
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":

                        Role admRole = roleRepository.findByName(ERole.Admin)
                                .orElseThrow(() -> new RuntimeException("error: role is not found."));
                        roles.add(admRole);
                        if(adminRepository.findByEmail(signUpRequest.getEmail())==null) {
                            Admin admin = new Admin();
                            admin.setNom(signUpRequest.getLastName());
                            admin.setPrenom(signUpRequest.getFirstName());
                            admin.setEmail(signUpRequest.getEmail());
                            admin.setCreatedAt(LocalDateTime.now());
                            adminRepository.save(admin);
                        }

                        break;
                    case "operateur":
                        Role oprRole = roleRepository.findByName(ERole.Operateur)
                                .orElseThrow(() -> new RuntimeException("error: role is not found."));
                        roles.add(oprRole);
                        if(operateurRepository.findByEmail(signUpRequest.getEmail())==null) {
                            Operateur operateur = new Operateur();
                            operateur.setNom(signUpRequest.getLastName());
                            operateur.setPrenom(signUpRequest.getFirstName());
                            operateur.setEmail(signUpRequest.getEmail());
                            operateur.setCreatedAt(LocalDateTime.now());

                            operateurRepository.save(operateur);
                        }

                        break;
                    case "agent":
                        Role agnRole = roleRepository.findByName(ERole.Agent)
                                .orElseThrow(() -> new RuntimeException("error: role is not found."));
                        roles.add(agnRole);
                        if(agentRepository.findByEmail(signUpRequest.getEmail())==null){
                            Agent agent = new Agent();
                            agent.setNom(signUpRequest.getLastName());
                            agent.setPrenom(signUpRequest.getFirstName());
                            agent.setEmail(signUpRequest.getEmail());
                            agent.setCreatedAt(LocalDateTime.now());
                            agentRepository.save(agent);
                        }
                        break;
                    case "candidat":
                        Role canRole = roleRepository.findByName(ERole.Candidat)
                                .orElseThrow(() -> new RuntimeException("error: role is not found."));
                        roles.add(canRole);
                        if(candidatRepository.findByEmail(signUpRequest.getEmail())==null) {
                            Candidat candidat = new Candidat();
                            candidat.setNom(signUpRequest.getLastName());
                            candidat.setPrenom(signUpRequest.getFirstName());
                            candidat.setEmail(signUpRequest.getEmail());
                            candidat.setCreatedAt(LocalDateTime.now());
                            candidatRepository.save(candidat);
                        }

                        break;
                    case "employeur":
                        Role empRole = roleRepository.findByName(ERole.Employeur)
                                .orElseThrow(() -> new RuntimeException("error: role is not found."));
                        roles.add(empRole);
                        if(employeurRepository.findByEmail(signUpRequest.getEmail())==null) {
                            Employeur employeur = new Employeur();
                            employeur.setNom(signUpRequest.getLastName());
                            employeur.setPrenom(signUpRequest.getFirstName());
                            employeur.setEmail(signUpRequest.getEmail());
                            employeur.setCreatedAt(LocalDateTime.now());
                            employeurRepository.save(employeur);
                        }

                        break;
                    default:
                        throw new RuntimeException("Error: Invalid role specified.");
                }
            });
        }
        user.setRoles(roles);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
        //notificationService.createUserNotification(user);
        return ok("user registered successfully!  Un email a été envoyé au proprietaire du compte");
    }

    //pour récupérer tous les users
    @GetMapping("/All")
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
