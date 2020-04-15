import { Injectable } from '@angular/core';
import { Cliente } from '../../src/entity/cliente';
import { CLIENTES } from '../../src/json/clientes.json';
import { Observable } from 'rxjs';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor() { }

  getClientes(): Observable<Cliente[]> {
    return of(CLIENTES);
  }
}
