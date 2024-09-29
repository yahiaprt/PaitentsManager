package com.example.demo.controller;

import com.example.demo.dto.PatientDTO;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Operation(summary = "Register a new patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<Patient> registerPatient(@RequestBody PatientDTO patientDTO) {
        Patient patient = patientService.createPatient(patientDTO);
        return ResponseEntity.ok(patient);
    }

    @Operation(summary = "Get patient details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient found"),
            @ApiResponse(responseCode = "404", description = "Patient not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@Parameter(description = "ID of the patient to retrieve") @PathVariable Long id) {
        // Use the correct method name to retrieve the patient
        Optional<Patient> patientOptional = Optional.ofNullable(patientService.getPatientById(id));

        // Check if the patient is present and return the appropriate response
        return patientOptional
                .map(ResponseEntity::ok) // Return 200 OK with the patient if found
                .orElse(ResponseEntity.notFound().build()); // Return 404 Not Found if not found
    }


    @Operation(summary = "Get all patients with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of patients retrieved")
    })
    @GetMapping("/all")
    public ResponseEntity<Page<Patient>> getAllPatients(Pageable pageable) {
        Page<Patient> patients = patientService.getAllPatients(pageable);
        return ResponseEntity.ok(patients);
    }

    @Operation(summary = "Count patients by gender")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Counts retrieved")
    })
    @GetMapping("/count")
    public ResponseEntity<List<Object[]>> countPatientsByGender() {
        List<Object[]> counts = patientService.countPatientsByGender();
        return ResponseEntity.ok(counts);
    }

    @Operation(summary = "Create a new patient based on parent IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Patient created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDTO patientDTO) {
        Patient patient = patientService.createPatient(patientDTO);
        return ResponseEntity.ok(patient);
    }
}
