package com.example.backendstage.Services;

import com.example.backendstage.Models.Role;
import com.example.backendstage.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Méthode pour enregistrer un nouveau rôle dans la base de données
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    // Méthode pour récupérer un rôle par son ID
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    // Méthode pour récupérer tous les rôles de la base de données
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Méthode pour supprimer un rôle par son ID
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
