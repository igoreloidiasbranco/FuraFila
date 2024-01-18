package br.com.furafila.restaurante;

import br.com.furafila.endereco.Endereco;

public record Restaurante(String nome, String email, Especialidade especialidade, Endereco endereco) {
}
