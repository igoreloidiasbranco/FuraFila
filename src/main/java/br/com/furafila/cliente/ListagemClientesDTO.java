package br.com.furafila.cliente;

import br.com.furafila.endereco.EnderecoDTO;

public record ListagemClientesDTO(
        String nome,
        String email,
        String telefone,
        EnderecoDTO endereco) {

    public ListagemClientesDTO(Cliente cliente) {
        this(
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
