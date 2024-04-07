package br.com.furafila.controller;

import br.com.furafila.domain.restaurante.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("restaurantes")
public class RestauranteController {
    @Autowired
    RestauranteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid RestauranteDTO restauranteDTO, UriComponentsBuilder uriBuilder) {

        var restaurante = new Restaurante(restauranteDTO);
        repository.save(restaurante);
        var uri = uriBuilder.path("/restaurantes/{id}").buildAndExpand(restaurante.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoRestaurante(restaurante));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemRestaurantesDTO>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(ListagemRestaurantesDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var restaurante = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoRestaurante(restaurante));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarRestauranteDTO dadosAtualizarRestauranteDTO) {
        var restaurante = repository.getReferenceById(dadosAtualizarRestauranteDTO.id());
        restaurante.atualizarRestaurante(dadosAtualizarRestauranteDTO);
        return ResponseEntity.ok(new DadosDetalhamentoRestaurante(restaurante));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity desativar(@PathVariable Long id) {
        var restaurante = repository.getReferenceById(id);
        restaurante.desativar();

        return ResponseEntity.noContent().build();
    }
}
