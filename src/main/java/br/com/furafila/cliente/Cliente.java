package br.com.furafila.cliente;

import br.com.furafila.endereco.Endereco;
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

    public Cliente(ClienteDTO clienteDTO) {
        this.nome = clienteDTO.nome();
        this.cpf = clienteDTO.cpf();
        this.telefone = clienteDTO.telefone();
        this.email = clienteDTO.email();
        this.datanascimento = clienteDTO.datanascimento();
        this.endereco = new Endereco(clienteDTO.endereco());
    }
}
