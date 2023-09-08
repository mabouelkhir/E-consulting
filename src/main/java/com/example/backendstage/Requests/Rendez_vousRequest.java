package com.example.backendstage.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rendez_vousRequest {
    private String titre;

    private String description;


    private LocalDateTime date_entretien;


    private String status;
}
