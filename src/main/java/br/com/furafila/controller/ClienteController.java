package br.com.furafila.controller;

import br.com.furafila.cliente.Cliente;
import br.com.furafila.cliente.ClienteDTO;
import br.com.furafila.cliente.ClienteRepository;
import br.com.furafila.cliente.ListagemClientesDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ListagemClientesDTO> listar () {
        return repository.findAll().stream().map(ListagemClientesDTO::new).toList();
    }
}
