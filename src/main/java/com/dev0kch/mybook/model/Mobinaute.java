package com.dev0kch.mybook.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "mobinaute")
public class Mobinaute {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nom_utilisateur", nullable = false)
    private String nomUtilisateur;

    private String nom;
    private String prenom;
    @Column(name = "mot_passe", nullable = false)
    private String motPasse;
    private String sexe;
    private String paye;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = "date_naissance", nullable = false)
    private Date   dateNaissance;
    private String email;



}
