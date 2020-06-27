import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  titulo: String = 'Bienvenido a Angular!';
  curso: String = 'Curso Angular con Spring 5';
  estudiante: String = 'Jhon Galvis';
  textoBoton: String = 'Ocultar';
  habilitarInfo: boolean = true;

  setHabilitar(): void{
    this.habilitarInfo= (this.habilitarInfo==true) ? false : true;
    this.textoBoton = (this.habilitarInfo==false) ? 'Mostrar' : 'Ocultar';
  }
}
