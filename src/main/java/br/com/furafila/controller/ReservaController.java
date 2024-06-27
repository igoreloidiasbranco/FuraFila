package br.com.furafila.controller;

import br.com.furafila.domain.reserva.DadosDetalhamentoReserva;
import br.com.furafila.domain.reserva.ReservaDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid ReservaDTO reservaDTO) {
        System.out.println(reservaDTO);
        return ResponseEntity.ok(new DadosDetalhamentoReserva(null, null, null, null));
    }
}
