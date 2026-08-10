package com.medilabo.risk_service.controller;

import com.medilabo.risk_service.service.RiskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/risk")
@RequiredArgsConstructor
public class RiskController {

    private final RiskService riskService;

    @GetMapping("/{patientId}")
    public ResponseEntity<String> getRisk(@PathVariable Integer patientId) {
        log.info("GET /api/risk/{} - Calculating risk", patientId);
        try {
            String risk = riskService.calculateRisk(patientId);
            return ResponseEntity.ok(risk);
        } catch (Exception e) {
            log.error("Error calculating risk for patient {}: {}", patientId, e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}