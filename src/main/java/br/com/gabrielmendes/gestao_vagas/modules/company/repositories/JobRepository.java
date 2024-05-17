package br.com.gabrielmendes.gestao_vagas.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielmendes.gestao_vagas.modules.company.entitites.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
  
  List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}
