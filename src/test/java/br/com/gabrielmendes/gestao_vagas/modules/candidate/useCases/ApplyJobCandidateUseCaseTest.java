package br.com.gabrielmendes.gestao_vagas.modules.candidate.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gabrielmendes.gestao_vagas.exceptions.JobNotFoundExeception;
import br.com.gabrielmendes.gestao_vagas.exceptions.UserNotFoundExeception;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.UseCases.ApplyJobCandidateUseCase;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.gabrielmendes.gestao_vagas.modules.company.entitites.JobEntity;
import br.com.gabrielmendes.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

  @InjectMocks
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @Mock
  private CandidateRepository candidateRepository;

  @Mock
  private JobRepository jobRepository;
  
  @Mock
  private ApplyJobRepository applyJobRepository;

  @Test
  @DisplayName("Should not be able to apply job with candidate not found")
  public void should_not_be_able_to_apply_job_with_candidate_not_found() {
    try {
      applyJobCandidateUseCase.execute(null, null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(UserNotFoundExeception.class);
    }
  }

  @Test
  @DisplayName("Should not be able to apply job with job not found")
  public void should_not_be_able_to_apply_job_with_job_not_found() {
    var idCandidate = UUID.randomUUID();

    var candidate = new CandidateEntity();
    candidate.setId(idCandidate);

    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

    try {
      applyJobCandidateUseCase.execute(idCandidate, null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(JobNotFoundExeception.class);
    }
  }

  @Test
  public void shoud_be_able_to_to_create_a_new_apply_job() {
    var idCandidate = UUID.randomUUID();
    var idJob = UUID.randomUUID();
  
    var applyJob = ApplyJobEntity.builder()
      .candidateId(idCandidate)
      .jobId(idJob)
      .build();
    
    var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
    when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

    when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

    var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

    assertThat(result).hasFieldOrProperty("id");
    assertNotNull(result.getId());
  }
}
