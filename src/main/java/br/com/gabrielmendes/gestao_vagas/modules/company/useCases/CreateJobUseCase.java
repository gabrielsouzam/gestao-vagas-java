package br.com.gabrielmendes.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielmendes.gestao_vagas.exceptions.CompanyNotFoundExeception;
import br.com.gabrielmendes.gestao_vagas.modules.company.entitites.JobEntity;
import br.com.gabrielmendes.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.gabrielmendes.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {
  
  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private CompanyRepository companyRepository;

  public JobEntity execute(JobEntity jobEntity) {
    companyRepository.findById(jobEntity.getCompanyId())
      .orElseThrow(() -> {
        throw new CompanyNotFoundExeception();
      });

    return this.jobRepository.save(jobEntity);
  }
}
