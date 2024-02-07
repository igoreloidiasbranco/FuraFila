package br.com.furafila.controller;

import br.com.furafila.cliente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("clientes")
public class ClienteController {
    @Autowired
    ClienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar (@RequestBody @Valid ClienteDTO clienteDTO) {

        repository.save(new Cliente(clienteDTO));
    }

    @GetMapping
    public Page<ListagemClientesDTO> listar (@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(ListagemClientesDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizarClienteDTO dadosAtualizarClienteDTO) {
        var cliente = repository.getReferenceById(dadosAtualizarClienteDTO.id());
        cliente.atualizarCliente(dadosAtualizarClienteDTO);
    }

    @DeleteMapping("{id}")
    @Transactional
    public void desativar(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);
        cliente.desativar();
    }
}
