package br.com.gabrielmendes.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileCandidateResponseDTO {

  
  @Schema(example = "Desenvolvedor Jav")
  private String description;
  
  @Schema(example = "João")
  private String name;
  
  @Schema(example = "joao")
  private String username;
  
  @Schema(example = "joao@gmail.com")
  private String email;
  
  private UUID id;
}
