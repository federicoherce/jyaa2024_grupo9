import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { HttpHeaders } from '@angular/common/http';

export interface Lote {
  id: number;
  codigo: string;
  nombre: string;
  fechaElaboracion: string;
  cantidadProducida: number;
  costoLote: number;
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

}
