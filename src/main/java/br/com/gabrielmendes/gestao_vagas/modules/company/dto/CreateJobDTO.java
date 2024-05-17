package br.com.gabrielmendes.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobDTO {
  
  @Schema(example = "Vaga para desenvolvedor java júnior", requiredMode = RequiredMode.REQUIRED)
  private String description;

  @Schema(example = "GymPass, Plano de saúde", requiredMode = RequiredMode.REQUIRED)
  private String benefits;

  @Schema(example = "JÚNIOR", requiredMode = RequiredMode.REQUIRED)
  private String level;


}
