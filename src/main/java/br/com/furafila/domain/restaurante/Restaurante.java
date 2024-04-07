package br.com.furafila.domain.restaurante;

import br.com.furafila.domain.endereco.Endereco;
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
    private String cnpj;
    private String email;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    private Integer capacidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Restaurante(RestauranteDTO dados) {
        this.nome = dados.nome();
        this.cnpj = dados.cnpj();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.capacidade = dados.capacidade();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }

    public void atualizarRestaurante(DadosAtualizarRestauranteDTO dadosAtualizarRestauranteDTO) {
        if(dadosAtualizarRestauranteDTO.email() != null) {
            this.email = dadosAtualizarRestauranteDTO.email();
        }
        if(dadosAtualizarRestauranteDTO.telefone() != null) {
            this.telefone = dadosAtualizarRestauranteDTO.telefone();
        }
        if(dadosAtualizarRestauranteDTO.especialidade() != null) {
            this.especialidade = dadosAtualizarRestauranteDTO.especialidade();
        }
        if(dadosAtualizarRestauranteDTO.capacidade() != null) {
            this.capacidade = dadosAtualizarRestauranteDTO.capacidade();
        }

        if(dadosAtualizarRestauranteDTO.endereco() != null) {
            this.endereco.atualizarEndereco(dadosAtualizarRestauranteDTO.endereco());
        }
    }

    public void desativar() {
        this.ativo = false;
    }
}
