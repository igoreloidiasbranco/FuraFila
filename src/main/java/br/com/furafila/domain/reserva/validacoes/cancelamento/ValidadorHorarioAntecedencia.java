package br.com.furafila.domain.reserva.validacoes.cancelamento;

import br.com.furafila.domain.reserva.DadosCancelamentoReserva;
import br.com.furafila.domain.reserva.ReservaRepository;
import br.com.furafila.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeReservas {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public void validar(DadosCancelamentoReserva dadosCancelamentoReserva) {
        var reserva = reservaRepository.getReferenceById(dadosCancelamentoReserva.idReserva());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, reserva.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Reserva somente pode ser cancelada com antecedência mínima de 24h!");
        }

    }
}
