import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AlunoComponent } from "./components/aluno/aluno.component";

@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.scss',
    imports: [RouterOutlet, AlunoComponent]
})
export class AppComponent {
  title = 'Angular-sistem-de-presenca';
}
