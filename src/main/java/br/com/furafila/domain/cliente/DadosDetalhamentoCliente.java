package br.com.furafila.domain.cliente;

import br.com.furafila.domain.endereco.Endereco;

public record DadosDetalhamentoCliente(
        Long id,
        String nome,
        String cpf,
        String telefone,
        String email,
        String datanascimento,
        Endereco endereco
) {
    public DadosDetalhamentoCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getCpf(), cliente.getTelefone(), cliente.getEmail(),
                cliente.getDatanascimento(), cliente.getEndereco());
    }
}
