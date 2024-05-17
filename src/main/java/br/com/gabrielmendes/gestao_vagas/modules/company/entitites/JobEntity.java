package br.com.gabrielmendes.gestao_vagas.modules.company.entitites;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "job")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  public UUID id;

  @Schema(example = "Vaga para design")
  public String description;

  @Schema(example = "GymPass, Plano de saúde")
  public String benefits;
  
  @NotBlank(message = "esse campo é obrigatório")
  @Schema(example = "SENIOR")
  public String level;

  @ManyToOne()
  @JoinColumn(name = "company_id", insertable = false, updatable = false)
  private CompanyEntity companyEntity;

  @Column(name = "company_id")
  private UUID companyId;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
