package med.voll.api.domain.controller;

import med.voll.api.domain.medico.*;
import med.voll.api.domain.medico.endereco.DadosEndereco;
import med.voll.api.domain.medico.endereco.Endereco;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJson;

    @MockitoBean
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Retornar código HTTP 400 por falta de dados cadastrados")
    @WithMockUser
    void cadastrar_teste1() throws Exception {
        var response = mvc.perform(post("/medicos")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Retornar código HTTP 201")
    @WithMockUser
    void cadastrar_teste2() throws Exception {
        var dadosCadastro = new DadosCadastroMedico(
                "Jeff",
                "jeff@gmail.com",
                "11999992222",
                "333444",
                Especialidade.CARDIOLOGIA,
                dadosEndereco()
        );

        var dadosDetalhamento = new DadosDetalhamentoMedico(
                null,
                dadosCadastro.nome(),
                dadosCadastro.email(),
                dadosCadastro.crm(),
                dadosCadastro.telefone(),
                dadosCadastro.especialidade(),
                new Endereco(dadosCadastro.endereco())
        );

        when(medicoRepository.save(any())).thenReturn(new Medico(dadosCadastro));

        var response = mvc.perform(post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroMedicoJson.write(dadosCadastro).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoMedicoJson.write(dadosDetalhamento).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "Logradouro",
                "Jardim Orly",
                "11112222",
                "Sao Paulo",
                "SP",
                "Casa 01",
                "82"
        );
    }
}