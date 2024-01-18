package br.com.furafila.endereco;

public record Endereco(String logradouro, String numero, String complemento,
                       String bairro, String cep, String cidade, String uf) {

}
