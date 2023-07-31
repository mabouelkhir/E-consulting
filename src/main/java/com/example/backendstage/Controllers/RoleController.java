package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Agent;
import com.example.backendstage.Models.Role;
import com.example.backendstage.Services.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    //pour enregistrer un nouvel role
    @PostMapping("/Save")
    public Role saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    //pour récupérer tous les roles
    @GetMapping("/All")
    public List<Role> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return roles;
    }

    //pour supprimer un role par son ID
    @DeleteMapping("/{id}")
    public void  deleteRoleById(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }
}
