import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface Receta {
  id: number;
  nombre: string;
  texto: string;
  usuarioMail: string;
}

export interface RecetaRequest {
  id?: number;
  nombre: string;
  texto: string;
  usuarioMail: string;
}

@Injectable({
  providedIn: 'root'
})

export class RecetaService {
  private apiURL = 'http://localhost:8080/Sala/recetas'; 


  constructor(private http: HttpClient) {
  }

  
  createReceta(receta: RecetaRequest): Observable<RecetaRequest> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<RecetaRequest>(this.apiURL, receta, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  getReceta(id: number): Observable<Receta> {
    return this.http.get<Receta>(`${this.apiURL}/${id}`);
  }

  getRecetas(): Observable<Receta[]> {
    return this.http.get<Receta[]>(`${this.apiURL}/all`);
  }


  updateReceta(receta: RecetaRequest): Observable<RecetaRequest> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<RecetaRequest>(this.apiURL, receta, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteReceta(id: number): Observable<void> {
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
