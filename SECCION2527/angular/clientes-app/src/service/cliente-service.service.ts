import { Injectable } from '@angular/core';
import { Cliente } from '../../src/entity/cliente';
import { CLIENTES } from '../../src/json/clientes.json';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private urlEndPoint = "http://localhost:8080/api/clientes";
  constructor(private http: HttpClient) { }

  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.urlEndPoint);
  }
}
