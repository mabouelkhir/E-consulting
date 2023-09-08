package com.example.backendstage.Controllers;

import com.example.backendstage.Models.Cin;
import com.example.backendstage.Requests.CinRequest;
import com.example.backendstage.Services.CinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cins")
public class CinController {

    private final CinService cinService;

    @Autowired
    public CinController(CinService cinService) {
        this.cinService = cinService;
    }

    @PostMapping("/{idCandidat}")
    public ResponseEntity<Cin> saveCin(@PathVariable Long idCandidat, @RequestBody CinRequest cinRequest) {
        Cin savedCin = cinService.saveCin(idCandidat, cinRequest);
        return new ResponseEntity<>(savedCin, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cin> getCinById(@PathVariable Long id) {
        Cin cin = cinService.getCinById(id);
        if (cin != null) {
            return ResponseEntity.ok(cin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Cin>> getAllCins() {
        List<Cin> cins = cinService.getAllCins();
        return ResponseEntity.ok(cins);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cin> updateCin(@PathVariable Long id, @RequestBody CinRequest updateCin) {
        Cin updatedCin = cinService.updateCin(id, updateCin);
        if (updatedCin != null) {
            return ResponseEntity.ok(updatedCin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCinById(@PathVariable Long id) {
        cinService.deleteCinById(id);
        return ResponseEntity.noContent().build();
    }
}
