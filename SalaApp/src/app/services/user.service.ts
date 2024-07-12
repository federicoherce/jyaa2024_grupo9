import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../environments/environment';

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
  password: string;
}

@Injectable({
  providedIn: 'root'
})



export class UserService {
  private apiURL = `${environment.apiUrl}/users`;


  constructor(private http: HttpClient) {
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

  getUsers(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.apiURL}/all`);
  }


  updateUser(user: UsuarioRequest, id: number): Observable<Usuario> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Usuario>(`${this.apiURL}/${id}`, user, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteUser(id: number): Observable<void> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.delete<void>(`${this.apiURL}/${id}`, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }


  private handleError(error: any) {
    console.error('An error occurred', error.message);
    return throwError(error);
  }
}
