package br.com.furafila.domain.cliente;

import br.com.furafila.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String datanascimento;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Cliente(ClienteDTO clienteDTO) {
        this.nome = clienteDTO.nome();
        this.cpf = clienteDTO.cpf();
        this.telefone = clienteDTO.telefone();
        this.email = clienteDTO.email();
        this.datanascimento = clienteDTO.datanascimento();
        this.endereco = new Endereco(clienteDTO.endereco());
        this.ativo = true;
    }

    public void atualizarCliente(DadosAtualizarClienteDTO dadosAtualizarClienteDTO) {
        if(dadosAtualizarClienteDTO.telefone() != null) {
            this.telefone = dadosAtualizarClienteDTO.telefone();
        }
        if(dadosAtualizarClienteDTO.email() != null) {
            this.email = dadosAtualizarClienteDTO.email();
        }

        if(dadosAtualizarClienteDTO.endereco() != null) {
            this.endereco.atualizarEndereco(dadosAtualizarClienteDTO.endereco());
        }
    }

    public void desativar() {
        this.ativo = false;
    }
}
