package br.com.gabrielmendes.gestao_vagas.modules.candidate.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrielmendes.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.UseCases.ApplyJobCandidateUseCase;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.UseCases.CreateCandidateUseCase;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.UseCases.ListAllJobsByFilterUseCase;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.UseCases.ProfileCandidateUseCase;
import br.com.gabrielmendes.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.gabrielmendes.gestao_vagas.modules.company.entitites.JobEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "Informações do candidato")
public class CandidateController {
  
  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

  @Autowired
  private ApplyJobCandidateUseCase applyJobCandidateUseCase;

  @PostMapping("/")
  @Operation(
    summary = "Cadastro do candidato", 
    description = "Essa função é responsável por cadastrar um candidato"
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(schema = @Schema(implementation = CandidateEntity.class))
    }),
    @ApiResponse(responseCode = "400", description = "Usuário já existe")
  })
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = this.createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(
    summary = "Perfil do candidato", 
    description = "Essa função é responsável por buscar informações do perfil do usuário"
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
    }),
    @ApiResponse(responseCode = "400", description = "User not found")
  })
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> get(HttpServletRequest request) {
    
    var idCandidate = request.getAttribute("candidate_id");

    try {
      var profile = this.profileCandidateUseCase
      .execute(UUID.fromString(idCandidate.toString()));
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
  
  @GetMapping("/job")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(
    summary = "Listagem de vagas do candidato", 
    description = "Essa função é responsável por listar todas as vagas disponíveis, baseada em um filtro"
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
      @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
    })
  })
  @SecurityRequirement(name = "jwt_auth")
  public List<JobEntity> findJobByFilter(@RequestParam String filter) {
    return this.listAllJobsByFilterUseCase.execute(filter);
  }

  @PostMapping("/job/apply")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(
    summary = "Inscrição do candidato em uma vaga", 
    description = "Essa função é responsável por realizar a inscrição do candidato em alguma vaga"
  )
  @SecurityRequirement(name = "jwt_auth")
  public ResponseEntity<Object> applyJob(HttpServletRequest request, @RequestBody UUID jobId) {

    var idCandidate = request.getAttribute("candidate_id");

    try {
      var result = 
        this.applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()), jobId);
      
      return ResponseEntity.ok().body(result);
        
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
