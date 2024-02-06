package br.com.furafila.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    String logradouro;
    String numero;
    String complemento;
    String bairro;
    String cep;
    String cidade;
    String uf;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.logradouro();
        this.numero = enderecoDTO.numero();
        this.complemento = enderecoDTO.complemento();
        this.bairro = enderecoDTO.bairro();
        this.cep = enderecoDTO.cep();
        this.cidade = enderecoDTO.cidade();
        this.uf = enderecoDTO.uf();
    }

    public void atualizarEndereco(EnderecoDTO atualizarEnderecoDTO) {
        if(atualizarEnderecoDTO.logradouro() != null) {
            this.logradouro = atualizarEnderecoDTO.logradouro();
        }
        if(atualizarEnderecoDTO.numero() != null) {
            this.numero = atualizarEnderecoDTO.numero();
        }
        if(atualizarEnderecoDTO.complemento() != null) {
            this.complemento = atualizarEnderecoDTO.complemento();
        }
        if(atualizarEnderecoDTO.bairro() != null) {
            this.bairro = atualizarEnderecoDTO.bairro();
        }
        if(atualizarEnderecoDTO.cep() != null) {
            this.cep = atualizarEnderecoDTO.cep();
        }
        if(atualizarEnderecoDTO.cidade() != null) {
            this.cidade = atualizarEnderecoDTO.cidade();
        }
        if(atualizarEnderecoDTO.uf() != null) {
            this.uf = atualizarEnderecoDTO.uf();
        }
    }
}
