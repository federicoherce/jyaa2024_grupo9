import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface UsuarioRequest {
  email: string;
  nombre: string;
  apellido: string;
  password: string;
}

export interface Usuario {
  id: number;
  email: string;
  nombre: string;
  apellido: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiURL = 'http://localhost:8080/Sala/users'; 


  constructor(private http: HttpClient) {
    console.log(http)
  }

  createUser(user: UsuarioRequest): Observable<Usuario> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Usuario>(this.apiURL, user, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }

    getUser(id: number): Observable<UsuarioRequest> {
    return this.http.get<UsuarioRequest>(`${this.apiURL}/${id}`);
  }

  private handleError(error: any) {
    console.error('An error occurred', error);
    return throwError(error);
  }
}
