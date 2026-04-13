package med.voll.api.paciente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.medico.endereco.DadosEndereco;
import med.voll.api.paciente.endereco.DadosEnderecoPaciente;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,

        @Pattern(regexp="\\d{11}")
        String telefone,
        
        DadosEnderecoPaciente endereco
) {
}
