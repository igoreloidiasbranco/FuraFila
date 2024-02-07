package br.com.furafila.controller;

import br.com.furafila.restaurante.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


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
        return repository.findAllByAtivoTrue(paginacao).map(ListagemRestaurantesDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarRestauranteDTO dadosAtualizarRestauranteDTO) {
        var restaurante = repository.getReferenceById(dadosAtualizarRestauranteDTO.id());
        restaurante.atualizarRestaurante(dadosAtualizarRestauranteDTO);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void desativar(@PathVariable Long id) {
        var restaurante = repository.getReferenceById(id);
        restaurante.desativar();
    }
}
