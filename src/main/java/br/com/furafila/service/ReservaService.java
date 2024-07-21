package br.com.furafila.service;

import br.com.furafila.domain.cliente.ClienteRepository;
import br.com.furafila.domain.reserva.DadosDetalhamentoReserva;
import br.com.furafila.domain.reserva.Reserva;
import br.com.furafila.domain.reserva.ReservaDTO;
import br.com.furafila.domain.reserva.ReservaRepository;
import br.com.furafila.domain.reserva.validacoes.ValidadorDeReservas;
import br.com.furafila.domain.restaurante.Restaurante;
import br.com.furafila.domain.restaurante.RestauranteRepository;
import br.com.furafila.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private List<ValidadorDeReservas> validadores;

    public DadosDetalhamentoReserva reservar(ReservaDTO reservaDTO) {

        if(!clienteRepository.existsById(reservaDTO.idCliente())) {
            throw new ValidacaoException("Id do cliente informado não existe");
        }

        if(reservaDTO.idRestaurante() != null && !restauranteRepository.existsById(reservaDTO.idRestaurante())) {
            throw new ValidacaoException("Id do restaurante informado não existe");
        }


        validadores.forEach(v -> v.validar(reservaDTO));

        var restaurante = escolherRestaurante(reservaDTO);
        if (restaurante == null) {
            throw new ValidacaoException("Não há restaurante disponível no momento");
        }
        var cliente = clienteRepository.getReferenceById(reservaDTO.idCliente());
        var reserva = new Reserva(null, restaurante, cliente, reservaDTO.qtde(), reservaDTO.data());
        reservaRepository.save(reserva);

        return new DadosDetalhamentoReserva(reserva);
    }

    private Restaurante escolherRestaurante(ReservaDTO reservaDTO) {

        if (reservaDTO.idRestaurante() != null) {
            return restauranteRepository.getReferenceById(reservaDTO.idRestaurante());
        }

        if (reservaDTO.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o restaurante não for escolhido.");
        }

        return restauranteRepository.escolherRestauranteAleatorioNaData(reservaDTO.especialidade(), reservaDTO.data());

    }
}
