package br.com.furafila.cliente;

import br.com.furafila.endereco.Endereco;
import jakarta.persistence.Embedded;

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
