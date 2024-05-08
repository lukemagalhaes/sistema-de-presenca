import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, inject } from '@angular/core';

@Component({
  selector: 'app-turma',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './turma.component.html',
  styleUrl: './turma.component.css'
})
export class TurmaComponent {
  httpClient = inject(HttpClient);
  turma: any = [];

  ngOnInit(): void {
      this.fetchTurma();
  }

  fetchTurma(){
    this.httpClient.get("https://sistema-de-presenca.onrender.com/api/turma").subscribe((turma: any) => {
      console.log(turma);
      this.turma = turma;
    })
  }
}
