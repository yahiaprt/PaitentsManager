package com.example.demo.service;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Enregistrer un nouveau patient
     *
     * @param patientDto le DTO contenant les caractéristiques du patient
     * @return le patient enregistré
     */
    @Transactional
    public Patient createPatient(PatientDTO patientDto) {
        // Valider les données d'identité strictes
        validatePatientIdentity(patientDto);

        // Créer un nouvel objet Patient à partir du DTO
        Patient patient = new Patient();
        patient.setNomNaissance(patientDto.getNomNaissance());
        patient.setPrenomNaissance(patientDto.getPrenomNaissance());
        patient.setDateNaissance(patientDto.getDateNaissance());
        patient.setSexe(patientDto.getSexe());
        patient.setCommuneNaissance(patientDto.getCommuneNaissance());
        patient.setAllele1(patientDto.getAllele1());
        patient.setAllele2(patientDto.getAllele2());
        patient.setParent1Id(patientDto.getParent1Id());
        patient.setParent2Id(patientDto.getParent2Id());
        // Calculer le groupe sanguin à partir des allèles
        patientDto.calculerGroupeSanguin();

        // Enregistrer le patient dans le référentiel
        return patientRepository.save(patient);
    }

    /**
     * Récupérer les données d'un patient par ID
     *
     * @param id l'ID du patient
     * @return le patient trouvé ou une exception si non trouvé
     */
    public Patient getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID: " + id));
    }


    /**
     * Récupérer tous les patients de manière paginée
     *
     * @param pageable les paramètres de pagination
     * @return une page de patients
     */
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    /**
     * Valider les caractéristiques d'identité du patient
     *
     * @param patientDto le DTO du patient à valider
     */
    private void validatePatientIdentity(PatientDTO patientDto) {
        if (patientDto.getNomNaissance() == null || patientDto.getNomNaissance().isEmpty()) {
            throw new IllegalArgumentException("Le nom de naissance est requis.");
        }
        if (patientDto.getPrenomNaissance() == null || patientDto.getPrenomNaissance().isEmpty()) {
            throw new IllegalArgumentException("Le prénom de naissance est requis.");
        }
        if (patientDto.getDateNaissance() == null) {
            throw new IllegalArgumentException("La date de naissance est requise.");
        }
        if (patientDto.getSexe() == null || (!patientDto.getSexe().equals("M") && !patientDto.getSexe().equals("F"))) {
            throw new IllegalArgumentException("Le sexe doit être 'M' ou 'F'.");
        }
        if (patientDto.getCommuneNaissance() == null || patientDto.getCommuneNaissance().isEmpty()) {
            throw new IllegalArgumentException("La commune de naissance est requise.");
        }

        // Validation des allèles
        validateAlleles(patientDto.getAllele1(), patientDto.getAllele2());
    }
    public List<Object[]> countPatientsByGender() {
        return patientRepository.countPatientsByGender();
    }
    /**
     * Valider les allèles
     *
     * @param allele1 l'allèle 1
     * @param allele2 l'allèle 2
     */
    private void validateAlleles(String allele1, String allele2) {
        String[] validAlleles = {"A", "B", "O"};
        if (!isValidAllele(allele1, validAlleles) || !isValidAllele(allele2, validAlleles)) {
            throw new IllegalArgumentException("Les allèles doivent être 'A', 'B' ou 'O'.");
        }
    }

    /**
     * Vérifie si l'allèle est valide
     *
     * @param allele      l'allèle à vérifier
     * @param validAlleles les allèles valides
     * @return vrai si l'allèle est valide, faux sinon
     */
    private boolean isValidAllele(String allele, String[] validAlleles) {
        for (String validAllele : validAlleles) {
            if (validAllele.equals(allele)) {
                return true;
            }
        }
        return false;
    }
}
