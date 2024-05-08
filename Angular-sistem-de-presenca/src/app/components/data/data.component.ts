import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule, } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';

@Component({
  selector: 'app-data',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './data.component.html',
  styleUrl: './data.component.css'
})

export class DataComponent implements OnInit {
  httpClient = inject(HttpClient);
  data: any = [];
  selectedSerie: string = '';

  ngOnInit(): void {
      this.fetchData();
  }

  fetchData(){
    this.httpClient.get("https://sistema-de-presenca.onrender.com/api/alunos").subscribe((data: any) => {
      console.log(data);
      this.data = data;
    })
  }

  onSerieChange(serie: string) {
    this.selectedSerie = serie;
    this.getFilteredAlunos(); 
  }

  getFilteredAlunos() {
    const filteredAlunos = this.data.filter((aluno: any) => aluno.turma.serie === this.selectedSerie);
    console.log(filteredAlunos); 
    return filteredAlunos;
  }

  onCardClick(aluno: any) {
    const faltaData = {
      id_presenca: 1,
      aluno: {
        id_aluno: aluno.id_aluno,
        turma: aluno.turma,
        nome: aluno.nome,
        idade: aluno.idade,
        genero: aluno.genero,
        dataNascimento: aluno.dataNascimento,
        endereco: aluno.endereco
      },
      aula: {
        id_aula: 1,
        turma: aluno.turma,
        professor: {
          id_professor: 1,
          nome: "Carlos Mendes",
          disciplina: "Matemática",
          formacao: "Licenciatura em Matemática"
        },
        data: "2024-05-08T05:02:34.171Z",
        tipo: "início",
        conteudo: "Introdução aos números"
      },
      presenca: !aluno.presenca, // Toggle the presence state
      justificativa: ""
    };
        
    this.httpClient.put(`https://sistema-de-presenca.onrender.com/api/faltas`, faltaData)
      .subscribe(response => {
        console.log(response);
      });
  }
  
  
}
