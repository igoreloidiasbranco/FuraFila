package br.com.furafila.domain.cliente;

import br.com.furafila.domain.endereco.EnderecoDTO;

public record ListagemClientesDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        EnderecoDTO endereco) {

    public ListagemClientesDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                new EnderecoDTO(
                        cliente.getEndereco().getLogradouro(),
                        cliente.getEndereco().getNumero(),
                        cliente.getEndereco().getComplemento(),
                        cliente.getEndereco().getBairro(),
                        cliente.getEndereco().getCep(),
                        cliente.getEndereco().getCidade(),
                        cliente.getEndereco().getUf()
                )
        );

    }


}
