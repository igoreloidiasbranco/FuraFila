package br.com.furafila.domain.reserva.validacoes;

import br.com.furafila.domain.reserva.ReservaDTO;
import br.com.furafila.domain.restaurante.Restaurante;
import br.com.furafila.domain.restaurante.RestauranteRepository;
import br.com.furafila.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCapacidadeRestaurante implements ValidadorDeReservas {

    @Autowired
    RestauranteRepository restauranteRepository;


    public void validar(ReservaDTO reservaDTO) {

        Restaurante restaurante;

        if (reservaDTO.idRestaurante() == null) {
            if (reservaDTO.especialidade() == null) {
                throw new ValidacaoException("Especialidade é obrigatória quando o restaurante não for escolhido.");

            } else {
                restaurante = restauranteRepository.escolherRestauranteAleatorioNaData(reservaDTO.especialidade(), reservaDTO.data());

            }
            reservaDTO = new ReservaDTO(restaurante.getId(), reservaDTO.idCliente(), reservaDTO.qtde(), reservaDTO.data(), reservaDTO.especialidade());

        }

        restaurante = restauranteRepository.getReferenceById(reservaDTO.idRestaurante());

        if (reservaDTO.qtde() > restaurante.getCapacidade()) {
            throw new ValidacaoException("Quantidade superior a capacidade de reservas disponíves do restaurante no momento");
        }

    }


}


