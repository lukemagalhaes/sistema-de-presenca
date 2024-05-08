import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DataComponent } from './components/data/data.component';
import { TurmaComponent } from './components/turma/turma.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, DataComponent, TurmaComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})

export class AppComponent {
  title = 'Angular-sistem-de-presenca';
}
