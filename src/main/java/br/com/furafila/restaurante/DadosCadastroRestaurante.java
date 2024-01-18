package br.com.furafila.restaurante;

import br.com.furafila.endereco.DadosEndereco;

public record DadosCadastroRestaurante(String nome, String email, Especialidade especialidade, DadosEndereco endereco) {
}
