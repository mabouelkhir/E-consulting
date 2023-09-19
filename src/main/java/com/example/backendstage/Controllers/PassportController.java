package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Passport;
import com.example.backendstage.Requests.PassportRequest;
import com.example.backendstage.Services.PassportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/passports")
public class PassportController {
    private final PassportService passportService;

    @Autowired
    public PassportController(PassportService passportService) {
        this.passportService = passportService;
    }
    @PostMapping("/{candidatID}/create")
    public ResponseEntity<?> createPassport(@PathVariable Long candidatID, @Valid @RequestBody PassportRequest passportRequest) {
        try {
            Passport passport = passportService.createPassport(candidatID, passportRequest);
            return ResponseEntity.ok(passport);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Return the custom error message
        }
    }
    @GetMapping("/all")
    public List<Passport> getAllPassports() {
        return passportService.getAllPassports();
    }

    @GetMapping("/{id}")
    public Optional<Passport> getPassportById(@PathVariable Long id) {
        return passportService.getPassportById(id);
    }

    @PutMapping("/{id}/update")
    public Passport updatePassport(@PathVariable Long id, @RequestBody PassportRequest passportRequest) {
        return passportService.updatePassport(id, passportRequest);
    }

    @DeleteMapping("/{id}/delete")
    public void deletePassport(@PathVariable Long id) {
        passportService.deletePassport(id);
    }

}
