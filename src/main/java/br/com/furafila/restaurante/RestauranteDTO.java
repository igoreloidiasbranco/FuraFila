package br.com.furafila.restaurante;

import br.com.furafila.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestauranteDTO(
        @NotBlank
        String nome,

        @NotBlank
        String cnpj,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotNull
        Especialidade especialidade,

        @NotNull
        Integer capacidade,

        @NotNull
        @Valid
        EnderecoDTO endereco) {
}
