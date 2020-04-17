import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  estudiante = '© Jhon Galvis - Company 2020';
  tecnologias: string []= ['Java','Spring Framework','C#','.NET Framework','Angular','Thymelead','HTML','CSS','JavaScript','Git'];
  idiomas: string []= ['Español'];
  constructor() { }

  ngOnInit(): void {
  }

}
