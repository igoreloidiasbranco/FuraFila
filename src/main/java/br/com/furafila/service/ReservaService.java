package br.com.furafila.service;

import br.com.furafila.domain.cliente.ClienteRepository;
import br.com.furafila.domain.reserva.*;
import br.com.furafila.domain.reserva.validacoes.agendamento.ValidadorDeReservas;
import br.com.furafila.domain.reserva.validacoes.cancelamento.ValidadorCancelamentoDeReservas;
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

    @Autowired
    private List<ValidadorCancelamentoDeReservas> validadoresCancelamento;

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
        var reserva = new Reserva(null, restaurante, cliente, reservaDTO.qtde(), reservaDTO.data(), null);
        reservaRepository.save(reserva);

        return new DadosDetalhamentoReserva(reserva);
    }

    public Restaurante escolherRestaurante(ReservaDTO reservaDTO) {

        if (reservaDTO.idRestaurante() != null) {
            return restauranteRepository.getReferenceById(reservaDTO.idRestaurante());
        }

        if (reservaDTO.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o restaurante não for escolhido.");
        }

        

        return restauranteRepository.escolherRestauranteAleatorioNaData(reservaDTO.especialidade(), reservaDTO.data());

    }

    public void cancelar(DadosCancelamentoReserva dadosCancelamentoReservaDTO) {
        if (!reservaRepository.existsById(dadosCancelamentoReservaDTO.idReserva())) {
            throw new ValidacaoException("Id da reserva informado não existe");
        }

        validadoresCancelamento.forEach(v -> v.validar(dadosCancelamentoReservaDTO));

        var reserva = reservaRepository.getReferenceById(dadosCancelamentoReservaDTO.idReserva());
        reserva.cancelar(dadosCancelamentoReservaDTO.motivoCancelamento());
    }
}
