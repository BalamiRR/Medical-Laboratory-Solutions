package com.medilabo.frontend_service.controller;

import com.medilabo.frontend_service.dto.NoteDTO;
import com.medilabo.frontend_service.dto.PatientDTO;
import com.medilabo.frontend_service.service.NoteClientService;
import com.medilabo.frontend_service.service.PatientClientService;
import com.medilabo.frontend_service.service.RiskClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PatientWebController {

    private final PatientClientService patientClientService;
    private final NoteClientService noteClientService;
    @Autowired
    private RiskClientService riskClientService;

    // Listing all patients
    @GetMapping("/")
    public String listPatients(Model model) {
        List<PatientDTO> patients = patientClientService.getAllPatients();
        log.info("Patients fetched: {}", patients.size());
        patients.forEach(p -> log.info("Patient: {}", p));
        model.addAttribute("patients", patients);
        return "patients/list";
    }

    // Patient details
    @GetMapping("/patients/{id}")
    public String patientDetail(@PathVariable Integer id, Model model) {
        PatientDTO patient = patientClientService.getPatientById(id);
        if (patient == null) return "redirect:/";
        model.addAttribute("patient", patient);
        model.addAttribute("notes", noteClientService.getNotesByPatientId(id));
        model.addAttribute("newNote", new NoteDTO());
        model.addAttribute("risk", riskClientService.getRiskByPatientId(id));
        return "patients/detail";
    }
    // Showing add form
    @GetMapping("/patients/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new PatientDTO());
        return "patients/add";
    }

    // Add Patient
    @PostMapping("/patients/add")
    public String addPatient(@ModelAttribute PatientDTO patient) {
        patientClientService.createPatient(patient);
        return "redirect:/";
    }

    // Edit form
    @GetMapping("/patients/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("patient", patientClientService.getPatientById(id));
        return "patients/edit";
    }

    // Update patient
    @PostMapping("/patients/edit/{id}")
    public String updatePatient(@PathVariable Integer id, @ModelAttribute PatientDTO patient) {
        patientClientService.updatePatient(id, patient);
        return "redirect:/";
    }

    // adding note
    @PostMapping("/patients/{id}/notes/add")
    public String addNote(@PathVariable Integer id,
                          @ModelAttribute NoteDTO note,
                          @RequestParam String patientLastName) {
        note.setPatientId(id);
        note.setPatient(patientLastName);
        noteClientService.createNote(note);
        return "redirect:/patients/" + id;
    }

    // Showing the note form
    @GetMapping("/patients/{patientId}/notes/edit/{noteId}")
    public String showEditNoteForm(@PathVariable Integer patientId,
                                   @PathVariable String noteId,
                                   Model model) {
        PatientDTO patient = patientClientService.getPatientById(patientId);
        List<NoteDTO> notes = noteClientService.getNotesByPatientId(patientId);
        NoteDTO noteToEdit = notes.stream()
                .filter(n -> n.getId().equals(noteId))
                .findFirst()
                .orElse(null);
        model.addAttribute("patient", patient);
        model.addAttribute("notes", notes);
        model.addAttribute("newNote", new NoteDTO());
        model.addAttribute("editNote", noteToEdit);
        return "patients/detail";
    }

    // Update note
    @PostMapping("/patients/{patientId}/notes/edit/{noteId}")
    public String updateNote(@PathVariable Integer patientId,
                             @PathVariable String noteId,
                             @ModelAttribute NoteDTO note) {
        noteClientService.updateNote(noteId, note);
        return "redirect:/patients/" + patientId;
    }

    // Delete note
    @PostMapping("/patients/{patientId}/notes/delete/{noteId}")
    public String deleteNote(@PathVariable Integer patientId,
                             @PathVariable String noteId) {
        noteClientService.deleteNote(noteId);
        return "redirect:/patients/" + patientId;
    }


}