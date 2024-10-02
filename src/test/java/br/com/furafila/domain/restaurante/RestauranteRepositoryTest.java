package br.com.furafila.domain.restaurante;


import br.com.furafila.domain.cliente.Cliente;
import br.com.furafila.domain.cliente.ClienteDTO;
import br.com.furafila.domain.endereco.EnderecoDTO;
import br.com.furafila.domain.reserva.Reserva;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class RestauranteRepositoryTest {

    @Autowired
    RestauranteRepository restauranteRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Deve devolver um restaurante aleatório passando especialidade e data futura")
    void escolherRestauranteAleatorioNaDataCenario1() {

        //given ou arrange
        var dataFutura = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).atTime(12,00);
        var cliente = cadastrarCliente("Nome do Cliente1", "23222222222", "888388888", "twst@test.com", "22041987");
        var restaurante = cadastrarRestaurante("Nome do Restaurante1", "9999899999", "tsyt@testerest.com", "74777777", Especialidade.JAPONESA,100);

        //when ou act
        var restauranteAleatorio = restauranteRepository.escolherRestauranteAleatorioNaData(restaurante.getEspecialidade(),dataFutura);

        //then ou assert
        assertThat(restauranteAleatorio).isEqualTo(restaurante);
    }


    private void cadastrarReserva(Restaurante restaurante, Cliente cliente, Integer qtde, LocalDateTime data) {
        testEntityManager.persist(new Reserva(null, restaurante, cliente, qtde, data, null));
    }

    private Restaurante cadastrarRestaurante(String nome, String cnpj, String email, String telefone,
                                             Especialidade especialidade, Integer capacidade) {
        var restaurante = new Restaurante(restauranteDTO(nome, cnpj, email, telefone, especialidade, capacidade));
        testEntityManager.persist(restaurante);
        return restaurante;
    }

    private Cliente cadastrarCliente(String nome, String cpf, String telefone, String email, String datanascimento) {
        var cliente = new Cliente(clienteDTO(nome, cpf, telefone, email, datanascimento));
        testEntityManager.persist(cliente);
        return cliente;
    }

    private RestauranteDTO restauranteDTO(String nome, String cnpj, String email, String telefone,
                                          Especialidade especialidade, Integer capacidade) {
        return new RestauranteDTO(
                nome,
                cnpj,
                email,
                telefone,
                especialidade,
                capacidade,
                enderecoDTO()
        );
    }

    private ClienteDTO clienteDTO(String nome, String cpf, String telefone, String email, String datanascimento) {
        return new ClienteDTO(
                nome,
                cpf,
                telefone,
                email,
                datanascimento,
                enderecoDTO()

        );
    }

    private EnderecoDTO enderecoDTO() {
        return new EnderecoDTO(
                "rua xpto",
                "321",
                "blocoA",
                "Bairro x",
                "00000000",
                "Cidade x",
                "SP"
        );
    }
}