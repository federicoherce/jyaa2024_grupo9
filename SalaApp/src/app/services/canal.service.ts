import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../environments/environment';

export interface Canal {
  id: number;
  nombre: string;
  ubicacion: string;
}

export interface CanalRequest {
  nombre: string;
  ubicacion: string;
}

@Injectable({
  providedIn: 'root'
})

export class CanalService {
  private apiURL = `${environment.apiUrl}/canalesVenta`;

  constructor(private http: HttpClient) {
  }

  getCanales(): Observable<Canal[]> {
    return this.http.get<Canal[]>(`${this.apiURL}/all`);
  }

  getCanal(id: number): Observable<CanalRequest> {
    return this.http.get<CanalRequest>(`${this.apiURL}/${id}`);
  }


  deleteCanal(id: number): Observable<void> {
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

  updateCanal(canal: CanalRequest, id: number): Observable<Canal> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Canal>(`${this.apiURL}/${id}`, canal, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  createCanal(canal: CanalRequest): Observable<Canal> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Canal>(`${this.apiURL}`, canal, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }

}