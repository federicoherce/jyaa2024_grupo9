import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';


export interface Producto {
  nombre: string;
  fechaEnvasado: string;
  fechaVencimiento: string;
  precioVenta: number;
  cantidadProductos: number;
}

@Injectable({
  providedIn: 'root'
})

export class ProductoService {
  private apiURL = 'http://localhost:8080/Sala/productos'; 

  constructor(private http: HttpClient) {
  }

  createProduct(producto: Producto, loteId: string): Observable<Producto> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Producto>(`${this.apiURL}/${loteId}`, producto, { headers: headers })
      .pipe(
        catchError(this.handleError)
      );
  }

  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.apiURL}/all`);
  }

  private handleError(error: any) {
    console.error('An error occurred', error.message);
    return throwError(error);
  }

}
