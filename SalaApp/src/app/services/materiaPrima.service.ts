import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import exp from 'constants';
import { environment } from '../environments/environment';

export interface MateriaPrima {
    id: number;
    nombre: string;
    fechaCompra: string;
    fechaVencimiento: string;
    costoPorKg: number;
    formaAlmacenamiento: string;
    peso: number;
    productor: Productor;
    }

export interface Productor {
    id: number;
    nombre: string;
    fechaInicio: string;
    zona: string;
}
export interface MateriaPrimaPost {
    nombre: string;
    fechaCompra: string;
    fechaVencimiento: string;
    costoPorKg: number;
    formaAlmacenamiento: string;
    peso: number;
    nombreProductor: string;
}

export interface MateriaPrimaRequest {
    nombre: string;
    fechaCompra: string;
    fechaVencimiento: string;
    costoPorKg: number;
    formaAlmacenamiento: string;
    peso: number;
    productor: Productor;
    nombreProductor: string;
}



    @Injectable({
        providedIn: 'root'
      })

export class MateriaPrimaService {
    private apiURL = `${environment.apiUrl}/materiasPrimas`;

    constructor(private http: HttpClient) {
    }

    getMateriasPrimas(): Observable<MateriaPrima[]> {
        const headers = new HttpHeaders({'Authorization':`Bearer ${localStorage.getItem('token')}`});
        
        return this.http.get<MateriaPrima[]>(`${this.apiURL}/all`,{ headers: headers });
    }

    getMateriaPrima(id: number): Observable<MateriaPrimaRequest> {
        return this.http.get<MateriaPrimaRequest>(`${this.apiURL}/${id}`);
    }

    deleteMateriaPrima(id: number): Observable<void> {
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
    
      updateMateriaPrima(materiaPrima: MateriaPrimaPost, id: number): Observable<MateriaPrima> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.put<MateriaPrima>(`${this.apiURL}/${id}`, materiaPrima, { headers: headers })
          .pipe(
            catchError(this.handleError)
          );
      }
    
      createMateriaPrima(materiaPrima: MateriaPrimaPost): Observable<MateriaPrima> {

        const headers = new HttpHeaders({ 'Content-Type': 'application/json' ,'Authorization':`Bearer ${localStorage.getItem('token')}`});
        return this.http.post<MateriaPrima>(`${this.apiURL}`, materiaPrima, { headers: headers })
          .pipe(
            catchError(this.handleError)
          );
      }
    
    }