package br.com.furafila.domain.restaurante;

import br.com.furafila.domain.endereco.EnderecoDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarRestauranteDTO(
        @NotNull
        Long id,
        @Email
        String email,
        String telefone,
        Especialidade especialidade,
        Integer capacidade,
        EnderecoDTO endereco) {
}
