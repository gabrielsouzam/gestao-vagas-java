package br.com.gabrielmendes.gestao_vagas.modules.company.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielmendes.gestao_vagas.modules.company.entitites.CompanyEntity;


public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
  Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
  Optional<CompanyEntity> findByUsername(String username);
}
