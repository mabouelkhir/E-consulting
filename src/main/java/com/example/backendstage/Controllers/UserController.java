package com.example.backendstage.Controllers;

import com.example.backendstage.Models.*;


import com.example.backendstage.Repositories.*;
import com.example.backendstage.Requests.LoginRequest;
import com.example.backendstage.Requests.LoginResponse;
import com.example.backendstage.Requests.RegisterRequest;
import com.example.backendstage.Services.UserService;
import com.example.backendstage.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import java.time.LocalDateTime;
import java.util.Date;
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
    private BCryptPasswordEncoder passwordEncoder;

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
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        // Hash the password
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        user.setPassword(hashedPassword);
        user.setRole(signUpRequest.getRole());
        user.setCreatedAt(new Date());
        user.setAccountVerified(false);

        Role strRoles = signUpRequest.getRole();
        String roleName = String.valueOf(strRoles.getName());


        Long candidatId = null;
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.Candidat)
                    .orElseThrow(() -> new RuntimeException("error: role is not found."));
        } else if (roleName.equals("Admin")) {

            if (adminRepository.findByEmail(signUpRequest.getEmail()) == null) {
                Admin admin = new Admin();
                admin.setNom(signUpRequest.getLastName());
                admin.setPrenom(signUpRequest.getFirstName());
                admin.setEmail(signUpRequest.getEmail());
                admin.setCreatedAt(new Date());
                adminRepository.save(admin);
            }
        } else if (roleName.equals("Operateur")) {

            if (operateurRepository.findByEmail(signUpRequest.getEmail()) == null) {
                Operateur operateur = new Operateur();
                operateur.setNom(signUpRequest.getLastName());
                operateur.setPrenom(signUpRequest.getFirstName());
                operateur.setEmail(signUpRequest.getEmail());
                operateur.setCreatedAt(new Date());

                operateurRepository.save(operateur);
            }
        } else if (roleName.equals("Agent")) {

            if (agentRepository.findByEmail(signUpRequest.getEmail()) == null) {
                Agent agent = new Agent();
                agent.setNom(signUpRequest.getLastName());
                agent.setPrenom(signUpRequest.getFirstName());
                agent.setEmail(signUpRequest.getEmail());
                agent.setCreatedAt(LocalDateTime.now());
                agentRepository.save(agent);
            }
        }
        else if (roleName.equals("Employeur")) {

            if (employeurRepository.findByEmail(signUpRequest.getEmail()) == null) {
                Employeur employeur = new Employeur();
                employeur.setNom(signUpRequest.getLastName());
                employeur.setPrenom(signUpRequest.getFirstName());
                employeur.setEmail(signUpRequest.getEmail());
                employeur.setCreatedAt(new Date());
                employeurRepository.save(employeur);
            }
        }
        else if (roleName.equals("Candidat")) {

                if (candidatRepository.findByEmail(signUpRequest.getEmail()) == null) {
                    Candidat candidat = new Candidat();
                    candidat.setNom(signUpRequest.getLastName());
                    candidat.setPrenom(signUpRequest.getFirstName());
                    candidat.setEmail(signUpRequest.getEmail());
                    candidat.setCreatedAt(new Date());
                    candidat.setStatus(EStatus.INACTIF);
                    candidat.setOFFIReçu(false);
                    candidat.setTLSReçu(false);
                    candidat.setVisaReçu(false);
                    candidatRepository.save(candidat);
                    candidatId = candidat.getId();

                }else {
                    throw new RuntimeException("Error: Invalid role specified.");
                }
            }
        userRepository.save(user);
        //notificationService.createUserNotification(user);
        if (candidatId != null) {
            return ResponseEntity.ok("User registered successfully! Candidat ID: " + hashedPassword);
        } else {
            // Continue with your existing code after registration
            // ...
            //notificationService.createUserNotification(user);
            return ResponseEntity.ok("User registered successfully!");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            // User not found or incorrect password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }

        if (!user.isAccountVerified()) {
            // Account is not activated
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Account is not activated. Please contact an admin.");
        }

        // Here, you can return the user object based on their role
        String roleName = String.valueOf(user.getRole().getName());
        Object userObject = null;

        switch (roleName) {
            case "Admin":
                userObject = adminRepository.findByEmail(loginRequest.getEmail());
                break;
            case "Operateur":
                userObject = operateurRepository.findByEmail(loginRequest.getEmail());
                break;
            case "Agent":
                userObject = agentRepository.findByEmail(loginRequest.getEmail());
                break;
            case "Employeur":
                userObject = employeurRepository.findByEmail(loginRequest.getEmail());
                break;
            case "Candidat":
                userObject = candidatRepository.findByEmail(loginRequest.getEmail());
                break;
            default:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid role");
        }

        // Create a LoginResponse object with role and user
        LoginResponse loginResponse = new LoginResponse(roleName, userObject);

        return ResponseEntity.ok(loginResponse);
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
    @PutMapping("/{id}/activer")
    public void  activateUser(@PathVariable Long id) throws NotFoundException {
            userService.activateUser(id);
    }
    @PutMapping("/{id}/desactiver")
    public void  desactivateUser(@PathVariable Long id) throws NotFoundException {
        userService.desactivateUser(id);
    }
}
