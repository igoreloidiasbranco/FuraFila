package br.com.furafila.domain.reserva.validacoes.agendamento;

import br.com.furafila.domain.reserva.ReservaDTO;
import br.com.furafila.domain.reserva.ReservaRepository;
import br.com.furafila.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteSemOutraReservaNoDia implements ValidadorDeReservas {

    @Autowired
    private ReservaRepository reservaRepository;

    public void validar(ReservaDTO reservaDTO) {
        var primeiroHorario = reservaDTO.data().withHour(11);
        var ultimoHorario = reservaDTO.data().withHour(22);
        var clientePossuiOutraReservaNoDia = reservaRepository.existsByClienteIdAndDataBetween(reservaDTO.idCliente(),
                primeiroHorario, ultimoHorario);
        if (clientePossuiOutraReservaNoDia) {
            throw new ValidacaoException("Cliente já possui uma reserva para esse dia");
        }
    }
}
