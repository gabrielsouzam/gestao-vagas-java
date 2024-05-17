package br.com.gabrielmendes.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessagaDTO {
  
  private String message;
  private String field;
}
