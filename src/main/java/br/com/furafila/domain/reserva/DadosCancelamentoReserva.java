package br.com.furafila.domain.reserva;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoReserva(
        @NotNull
        Long idReserva,

        @NotNull
        MotivoCancelamento motivoCancelamento
) {
}
