package br.com.furafila.domain.reserva;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservaDTO(

        Long idRestaurante,

        @NotNull
        Long idCliente,

        @NotNull
        @Future
        LocalDateTime data
) {
}
