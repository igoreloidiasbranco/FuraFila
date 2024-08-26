package br.com.furafila.domain.reserva.validacoes.agendamento;

import br.com.furafila.domain.cliente.ClienteRepository;
import br.com.furafila.domain.reserva.ReservaDTO;
import br.com.furafila.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteAtivo implements ValidadorDeReservas{

    @Autowired
    private ClienteRepository clienteRepository;

    public void validar(ReservaDTO reservaDTO) {
        var clienteEstaAtivo = clienteRepository.findAtivoById(reservaDTO.idCliente());
        if (!clienteEstaAtivo) {
            throw new ValidacaoException("Este cliente está inativo");
        }
    }
}
