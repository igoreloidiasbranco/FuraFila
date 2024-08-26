package br.com.furafila.domain.reserva.validacoes.agendamento;

import br.com.furafila.domain.reserva.ReservaDTO;
import br.com.furafila.domain.restaurante.RestauranteRepository;
import br.com.furafila.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorRestauranteAtivo implements ValidadorDeReservas{

    @Autowired
    private RestauranteRepository restauranteRepository;


    public void validar(ReservaDTO reservaDTO) {
        // escolha do restaurante opcional
        if (reservaDTO.idRestaurante() == null) {
            return;
        }

        var restauranteEstaAtivo = restauranteRepository.findAtivoById(reservaDTO.idRestaurante());

        if (!restauranteEstaAtivo) {
            throw new ValidacaoException("Este restaurante está inativo");
        }
    }
}
