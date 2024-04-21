package br.com.furafila.controller;


import br.com.furafila.domain.usuario.DadosAutenticacaoDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dadosAutenticacaoDTO) {

        var token = new UsernamePasswordAuthenticationToken(dadosAutenticacaoDTO.login(), dadosAutenticacaoDTO.senha());
        var authentication = authenticationManager.authenticate(token);

        return ResponseEntity.ok().build();
    }
}
