package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "patients", schema = "exam_inf201")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_naissance", nullable = false)
    private String nomNaissance; // Nom de naissance

    @Column(name = "prenom_naissance", nullable = false)
    private String prenomNaissance; // Premier prénom de naissance

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance; // Date de naissance

    @Column(name = "sexe", nullable = false)
    private String sexe; // Sexe (M ou F)

    @Column(name = "commune_naissance", nullable = false)
    private String communeNaissance; // Commune de naissance

    // Groupe sanguin
    @Column(name = "allele1", nullable = false)
    private String allele1; // Allèle 1 (A, B, O)

    @Column(name = "allele2", nullable = false)
    private String allele2; // Allèle 2 (A, B, O)

    @Column(name = "blood_group", nullable = false)
    private String bloodGroup; // Groupe sanguin calculé

    // Identifiants des parents
    @Column(name = "parent1_id")
    private Long parent1Id; // ID du premier parent

    @Column(name = "parent2_id")
    private Long parent2Id; // ID du deuxième parent

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomNaissance() {
        return nomNaissance;
    }

    public void setNomNaissance(String nomNaissance) {
        this.nomNaissance = nomNaissance;
    }

    public String getPrenomNaissance() {
        return prenomNaissance;
    }

    public void setPrenomNaissance(String prenomNaissance) {
        this.prenomNaissance = prenomNaissance;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCommuneNaissance() {
        return communeNaissance;
    }

    public void setCommuneNaissance(String communeNaissance) {
        this.communeNaissance = communeNaissance;
    }

    public String getAllele1() {
        return allele1;
    }

    public void setAllele1(String allele1) {
        this.allele1 = allele1;
        calculateBloodGroup(); // Calculer le groupe sanguin à chaque fois qu'un allèle est modifié
    }

    public String getAllele2() {
        return allele2;
    }

    public void setAllele2(String allele2) {
        this.allele2 = allele2;
        calculateBloodGroup(); // Calculer le groupe sanguin à chaque fois qu'un allèle est modifié
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public Long getParent1Id() {
        return parent1Id;
    }

    public void setParent1Id(Long parent1Id) {
        this.parent1Id = parent1Id;
    }

    public Long getParent2Id() {
        return parent2Id;
    }

    public void setParent2Id(Long parent2Id) {
        this.parent2Id = parent2Id;
    }

    /**
     * Calculer le groupe sanguin basé sur les allèles
     */
    private void calculateBloodGroup() {
        // Logique pour déterminer le groupe sanguin basé sur les allèles
        if (allele1.equals("A") && allele2.equals("A")) {
            bloodGroup = "A";
        } else if ((allele1.equals("A") && allele2.equals("B")) || (allele1.equals("B") && allele2.equals("A"))) {
            bloodGroup = "AB";
        } else if ((allele1.equals("A") && allele2.equals("O")) || (allele1.equals("O") && allele2.equals("A"))) {
            bloodGroup = "A";
        } else if (allele1.equals("B") && allele2.equals("B")) {
            bloodGroup = "B";
        } else if ((allele1.equals("B") && allele2.equals("O")) || (allele1.equals("O") && allele2.equals("B"))) {
            bloodGroup = "B";
        } else if (allele1.equals("O") && allele2.equals("O")) {
            bloodGroup = "O";
        } else {
            bloodGroup = "Inconnu"; // Cas non prévu
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
