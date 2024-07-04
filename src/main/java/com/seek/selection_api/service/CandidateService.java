package com.seek.selection_api.service;

import com.seek.selection_api.persistence.entity.Candidate;
import com.seek.selection_api.persistence.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate updateCandidate(Long id, Candidate candidate) {
        if (candidateRepository.existsById(id)) {
            candidate.setId(id);
            return candidateRepository.save(candidate);
        }
        return null;
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}
