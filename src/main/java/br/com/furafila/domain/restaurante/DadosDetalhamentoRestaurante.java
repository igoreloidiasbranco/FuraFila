package br.com.furafila.domain.restaurante;

import br.com.furafila.domain.endereco.Endereco;

public record DadosDetalhamentoRestaurante(
        Long id,
        String nome,
        String cnpj,
        String email,
        String telefone,
        Especialidade especialidade,
        Integer capacidade,
        Endereco endereco) {

    public DadosDetalhamentoRestaurante(Restaurante restaurante){
        this(restaurante.getId(), restaurante.getNome(), restaurante.getCnpj(), restaurante.getEmail(),
                restaurante.getTelefone(), restaurante.getEspecialidade(), restaurante.getCapacidade(), restaurante.getEndereco());
    }
}
