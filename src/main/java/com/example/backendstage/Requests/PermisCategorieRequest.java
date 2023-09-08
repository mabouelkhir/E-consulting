package com.example.backendstage.Requests;

import com.example.backendstage.Models.EPermis;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermisCategorieRequest {

    private EPermis categorie;

    private Date date_delivrance;
}
