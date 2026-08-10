package com.medilabo.frontend_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private String id;
    private int patientId;
    private String patient;
    private String note;
}