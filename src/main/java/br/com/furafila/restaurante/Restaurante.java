package br.com.furafila.restaurante;

import br.com.furafila.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "restaurantes")
@Entity(name = "Restaurante")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    private Integer capacidade;
    @Embedded
    private Endereco endereco;

    public Restaurante(RestauranteDTO dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.capacidade = dados.capacidade();
        this.endereco = new Endereco(dados.endereco());
    }
}
