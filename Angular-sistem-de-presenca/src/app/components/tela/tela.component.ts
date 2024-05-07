import { Component } from '@angular/core';
import { HeaderComponent } from "../header/header.component";
import { AsideComponent } from "../aside/aside.component";

@Component({
    selector: 'app-tela',
    standalone: true,
    templateUrl: './tela.component.html',
    styleUrl: './tela.component.scss',
    imports: [HeaderComponent, AsideComponent]
})
export class TelaComponent {

}
