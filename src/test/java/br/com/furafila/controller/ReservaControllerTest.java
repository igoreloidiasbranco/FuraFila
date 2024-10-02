package br.com.furafila.controller;

import br.com.furafila.domain.reserva.DadosDetalhamentoReserva;
import br.com.furafila.domain.reserva.ReservaDTO;
import br.com.furafila.domain.restaurante.Especialidade;
import br.com.furafila.service.ReservaService;
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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ReservaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ReservaDTO> reservaDTOJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoReserva> dadosDetalhamentoReservaJson;

    @MockBean
    private ReservaService reservaService;

    @Test
    @DisplayName("Deveria devolver código http 400 quando informações estão inválidas ")
    //@WithMockUser  se quiser mockar um usuário caso utilize token para logar
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/reservas")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver código http 200 quando informações estão válidas ")
        //@WithMockUser  se quiser mockar um usuário caso utilize token para logar
    void agendar_cenario2() throws Exception {
        var data = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.JAPONESA;

        var dadosDetalhamento = new DadosDetalhamentoReserva( null, 1l, 1l, 2, data);
        when(reservaService.reservar(any())).thenReturn(dadosDetalhamento);

        var response = mvc
                .perform(post("/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reservaDTOJson.write(
                                new ReservaDTO(1l,1l, 2, data, especialidade)
                        ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonEsperado = dadosDetalhamentoReservaJson.write(
               dadosDetalhamento
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}