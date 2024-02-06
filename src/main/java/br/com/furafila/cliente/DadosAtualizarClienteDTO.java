package br.com.furafila.cliente;

import br.com.furafila.endereco.EnderecoDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarClienteDTO(
        @NotNull
        Long id,
        String telefone,
        @Email
        String email,
        EnderecoDTO endereco) {

}
