package com.seek.selection_api;

import com.seek.selection_api.service.CandidateService;
import com.seek.selection_api.persistence.entity.Candidate;
import com.seek.selection_api.persistence.repository.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CandidateServiceIntegrationTest {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateRepository candidateRepository;

    @BeforeEach
    void setUp() {
        candidateRepository.deleteAll();
    }

    @Test
    void saveCandidate_ShouldReturnSavedCandidate() {
        Candidate candidate = new Candidate();
        candidate.setName("John Doe");
        candidate.setEmail("john.doe@example.com");

        Candidate savedCandidate = candidateService.saveCandidate(candidate);

        assertEquals(candidate.getName(), savedCandidate.getName());
        assertEquals(candidate.getEmail(), savedCandidate.getEmail());
    }

    @Test
    void getCandidateById_ShouldReturnCandidate() {
        Candidate candidate = new Candidate();
        candidate.setName("John Doe");
        candidate.setEmail("john.doe@example.com");
        candidate = candidateService.saveCandidate(candidate);

        Candidate foundCandidate = candidateService.getCandidateById(candidate.getId());

        assertEquals(candidate.getId(), foundCandidate.getId());
    }

    @Test
    void getCandidateById_ShouldReturnNull() {
        Candidate foundCandidate = candidateService.getCandidateById(1L);

        assertNull(foundCandidate);
    }

    @Test
    void getAllCandidates_ShouldReturnListOfCandidates() {
        Candidate candidate1 = new Candidate();
        candidate1.setName("John Doe");
        candidate1.setEmail("john.doe@example.com");
        Candidate candidate2 = new Candidate();
        candidate2.setName("Jane Doe");
        candidate2.setEmail("jane.doe@example.com");
        candidateService.saveCandidate(candidate1);
        candidateService.saveCandidate(candidate2);

        List<Candidate> allCandidates = candidateService.getAllCandidates();

        assertEquals(2, allCandidates.size());
    }

    @Test
    void updateCandidate_ShouldReturnUpdatedCandidate() {
        Candidate candidate = new Candidate();
        candidate.setName("John Doe");
        candidate.setEmail("john.doe@example.com");
        candidate = candidateService.saveCandidate(candidate);

        candidate.setName("John Updated");
        Candidate updatedCandidate = candidateService.updateCandidate(candidate.getId(), candidate);

        assertEquals("John Updated", updatedCandidate.getName());
    }

    @Test
    void updateCandidate_ShouldReturnNull() {
        Candidate candidate = new Candidate();
        candidate.setName("John Doe");
        candidate.setEmail("john.doe@example.com");

        Candidate updatedCandidate = candidateService.updateCandidate(1L, candidate);

        assertNull(updatedCandidate);
    }

    @Test
    void deleteCandidate_ShouldRemoveCandidate() {
        Candidate candidate = new Candidate();
        candidate.setName("John Doe");
        candidate.setEmail("john.doe@example.com");
        candidate = candidateService.saveCandidate(candidate);

        candidateService.deleteCandidate(candidate.getId());

        assertTrue(candidateRepository.findById(candidate.getId()).isEmpty());
    }
}
