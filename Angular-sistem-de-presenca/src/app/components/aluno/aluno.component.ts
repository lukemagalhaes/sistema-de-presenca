import { Component, OnInit } from '@angular/core';
import { Aluno } from './aluno.model';
import { AlunoService } from './aluno.service';

@Component({
  selector: 'app-aluno',
  standalone: true,
  imports: [],
  providers: [],
  templateUrl: './aluno.component.html',
  styleUrl: './aluno.component.scss',
})
export class AlunoComponent implements OnInit {
  alunos: Aluno[] = [];

  constructor(private alunoService: AlunoService) {}

  ngOnInit() {
    this.alunoService.getAllAlunos().subscribe({
      next: (data) => this.alunos = data,
      error: (error) => console.error('Erro ao obter dados:', error)
    });
  }
}
