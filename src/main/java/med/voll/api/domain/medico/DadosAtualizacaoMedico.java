package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.medico.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull
        Long id,

        @Pattern(regexp="\\d{11}")
        String telefone,

        DadosEndereco endereco,
        String nome
) {
}
