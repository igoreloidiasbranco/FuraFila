package br.com.furafila.domain.cliente;

import br.com.furafila.domain.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDTO(
        @NotBlank
        String nome,

        @NotBlank
        String cpf,

        @NotBlank
        String telefone,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String datanascimento,

        @NotNull
        @Valid
        EnderecoDTO endereco) {
}
