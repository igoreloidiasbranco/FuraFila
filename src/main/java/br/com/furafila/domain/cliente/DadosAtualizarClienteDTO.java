package br.com.furafila.domain.cliente;

import br.com.furafila.domain.endereco.EnderecoDTO;
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
