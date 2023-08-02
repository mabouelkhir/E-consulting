package com.example.backendstage.Controllers;

import com.example.backendstage.Models.*;
import com.example.backendstage.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    AgentRepository agentRepository;
    @Autowired
    CandidatRepository candidatRepository;
    @Autowired
    OperateurRepository operateurRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private EmployeurRepository employeurRepository;

    @GetMapping("/{type}/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable String type, @PathVariable Long id) {
        byte[] imageBytes = null;
        String contentType = null;

        switch (type) {
            case "agent":
                Optional<Agent> agentOptional = agentRepository.findById(id);
                if (agentOptional.isPresent()) {
                    Agent agent = agentOptional.get();
                    imageBytes = agent.getImage();
                    contentType = MediaType.IMAGE_JPEG_VALUE;
                }
                break;

            case "candidat":
                Optional<Candidat> candidatOptional = candidatRepository.findById(id);
                if (candidatOptional.isPresent()) {
                    Candidat candidat = candidatOptional.get();
                    imageBytes = candidat.getImage()    ;
                    contentType = MediaType.IMAGE_JPEG_VALUE;
                }
                break;

            case "operateur":
                Optional<Operateur> optionalOperateur = operateurRepository.findById(id);
                if (optionalOperateur.isPresent()) {
                    Operateur operateur = optionalOperateur.get();
                    imageBytes = operateur.getImage();
                    contentType = MediaType.IMAGE_JPEG_VALUE;
                }
                break;
            case "admin":
                Optional<Admin> optionalAdmin = adminRepository.findById(id);
                if (optionalAdmin.isPresent()) {
                    Admin admin = optionalAdmin.get();
                    imageBytes = admin.getImage();
                    contentType = MediaType.IMAGE_JPEG_VALUE;
                }
                break;

            default:
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (imageBytes != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(contentType));
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(contentType))
                    .body(new ByteArrayResource(imageBytes));
        } else {
            return new ResponseEntity<>("Image not Found for this " + type + " with ID " +id,HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{type}/{id}/image")
    public ResponseEntity<?> uploadImage(@PathVariable String type, @PathVariable Long id, @RequestPart("image") MultipartFile file) {

        switch (type) {
            case "agent":
                Agent agent = agentRepository.getById(id);
                if (agent !=null) {
                    try {
                        agent.setImage(file.getBytes());
                        agentRepository.save(agent);
                        return new ResponseEntity<>("Image has been uploaded successfully.", HttpStatus.OK);
                    } catch (IOException e) {
                        return new ResponseEntity<>("Image exeption.", HttpStatus.NO_CONTENT);
                    }
                }
                else {
                    return new ResponseEntity<>("User not Found for this " + type + " with ID " + id, HttpStatus.NOT_FOUND);
                }
            case "operateur":
                Operateur operateur = operateurRepository.getById(id);
                if (operateur !=null) {
                    try {
                        operateur.setImage(file.getBytes());
                        operateurRepository.save(operateur);
                        return new ResponseEntity<>("Image has been uploaded successfully.", HttpStatus.OK);
                    } catch (IOException e) {
                        return new ResponseEntity<>("Image exeption.", HttpStatus.NO_CONTENT);
                    }
                }
                else {
                    return new ResponseEntity<>("User not Found for this " + type + " with ID " + id, HttpStatus.NOT_FOUND);
                }
            case "employeur":
                Employeur employeur = employeurRepository.getById(id);
                if (employeur !=null) {
                    try {
                        employeur.setImage(file.getBytes());
                        employeurRepository.save(employeur);
                        return new ResponseEntity<>("Image has been uploaded successfully.", HttpStatus.OK);
                    } catch (IOException e) {
                        return new ResponseEntity<>("Image exeption.", HttpStatus.NO_CONTENT);
                    }
                }
                else {
                    return new ResponseEntity<>("User not Found for this " + type + " with ID " + id, HttpStatus.NOT_FOUND);
                }
            case "admin":
                Admin admin = adminRepository.getById(id);
                if (admin !=null) {
                    try {
                        admin.setImage(file.getBytes());
                        adminRepository.save(admin);
                        return new ResponseEntity<>("Image has been uploaded successfully.", HttpStatus.OK);
                    } catch (IOException e) {
                        return new ResponseEntity<>("Image exeption.", HttpStatus.NO_CONTENT);
                    }
                }
                else {
                    return new ResponseEntity<>("User not Found for this " + type + " with ID " + id, HttpStatus.NOT_FOUND);
                }

            case "candidat":
                Candidat candidat = candidatRepository.getById(id);
                if (candidat !=null) {
                    try {
                        candidat.setImage(file.getBytes());
                        candidatRepository.save(candidat);
                        return new ResponseEntity<>("Image has been uploaded successfully.", HttpStatus.OK);
                    } catch (IOException e) {
                        return new ResponseEntity<>("Image exeption.", HttpStatus.NO_CONTENT);
                    }
                }
                else {
                    return new ResponseEntity<>("User not Found for this " + type + " with ID " + id, HttpStatus.NOT_FOUND);
                }
            default:
                return new ResponseEntity<>("User not Found for this " + type , HttpStatus.NOT_FOUND);

        }

    }

}
