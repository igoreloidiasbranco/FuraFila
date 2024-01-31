package br.com.furafila.cliente;

import br.com.furafila.endereco.EnderecoDTO;

public record ClienteDTO(String nome, String cpf, String telefone, String email,
                         String datanascimento, EnderecoDTO endereco) {
}
