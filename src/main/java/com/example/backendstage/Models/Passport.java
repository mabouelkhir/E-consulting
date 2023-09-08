package com.example.backendstage.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Table(name = "Passport")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Date date_validite;

    private boolean isValid;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;

    public void passportValide() {
        LocalDate currentDate = LocalDate.now();
        LocalDate validUntilDate = date_validite.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Calculate the difference in months between current date and valid until date
        long monthsDifference = ChronoUnit.MONTHS.between(currentDate, validUntilDate);

        // Set isValid based on the condition (e.g., 15 months or less)
        isValid = monthsDifference > 15;
    }
}
