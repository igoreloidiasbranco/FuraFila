package br.com.furafila.cliente;

import br.com.furafila.endereco.EnderecoDTO;
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
