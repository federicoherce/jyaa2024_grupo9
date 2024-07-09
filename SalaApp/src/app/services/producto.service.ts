import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';


export interface Producto {
  id: number,
  nombre: string;
  fechaEnvasado: string;
  fechaVencimiento: string;
  precioVenta: number;
  cantidadProductos: number;
  costoTotal: number;
  insumos: ItemDeInsumo[];
}

export interface ItemDeInsumo {
  cantidad: number;
  insumo: number;
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

  agregarInsumos(productoId: string, insumos: ItemDeInsumo): Observable<any> {
    const url = `${this.apiURL}/${productoId}/agregarInsumos`; 
    console.log(url)
    return this.http.post(url, insumos);
  }


  getProductos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.apiURL}/all`);
  }

  private handleError(error: any) {
    console.error('An error occurred', error.message);
    return throwError(error);
  }

}
