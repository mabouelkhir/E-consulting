package com.example.backendstage.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "candidats")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String sexe;
    @Column
    private LocalDateTime createdAt;
    @Column
    private String adresse;
    @Column
    private String num_tel;
    @Column
    private String email;

    @Column(length = 500000)
    @Lob
    private byte[] image;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "candidat_fonctions",
            joinColumns ={ @JoinColumn(name = "candidat_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "fonction_id",referencedColumnName = "id")})
    private Set<Fonction> fonctions ;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Cin cin;

    @Column
    private String obs;
    @OneToMany(mappedBy = "candidat")
    @JsonManagedReference
    private List<Candidat_IdentityPieces> candidatIdentityPieces;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidat")
    @JsonManagedReference
    private List<Rendez_vous> rendez_vous;

    @Column
    private String tl;

    @Column
    private String groupe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    private EStatus status;
    @Lob
    @Column(length = 500000)
    private byte[] cv;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidat")
    @JsonBackReference
    private List<Reglement> reglements;



    @Column
    private String situation_fam;
<<<<<<< HEAD
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "candidat_subfunctions",
            joinColumns = @JoinColumn(name = "candidat_id"),
            inverseJoinColumns = @JoinColumn(name = "subfunction_id"))
    private Set<SubFonction> subfonctions = new HashSet<>();
=======
    @Column
    private String Ref_contrat;

>>>>>>> 98cc3019a18c504e7b735ba59b3259015c4d3d4b
    @Column
    private int children;
    @Column
    private boolean visaRecu;
    @Column
    private boolean tlsRecu;
    @Column
    private boolean ofiiRecu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "employeur_id")
    private Employeur employeur;



    public void setVisaColor(String newColor) {
    }

    public void setTLSColor(String newColor) {
    }

    public void setOFFIColor(String newColor) {
    }

    public void setOFFIReçu(Boolean newOFFIState) {
    }

    public void setTLSReçu(Boolean newTLSState) {
    }

    public void setVisaReçu(Boolean newVisaState) {
    }
}
