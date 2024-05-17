package br.com.gabrielmendes.gestao_vagas.exceptions;

public class CompanyNotFoundExeception extends RuntimeException {
  public CompanyNotFoundExeception() {
    super("Company not found");
  }
}
