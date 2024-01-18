package br.com.furafila.controller;

import br.com.furafila.restaurante.Restaurante;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {
    @PostMapping
    public void cadastrar(@RequestBody Restaurante dadosRestaurante) {
        System.out.println(dadosRestaurante);
    }
}
