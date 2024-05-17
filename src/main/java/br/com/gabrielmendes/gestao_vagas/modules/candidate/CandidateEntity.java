package br.com.gabrielmendes.gestao_vagas.modules.candidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "José Silva", requiredMode = RequiredMode.REQUIRED, description = "Nome do cadidato")
  private String name;

  @NotBlank()
  @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaços vazios")
  @Schema(example = "josesilva", requiredMode = RequiredMode.REQUIRED, description = "username do cadidato")
  private String username;

  @Email(message = "O campo email deve possuir um formato válido")
  @Schema(example = "jose@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "E-mail do cadidato")
  private String email;

  @Length(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
  @Schema(
    example = "admin@1234",
    minLength = 6,
    maxLength = 100,
    requiredMode = RequiredMode.REQUIRED,
    description = "Senha do cadidato"
  )
  private String password;

  @Schema(
    example = "Desenvolvedor Web",
    requiredMode = RequiredMode.REQUIRED,
    description = "Breve descrição do cadidato"
  )
  private String description;

  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
