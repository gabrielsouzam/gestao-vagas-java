package br.com.gabrielmendes.gestao_vagas.modules.company.entitites;


import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "company")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  public UUID id;
  
  @NotBlank()
  @Pattern(regexp = "\\S+", message = "O campo username não deve conter espaços vazios")
  private String username;

  @Email(message = "O campo email deve possuir um formato válido")
  private String email;

  @Length(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
  private String password;

  public String website;
  public String name;
  public String description;

  @CreationTimestamp
  private LocalDateTime createdAt;
}

