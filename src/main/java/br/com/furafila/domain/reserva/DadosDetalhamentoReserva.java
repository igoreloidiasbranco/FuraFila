package br.com.furafila.domain.reserva;

import java.time.LocalDateTime;

public record DadosDetalhamentoReserva(Long id, Long idRestaurante, Long idCliente, LocalDateTime data) {
}
