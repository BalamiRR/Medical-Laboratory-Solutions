package com.medilabo.microservice_notes.control;

import com.medilabo.microservice_notes.model.Notes;
import com.medilabo.microservice_notes.service.PatientNoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notes")
public class PatientNoteController {

    @Autowired
    private PatientNoteService noteService;

    @GetMapping
    public ResponseEntity<List<Notes>> getAllNotes() {
        log.info("GET /api/notes/ - Getting all notes");
        List<Notes> notes = noteService.getAllNotes();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<Notes>> getNotesByPatientId(@PathVariable Integer patientId) {
        log.info("Getting notes for patientId: {}", patientId);
        List<Notes> notes = noteService.getNotesByPatientId(patientId);
        return ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<Notes> createNote(@RequestBody Notes note) {
        log.info("POST /api/notes - Creating a new note: {}", note);
        try {
            Notes createdNote = noteService.createNote(note);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
        } catch (Exception e) {
            log.error("Error creating note: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notes> updateNote(@PathVariable String id, @RequestBody Notes note) {
        log.info("PUT /api/notes/{} - Updating note", id);
        return noteService.updateNote(id, note)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
        log.info("DELETE /api/notes/{} - Deleting note", id);
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

}
