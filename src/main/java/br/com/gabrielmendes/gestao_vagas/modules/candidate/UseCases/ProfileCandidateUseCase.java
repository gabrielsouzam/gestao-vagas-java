package br.com.gabrielmendes.gestao_vagas.modules.candidate.UseCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielmendes.gestao_vagas.exceptions.UserNotFoundExeception;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {
  
  @Autowired
  private CandidateRepository candidateRepository;

  public ProfileCandidateResponseDTO execute(UUID idCandidate) {
    var candidate = this.candidateRepository.findById(idCandidate)
      .orElseThrow(() -> {
        throw new UserNotFoundExeception();
      });

    var candidateDTO = ProfileCandidateResponseDTO.builder()
      .description(candidate.getDescription())
      .username(candidate.getUsername())
      .name(candidate.getName())
      .email(candidate.getEmail())
      .id(candidate.getId())
      .build();

    return candidateDTO;
  }
}
