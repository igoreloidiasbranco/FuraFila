package br.com.furafila.controller;

import br.com.furafila.restaurante.ListagemRestaurantesDTO;
import br.com.furafila.restaurante.Restaurante;
import br.com.furafila.restaurante.RestauranteDTO;
import br.com.furafila.restaurante.RestauranteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public Page<ListagemRestaurantesDTO> listar(@PageableDefault(size = 5, sort = {"nome"} ) Pageable paginacao) {
        return repository.findAll(paginacao).map(ListagemRestaurantesDTO::new);
    }
}
