package br.com.furafila.controller;

import br.com.furafila.restaurante.Restaurante;
import br.com.furafila.restaurante.RestauranteDTO;
import br.com.furafila.restaurante.RestauranteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("restaurantes")
public class RestauranteController {
    @Autowired
    RestauranteRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid RestauranteDTO restauranteDTO) {
        repository.save(new Restaurante(restauranteDTO));
    }
}
