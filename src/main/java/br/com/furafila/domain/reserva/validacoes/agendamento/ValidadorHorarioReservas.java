package br.com.furafila.domain.reserva.validacoes.agendamento;

import br.com.furafila.domain.reserva.ReservaDTO;
import br.com.furafila.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorHorarioReservas implements ValidadorDeReservas{

    public void validar(ReservaDTO reservaDTO) {

        var dataReserva = reservaDTO.data();
        var antesDasOnzeHoras = dataReserva.getHour() < 11;
        var depoisDasVinteDuasHoras = dataReserva.getHour() > 22;

        if (antesDasOnzeHoras || depoisDasVinteDuasHoras) {
            throw new ValidacaoException("Reserva fora do horário disponível");
        }
    }
}
