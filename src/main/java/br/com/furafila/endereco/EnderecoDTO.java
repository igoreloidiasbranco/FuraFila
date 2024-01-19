package br.com.furafila.endereco;

public record EnderecoDTO(String logradouro, String numero, String complemento,
                          String bairro, String cep, String cidade, String uf) {

}
