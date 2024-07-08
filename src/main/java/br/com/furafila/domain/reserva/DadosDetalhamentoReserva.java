package br.com.furafila.domain.reserva;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoReserva(
        Long id,
        Long idRestaurante,
        Long idCliente,
        Integer qtde,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data
) {
}
