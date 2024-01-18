package br.com.furafila.cliente;

import br.com.furafila.endereco.Endereco;

public record Cliente(String nome, String cpf, String telefone, String email,
                      String datanascimento, Endereco endereco) {
}
