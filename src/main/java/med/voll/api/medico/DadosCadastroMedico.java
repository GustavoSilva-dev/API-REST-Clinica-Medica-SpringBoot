package med.voll.api.medico;

import med.voll.api.medico.endereco.DadosEndereco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEndereco endereco)  {
}
