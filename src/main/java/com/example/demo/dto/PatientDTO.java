package com.example.demo.dto;

import java.time.LocalDate;

public class PatientDTO {

    // Identité stricte
    private String nomNaissance; // Nom de naissance
    private String prenomNaissance; // Premier prénom de naissance
    private LocalDate dateNaissance; // Date de naissance
    private String sexe; // Sexe (M ou F)
    private String communeNaissance; // Commune de naissance

    // Groupe sanguin
    private String allele1; // Allèle 1 (A, B, O)
    private String allele2; // Allèle 2 (A, B, O)
    private String bloodGroup; // Groupe sanguin calculé

    // Identifiants des parents
    private Long parent1Id; // ID du premier parent
    private Long parent2Id; // ID du deuxième parent

    // Constructeur par défaut
    public PatientDTO() {}

    // Getters et Setters

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
    }

    public String getAllele2() {
        return allele2;
    }

    public void setAllele2(String allele2) {
        this.allele2 = allele2;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
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

    // Méthode pour calculer le groupe sanguin basé sur les allèles
    public void calculerGroupeSanguin() {
        if (allele1.equals("O") && allele2.equals("O")) {
            bloodGroup = "O";
        } else if (allele1.equals("A") && allele2.equals("A")) {
            bloodGroup = "A";
        } else if (allele1.equals("B") && allele2.equals("B")) {
            bloodGroup = "B";
        } else if ((allele1.equals("A") && allele2.equals("B")) || (allele1.equals("B") && allele2.equals("A"))) {
            bloodGroup = "AB";
        } else if (allele1.equals("O")) {
            bloodGroup = allele2;
        } else if (allele2.equals("O")) {
            bloodGroup = allele1;
        }
    }
}
