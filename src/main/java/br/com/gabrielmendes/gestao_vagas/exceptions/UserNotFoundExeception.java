package br.com.gabrielmendes.gestao_vagas.exceptions;

public class UserNotFoundExeception extends RuntimeException {
  public UserNotFoundExeception() {
    super("Usuário not found");
  }
}

