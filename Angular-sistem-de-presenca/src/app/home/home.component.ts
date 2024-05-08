import { Component, OnInit, ElementRef, ViewChild, AfterViewInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DataComponent } from "../components/data/data.component";

interface TurmaItem {
  id_turma: number;
  anoEnsino: number;
  serie: string;
  periodo: string;
}

@Component({
    selector: 'app-home',
    standalone: true,
    template: `
    <section>
      <form>
        <select #selectElement placeholder="Filtrar por turma">
          <option value="">Selecione uma turma</option>
        </select>
        <button class="primary" type="button">Search</button>
      </form>
    </section>
  `,
    styleUrl: './home.component.scss',
  })

  export class HomeComponent implements OnInit, AfterViewInit {
    @ViewChild('selectElement') selectElement!: ElementRef;
    turma: TurmaItem[] = [];
    constructor(private httpClient: HttpClient) {}

  ngOnInit(): void {
    // Initialization logic here
  }

  ngAfterViewInit(): void {
    this.fetchTurma();
  }

  fetchTurma(){
    this.httpClient.get<TurmaItem[]>("https://sistema-de-presenca.onrender.com/api/turma").subscribe((turma: TurmaItem[]) => {
      this.turma = turma;
      this.createOptions();
    })
  }

  createOptions() {
    this.turma.forEach((item: TurmaItem) => {
      const option = document.createElement('option');
      option.text = item.serie;
      this.selectElement.nativeElement.add(option);
    });
  }

}
