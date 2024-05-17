package br.com.gabrielmendes.gestao_vagas.modules.candidate.UseCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielmendes.gestao_vagas.modules.company.entitites.JobEntity;
import br.com.gabrielmendes.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {
  
  @Autowired
  private JobRepository jobRepository;

  public List<JobEntity> execute(String filter) {
    return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);
  }
}
