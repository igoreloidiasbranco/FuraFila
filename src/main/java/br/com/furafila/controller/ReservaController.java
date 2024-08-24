package br.com.furafila.controller;

import br.com.furafila.domain.reserva.DadosCancelamentoReserva;
import br.com.furafila.domain.reserva.ReservaDTO;
import br.com.furafila.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoReserva dadosCancelamentoReservaDTO) {
        reservaService.cancelar(dadosCancelamentoReservaDTO);
        return ResponseEntity.noContent().build();
    }

}
