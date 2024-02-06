package br.com.furafila.restaurante;

import br.com.furafila.endereco.EnderecoDTO;


public record ListagemRestaurantesDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        Especialidade especialidade,
        EnderecoDTO endereco) {

    public ListagemRestaurantesDTO(Restaurante restaurante) {
        this(restaurante.getId(), restaurante.getNome(), restaurante.getEmail(), restaurante.getTelefone(),
                restaurante.getEspecialidade(),
                new EnderecoDTO(
                        restaurante.getEndereco().getLogradouro(),
                        restaurante.getEndereco().getNumero(),
                        restaurante.getEndereco().getComplemento(),
                        restaurante.getEndereco().getBairro(),
                        restaurante.getEndereco().getCep(),
                        restaurante.getEndereco().getCidade(),
                        restaurante.getEndereco().getUf()
                )
        );
    }


}
