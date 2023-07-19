package com.example.backendstage.Models;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column
    private ERole name;

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }


}