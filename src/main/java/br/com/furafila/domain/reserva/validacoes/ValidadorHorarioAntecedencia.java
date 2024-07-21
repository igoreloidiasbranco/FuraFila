package br.com.furafila.domain.reserva.validacoes;

import br.com.furafila.domain.reserva.ReservaDTO;
import br.com.furafila.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorDeReservas {

    public void validar(ReservaDTO reservaDTO) {
        var dataConsulta = reservaDTO.data();
        var agora = LocalDateTime.now();

        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Reserva deve ser feita com antecedência mínima de 30 minutos");
        }
    }
}
