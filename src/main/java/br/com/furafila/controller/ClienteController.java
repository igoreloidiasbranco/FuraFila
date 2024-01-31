package br.com.furafila.controller;

import br.com.furafila.cliente.Cliente;
import br.com.furafila.cliente.ClienteDTO;
import br.com.furafila.cliente.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
