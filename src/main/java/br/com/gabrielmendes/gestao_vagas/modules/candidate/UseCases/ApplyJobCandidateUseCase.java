package br.com.gabrielmendes.gestao_vagas.modules.candidate.UseCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielmendes.gestao_vagas.exceptions.JobNotFoundExeception;
import br.com.gabrielmendes.gestao_vagas.exceptions.UserNotFoundExeception;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.gabrielmendes.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private ApplyJobRepository applyJobRepository;

  public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
    this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
          throw new UserNotFoundExeception();
        });

    this.jobRepository.findById(idJob)
        .orElseThrow(() -> {
          throw new JobNotFoundExeception();
        });

    var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob)
        .build();

    applyJob = this.applyJobRepository.save(applyJob);

    return applyJob;
  }
}
