import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface FamiliaProductora {
  id: number;
  nombre: string;
  fecha_inicio: string;
  zona: string;
}

@Injectable({
  providedIn: 'root'
})

export class FamiliaProductoraService {
  private apiURL = 'http://localhost:8080/Sala/familias_productoras'; 


  constructor(private http: HttpClient) {
  }

  
  createFamiliaProductora(fp: FamiliaProductora): Observable<FamiliaProductora> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<FamiliaProductora>(this.apiURL, fp, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  getFamiliaProductora(id: number): Observable<FamiliaProductora> {
    return this.http.get<FamiliaProductora>(`${this.apiURL}/${id}`);
  }

  getFamiliasProductoras(): Observable<FamiliaProductora[]> {
    return this.http.get<FamiliaProductora[]>(`${this.apiURL}/all`);
  }


  updateFamiliaProductora(fp: FamiliaProductora): Observable<FamiliaProductora> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<FamiliaProductora>(this.apiURL, fp, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  deleteFamiliaProductora(id: number): Observable<void> {
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
