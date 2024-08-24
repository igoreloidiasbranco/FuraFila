package br.com.furafila.domain.reserva;

import br.com.furafila.domain.restaurante.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservaDTO(

        Long idRestaurante,

        @NotNull
        Long idCliente,

        @NotNull
        @Min(value = 1, message = "A quantidade de pessoas para a reserva não pode ser menor do que 1")
        Integer qtde,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,

        Especialidade especialidade
) {
}
