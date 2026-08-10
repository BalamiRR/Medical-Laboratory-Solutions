package com.medilabo.frontend_service.service;

import com.medilabo.frontend_service.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientClientService {

    private final RestTemplate restTemplate;

    @Value("${patient.service.url}")
    private String patientServiceUrl;

    public List<PatientDTO> getAllPatients() {
        try {
            PatientDTO[] patients = restTemplate.getForObject(
                    patientServiceUrl + "/api/patients", PatientDTO[].class);
            return patients != null ? Arrays.asList(patients) : Collections.emptyList();
        } catch (Exception e) {
            log.error("Error fetching patients: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public PatientDTO getPatientById(Integer id) {
        try {
            return restTemplate.getForObject(
                    patientServiceUrl + "/api/patients/" + id, PatientDTO.class);
        } catch (Exception e) {
            log.error("Error fetching patient {}: {}", id, e.getMessage());
            return null;
        }
    }

    public void createPatient(PatientDTO patient) {
        restTemplate.postForObject(
                patientServiceUrl + "/api/patients", patient, PatientDTO.class);
    }

    public void updatePatient(Integer id, PatientDTO patient) {
        patient.setId(id);
        restTemplate.put(patientServiceUrl + "/api/patients", patient);
    }
}