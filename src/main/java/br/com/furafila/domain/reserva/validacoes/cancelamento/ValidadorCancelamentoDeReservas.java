package br.com.furafila.domain.reserva.validacoes.cancelamento;

import br.com.furafila.domain.reserva.DadosCancelamentoReserva;

public interface ValidadorCancelamentoDeReservas {

    void validar(DadosCancelamentoReserva dadosCancelamentoReserva);
}
