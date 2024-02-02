package br.com.furafila.controller;

import br.com.furafila.cliente.Cliente;
import br.com.furafila.cliente.ClienteDTO;
import br.com.furafila.cliente.ClienteRepository;
import br.com.furafila.cliente.ListagemClientesDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return repository.findAll(paginacao).map(ListagemClientesDTO::new);
    }
}
