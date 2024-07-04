package com.seek.selection_api;

import com.seek.selection_api.service.CandidateService;

import com.seek.selection_api.persistence.entity.Candidate;
import com.seek.selection_api.persistence.repository.CandidateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class CandidateServiceTest {

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateService candidateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCandidate_ShouldReturnSavedCandidate() {
        Candidate candidate = new Candidate();
        when(candidateRepository.save(candidate)).thenReturn(candidate);

        Candidate savedCandidate = candidateService.saveCandidate(candidate);

        assertEquals(candidate, savedCandidate);
        verify(candidateRepository, times(1)).save(candidate);
    }

    @Test
    void getCandidateById_ShouldReturnCandidate() {
        Candidate candidate = new Candidate();
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));

        Candidate foundCandidate = candidateService.getCandidateById(1L);

        assertEquals(candidate, foundCandidate);
        verify(candidateRepository, times(1)).findById(1L);
    }

    @Test
    void getCandidateById_ShouldReturnNull() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.empty());

        Candidate foundCandidate = candidateService.getCandidateById(1L);

        assertNull(foundCandidate);
        verify(candidateRepository, times(1)).findById(1L);
    }

    @Test
    void getAllCandidates_ShouldReturnListOfCandidates() {
        Candidate candidate1 = new Candidate();
        Candidate candidate2 = new Candidate();
        List<Candidate> candidates = Arrays.asList(candidate1, candidate2);
        when(candidateRepository.findAll()).thenReturn(candidates);

        List<Candidate> allCandidates = candidateService.getAllCandidates();

        assertEquals(candidates, allCandidates);
        verify(candidateRepository, times(1)).findAll();
    }

    @Test
    void updateCandidate_ShouldReturnUpdatedCandidate() {
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        when(candidateRepository.existsById(1L)).thenReturn(true);
        when(candidateRepository.save(candidate)).thenReturn(candidate);

        Candidate updatedCandidate = candidateService.updateCandidate(1L, candidate);

        assertEquals(candidate, updatedCandidate);
        verify(candidateRepository, times(1)).existsById(1L);
        verify(candidateRepository, times(1)).save(candidate);
    }

    @Test
    void updateCandidate_ShouldReturnNull() {
        Candidate candidate = new Candidate();
        when(candidateRepository.existsById(1L)).thenReturn(false);

        Candidate updatedCandidate = candidateService.updateCandidate(1L, candidate);

        assertNull(updatedCandidate);
        verify(candidateRepository, times(1)).existsById(1L);
        verify(candidateRepository, times(0)).save(candidate);
    }

    @Test
    void deleteCandidate_ShouldCallDeleteById() {
        candidateService.deleteCandidate(1L);

        verify(candidateRepository, times(1)).deleteById(1L);
    }
}
