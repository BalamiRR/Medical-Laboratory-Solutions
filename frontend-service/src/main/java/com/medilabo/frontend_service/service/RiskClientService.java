package com.medilabo.frontend_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiskClientService {

    private final RestTemplate restTemplate;

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