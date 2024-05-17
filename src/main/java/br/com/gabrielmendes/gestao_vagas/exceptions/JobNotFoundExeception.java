package br.com.gabrielmendes.gestao_vagas.exceptions;

public class JobNotFoundExeception extends RuntimeException {
  public JobNotFoundExeception() {
    super("Job not found");
  }
}
