import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface Insumo {
  id?: number | null;
  nombre: string;
  cantidad: number;
  costo_unitario: number;
}

/*export interface FamiliaProductoraPost {
  id?: number;
  nombre: string;
  fecha_inicio: string;
  zona: string;
}*/

@Injectable({
  providedIn: 'root'
})

export class InsumoService {
  private apiURL = 'http://localhost:8080/Sala/insumos'; 


  constructor(private http: HttpClient) {
  }

  
  createInsumo(insumo: Insumo): Observable<Insumo> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Insumo>(this.apiURL, insumo, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  getInsumo(id: number): Observable<Insumo> {
    return this.http.get<Insumo>(`${this.apiURL}/${id}`);
  }

  getInsumos(): Observable<Insumo[]> {
    return this.http.get<Insumo[]>(`${this.apiURL}/all`);
  }


  updateInsumo(insumo: Insumo): Observable<Insumo> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Insumo>(this.apiURL, insumo, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteInsumo(id: number): Observable<void> {
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
