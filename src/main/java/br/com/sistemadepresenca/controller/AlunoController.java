package br.com.sistemadepresenca.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.sistemadepresenca.aluno.Aluno;
import br.com.sistemadepresenca.aluno.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public List<Aluno> getAll(){
        List<Aluno>  listaAluno = repository.findAll();
        return listaAluno;
    }
}