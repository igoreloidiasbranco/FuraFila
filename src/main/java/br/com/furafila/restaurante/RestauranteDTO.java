package br.com.furafila.restaurante;

import br.com.furafila.endereco.EnderecoDTO;

public record RestauranteDTO(String nome, String email, String telefone,
                             Especialidade especialidade, Integer capacidade, EnderecoDTO endereco) {
}
