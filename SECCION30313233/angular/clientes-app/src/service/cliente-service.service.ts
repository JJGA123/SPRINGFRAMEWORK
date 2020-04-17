import { Injectable } from '@angular/core';
import { Cliente } from '../../src/entity/cliente';
import { Observable, throwError } from 'rxjs';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators';
import swal from 'sweetalert2';

import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private urlEndPoint = "http://localhost:8080/api";
  private httpHeaders = new HttpHeaders({'content-Type':'application/json'});

  constructor(private http: HttpClient, private router: Router) { }

  getClientes(): Observable<Cliente[]> {
    return this.http.get<Cliente[]>(this.urlEndPoint+'/clientes');
  }

  create(cliente: Cliente) : Observable<any>{
    return this.http.post<any>(this.urlEndPoint+'/cliente',cliente,{headers: this.httpHeaders}).pipe(
      catchError(e => {

          if(e.status==400){
            return throwError(e);
          }
        swal.fire('Error al crear cliente', e.error.mensaje,'error');
        return throwError(e);
      })
    );
  }

  getCliente(id): Observable<Cliente>{
    return this.http.get<Cliente>(`${this.urlEndPoint+'/cliente'}/${id}`).pipe(
      catchError(e => {
        this.router.navigate(['/cliente']);
        swal.fire('Error al editar', e.error.mensaje,'error');
        return throwError(e);
      })
    );
  }

  update(cliente: Cliente): Observable<any>{
    return this.http.put<any>(`${this.urlEndPoint+'/cliente'}/${cliente.id}`, cliente,{headers: this.httpHeaders}).pipe(
      catchError(e => {
        if(e.status==400){
          return throwError(e);
        }
        swal.fire('Error al editar', e.error.mensaje,'error');
        return throwError(e);
      })
    );;
  }

  delete(id: number): Observable<Cliente>{
    return this.http.delete<Cliente>(`${this.urlEndPoint+'/cliente'}/${id}`, {headers: this.httpHeaders}).pipe(
      catchError(e => {
        swal.fire('Error al eliminar cliente', e.error.mensaje,'error');
        return throwError(e);
      })
    );;
  }
}
