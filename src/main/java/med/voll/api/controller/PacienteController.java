package med.voll.api.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.DadosListagemPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void Cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> Listar(@PageableDefault(size=10, sort="nome") Pageable pageable){
        return repository.findAll(pageable).map(DadosListagemPaciente::new);
    }

}
