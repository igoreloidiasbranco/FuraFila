package br.com.furafila.restaurante;

import br.com.furafila.endereco.Endereco;


public record ListagemRestaurantesDTO(
        String nome,
        String email,
        String telefone,
        Especialidade especialidade,
        Endereco endereco) {

    public ListagemRestaurantesDTO(Restaurante restaurante) {
        this(restaurante.getNome(), restaurante.getEmail(), restaurante.getTelefone(),
                restaurante.getEspecialidade(),
                restaurante.getEndereco());
    }


}
