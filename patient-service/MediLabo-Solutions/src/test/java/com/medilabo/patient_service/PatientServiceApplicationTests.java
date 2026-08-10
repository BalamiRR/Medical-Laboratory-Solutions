package com.medilabo.patient_service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.patient_service.controller.PatientController;
import com.medilabo.patient_service.model.Patient;
import com.medilabo.patient_service.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc
class PatientServiceApplicationTests {

	@Mock
	private MockMvc mockMvc;

	@MockBean
	private PatientService patientService;

	@Autowired
	private ObjectMapper objectMapper;


	@InjectMocks
	private PatientController patientController;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllPatients() {
		Patient patient1 = new Patient(1, "Test1", "TestNone1", new Date(45, 5, 24), "F", "1 Brookside St first", "100-222-1111");
		Patient patient2 = new Patient(2, "Test2", "TestNone2", new Date(45, 5, 24), "M", "1 Brookside St second", "100-222-2222");

		List<Patient> patients = Arrays.asList(patient1, patient2);
		when(patientService.getAllPatients()).thenReturn(patients);

		ResponseEntity<List<Patient>> responseEntity = patientController.getAllPatients();

		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void testGetPatientById() {
		Patient patient = new Patient(1, "Test1", "TestNone1", new Date(45, 5, 24), "F", "1 Brookside St first", "100-222-1111");

		when(patientService.getPatientById(1)).thenReturn(java.util.Optional.of(patient));

		ResponseEntity<Patient> responseEntity = patientController.getPatientById("1");

		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(patient, responseEntity.getBody());
	}

	@Test
	void testCreatePatient() {
		Patient patient = new Patient(1, "Test1", "TestNone1", new Date(45, 5, 24), "F", "1 Brookside St first", "100-222-1111");

		when(patientService.createPatient(patient)).thenReturn(patient);

		ResponseEntity<Patient> responseEntity = patientController.createPatient(patient);

		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(patient, responseEntity.getBody());
	}

	@Test
	void testUpdatePatient() {
		Patient updatedPatient = new Patient(1, "Test1", "TestNone1", new Date(45, 5, 24), "F",
				"1 Brookside St first", "100-222-1111");
		when(patientService.updatePatient(updatedPatient)).thenReturn(updatedPatient);

		ResponseEntity<Patient> responseEntity = patientController.updatePatient(updatedPatient);
		assertEquals(updatedPatient, responseEntity.getBody());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	@Test
	void testDeletePatient() {
		String id = "1";
		Patient toDeleted = new Patient(1, "Test1", "TestNone1", new Date(45, 5, 24), "F", "1 Brookside St first", "100-222-1111");

		Optional<Patient> patient = Optional.of(toDeleted);
		when(patientService.getPatientById(Integer.parseInt(id))).thenReturn(patient);

		ResponseEntity<Optional<Patient>> responseEntity = patientController.deletePatient(id);

		assertTrue(responseEntity.getBody().isPresent());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(patientService, times(1)).deletePatient(Integer.parseInt(id));
	}

	@Test
	void testDeletePatientNotFound() {
		String id = "100"; // Assuming this ID does not exist
		when(patientService.getPatientById(Integer.parseInt(id))).thenReturn(Optional.empty());
		ResponseEntity<Optional<Patient>> responseEntity = patientController.deletePatient(id);
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}


}
