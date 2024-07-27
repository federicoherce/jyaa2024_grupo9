import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from '../environments/environment';
import { HttpHeaders } from '@angular/common/http';

export interface ItemDeMateriaPrima {
  materiaPrimaId: number;
  cantidadEnKg: number;
}

export interface Lote {
  id: number;
  codigo: string;
  nombre: string;
  fechaElaboracion: string;
  cantidadProducida: number;
  costoLote: number;
  itemsDeMateriaPrima: ItemDeMateriaPrima[];
}

export interface LoteRequest {
  codigo: string;
  nombre: string;
  fechaElaboracion: string;
  cantidadProducida: number;
  itemsDeMateriaPrima: ItemDeMateriaPrima[];
}

@Injectable({
  providedIn: 'root'
})

export class LoteService {
  private apiURL = `${environment.apiUrl}/lotes`; 
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' ,'Authorization':`Bearer ${localStorage.getItem('token')}`});

  constructor(private http: HttpClient) {
  }

  getLotes(): Observable<Lote[]> {
    return this.http.get<Lote[]>(`${this.apiURL}/all`, { headers: this.headers });
  }

  createLote(lote: LoteRequest): Observable<LoteRequest> {
    return this.http.post<LoteRequest>(this.apiURL, lote, { headers: this.headers })
  }

  getLote(id: number): Observable<Lote> {
    return this.http.get<Lote>(`${this.apiURL}/${id}`, { headers: this.headers });
  }

  deleteLote(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiURL}/${id}`, { headers: this.headers });
  }

}
