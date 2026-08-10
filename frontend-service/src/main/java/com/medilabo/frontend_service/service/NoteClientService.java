package com.medilabo.frontend_service.service;

import com.medilabo.frontend_service.dto.NoteDTO;
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
public class NoteClientService {

    private final RestTemplate restTemplate;

    @Value("${notes.service.url}")
    private String notesServiceUrl;

    public List<NoteDTO> getNotesByPatientId(int patientId) {
        try {
            NoteDTO[] notes = restTemplate.getForObject(
                    notesServiceUrl + "/api/notes/" + patientId, NoteDTO[].class);
            return notes != null ? Arrays.asList(notes) : Collections.emptyList();
        } catch (Exception e) {
            log.error("Error fetching notes for patient {}: {}", patientId, e.getMessage());
            return Collections.emptyList();
        }
    }

    public void createNote(NoteDTO note) {
        try {
            restTemplate.postForObject(
                    notesServiceUrl + "/api/notes", note, NoteDTO.class);
        } catch (Exception e) {
            log.error("Error creating note: {}", e.getMessage());
        }
    }

    public void updateNote(String id, NoteDTO note) {
        restTemplate.put(notesServiceUrl + "/api/notes/" + id, note);
    }

    public void deleteNote(String id) {
        restTemplate.delete(notesServiceUrl + "/api/notes/" + id);
    }

    @Value("${risk.service.url}")
    private String riskServiceUrl;

    public String getRiskByPatientId(int patientId) {
        try {
            return restTemplate.getForObject(
                    riskServiceUrl + "/api/risk/" + patientId, String.class);
        } catch (Exception e) {
            log.error("Error fetching risk for patient {}: {}", patientId, e.getMessage());
            return "Unknown";
        }
    }
}