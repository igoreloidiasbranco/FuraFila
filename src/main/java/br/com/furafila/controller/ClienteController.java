package br.com.furafila.controller;

import br.com.furafila.cliente.ClienteDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class ClienteController {
    @PostMapping
    public void cadastrar (@RequestBody ClienteDTO dadosClienteDTO) {
        System.out.println(dadosClienteDTO);
    }
}
