package br.com.furafila.service;

import br.com.furafila.domain.cliente.ClienteRepository;
import br.com.furafila.domain.reserva.Reserva;
import br.com.furafila.domain.reserva.ReservaDTO;
import br.com.furafila.domain.reserva.ReservaRepository;
import br.com.furafila.domain.restaurante.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void reservar(ReservaDTO reservaDTO) {

        var restaurante = restauranteRepository.findById(reservaDTO.idRestaurante()).get();
        var cliente = clienteRepository.findById(reservaDTO.idCliente()).get();
        var reserva = new Reserva(null, restaurante, cliente, reservaDTO.qtde(), reservaDTO.data());
        reservaRepository.save(reserva);
    }
}
