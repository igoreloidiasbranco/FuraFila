package br.com.furafila.controller;

import br.com.furafila.domain.reserva.DadosDetalhamentoReserva;
import br.com.furafila.domain.reserva.ReservaDTO;
import br.com.furafila.service.ReservaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid ReservaDTO reservaDTO) {

       var detalhamentoReservaDTO = reservaService.reservar(reservaDTO);
        return ResponseEntity.ok(detalhamentoReservaDTO);
    }
}
