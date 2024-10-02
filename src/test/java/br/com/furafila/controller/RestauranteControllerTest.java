package br.com.furafila.controller;


import br.com.furafila.domain.endereco.Endereco;
import br.com.furafila.domain.endereco.EnderecoDTO;
import br.com.furafila.domain.restaurante.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class RestauranteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<RestauranteDTO> restauranteDTOJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoRestaurante> dadosDetalhamentoRestauranteJson;

    @MockBean
    private RestauranteRepository restauranteRepository;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas ")
        //@WithMockUser  se quiser mockar um usuário caso utilize token para logar
    void cadastrar_cenario1() throws Exception {
        var response = mvc.perform(post("/restaurantes")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão válidas ")
        //@WithMockUser  se quiser mockar um usuário caso utilize token para logar
    void agendar_cenario2() throws Exception {

        var restauranteDTO = new RestauranteDTO("Restaurante", "79876545631", "test@restaurante.com.br", "65431321", Especialidade.JAPONESA, 50, dadosEndereco());

        when(restauranteRepository.save(any())).thenReturn(new Restaurante(restauranteDTO));

        var response = mvc
                .perform(post("/restaurantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(restauranteDTOJson.write(restauranteDTO).getJson()))
                .andReturn().getResponse();

        var dadosDetalhamentoRestaurante = new DadosDetalhamentoRestaurante(null, restauranteDTO.nome(),
                restauranteDTO.cnpj(), restauranteDTO.email(), restauranteDTO.telefone(), restauranteDTO.especialidade(),
                restauranteDTO.capacidade(), new Endereco(restauranteDTO.endereco()));


        var jsonEsperado = dadosDetalhamentoRestauranteJson.write(
                dadosDetalhamentoRestaurante).getJson();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private EnderecoDTO dadosEndereco() {
        return new EnderecoDTO(
                "Rua Teste", "465", "comp. teste" , "bairro Teste", "12345678", "Cidade", "SP"
        );
    }
}
