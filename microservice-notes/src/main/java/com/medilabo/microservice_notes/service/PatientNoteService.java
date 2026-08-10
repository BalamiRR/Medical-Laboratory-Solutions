package com.medilabo.microservice_notes.service;

import com.medilabo.microservice_notes.model.Notes;
import com.medilabo.microservice_notes.repository.PatientNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientNoteService {

    @Autowired
    private PatientNoteRepository noteRepository;

    public List<Notes> getAllNotes(){
        System.out.println("HELLO WORLD TEST");
        System.out.println("Database: " + mongoTemplate.getDb().getName());
        return noteRepository.findAll();
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Notes> getNotesByPatientId(Integer id) {
        return noteRepository.findByPatientId(id);
    }

    public Notes createNote(Notes patientNote) {
        patientNote.setId(null);
        return noteRepository.save(patientNote);
    }

    public Optional<Notes> updateNote(String id, Notes updatedNote) {
        return noteRepository.findById(id).map(existing -> {
            existing.setNote(updatedNote.getNote());
            return noteRepository.save(existing);
        });
    }

    public void deleteNote(String id) {
        noteRepository.deleteById(id);
    }

}