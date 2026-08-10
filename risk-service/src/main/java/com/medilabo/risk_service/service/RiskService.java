package com.medilabo.risk_service.service;

import com.medilabo.risk_service.dto.NoteDTO;
import com.medilabo.risk_service.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiskService {

    private final RestTemplate restTemplate;

    @Value("${patient.service.url}")
    private String patientServiceUrl;

    @Value("${notes.service.url}")
    private String notesServiceUrl;

    private static final List<String> TRIGGERS = Arrays.asList(
            "hémoglobine a1c",
            "microalbumine",
            "taille",
            "poids",
            "fumeur",
            "fumeuse",
            "fumer",
            "anormal",
            "cholestérol",
            "vertige",
            "rechute",
            "réaction",
            "anticorps"
    );

    public String calculateRisk(Integer patientId) {
        PatientDTO patient = restTemplate.getForObject(
                patientServiceUrl + "/api/patients/" + patientId, PatientDTO.class);

        if (patient == null) {
            throw new RuntimeException("Patient not found: " + patientId);
        }

        NoteDTO[] notesArray = restTemplate.getForObject(
                notesServiceUrl + "/api/notes/" + patientId, NoteDTO[].class);
        List<NoteDTO> notes = notesArray != null ? Arrays.asList(notesArray) : List.of();

        int triggerCount = countTriggers(notes);
        int age = Period.between(patient.getBirthDate(), LocalDate.now()).getYears();
        boolean isMale = "M".equalsIgnoreCase(patient.getGender());

        log.info("Patient: {}, Age: {}, Gender: {}, Triggers: {}",
                patient.getLastName(), age, patient.getGender(), triggerCount);
        return determineRisk(age, isMale, triggerCount);
    }

    private int countTriggers(List<NoteDTO> notes) {
        String allNotes = notes.stream()
                .map(NoteDTO::getNote)
                .reduce("", (a, b) -> a + " " + b)
                .toLowerCase();

        int count = 0;
        for (String trigger : TRIGGERS) {
            if (allNotes.contains(trigger)) {
                count++;
            }
        }
        return count;
    }

    private String determineRisk(int age, boolean isMale, int triggerCount) {
        if (triggerCount == 0) {
            return "None";
        }

        if (age < 30) {
            if (isMale && triggerCount >= 5) return "Early onset";
            if (!isMale && triggerCount >= 7) return "Early onset";
        } else {
            if (triggerCount >= 8) return "Early onset";
        }

        if (age < 30) {
            if (isMale && triggerCount >= 3) return "In Danger";
            if (!isMale && triggerCount >= 4) return "In Danger";
        } else {
            if (triggerCount >= 6) return "In Danger";
        }

        if (age > 30 && triggerCount >= 2 && triggerCount <= 5) {
            return "Borderline";
        }
        return "None";
    }
}