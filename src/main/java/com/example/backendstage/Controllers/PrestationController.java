package com.example.backendstage.Controllers;

        import com.example.backendstage.Models.Prestation;
        import com.example.backendstage.Services.PrestationService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/prestations")
public class PrestationController {
    private final PrestationService prestationService;

    @Autowired
    public PrestationController(PrestationService prestationService) {
        this.prestationService = prestationService;
    }

    @GetMapping("/All")
    public ResponseEntity<List<Prestation>> getAllPrestations() {
        List<Prestation> prestations = prestationService.getAllPrestations();
        return new ResponseEntity<>(prestations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestation> getPrestationById(@PathVariable Long id) {
        Prestation prestation = prestationService.getPrestationById(id);
        if (prestation != null) {
            return new ResponseEntity<>(prestation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Save")
    public ResponseEntity<Prestation> savePrestation(@RequestBody Prestation prestation) {
        Prestation savedPrestation = prestationService.savePrestation(prestation);
        return new ResponseEntity<>(savedPrestation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/Update")
    public ResponseEntity<Prestation> updatePrestation(@PathVariable Long id, @RequestBody Prestation updatedPrestation) {
        Prestation updated = prestationService.updatePrestation(id, updatedPrestation);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrestation(@PathVariable Long id) {
        prestationService.deletePrestationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{employeurID}/assignEmployeurToPrestation/{prestationID}")
    public void assignEmployeurToPrestation(@PathVariable Long employerID,@PathVariable Long prestationID){
        prestationService.assignEmployeurToPrestation(prestationID,employerID);
    }
}
