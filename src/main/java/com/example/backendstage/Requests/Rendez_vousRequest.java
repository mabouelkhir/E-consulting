package com.example.backendstage.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rendez_vousRequest {
    private String titre;

    private String description;


    private Date date_entretien;


    private String status;
}
