import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Canal {
  id: number;
  nombre: string;
  ubicacion: string;
}


@Injectable({
  providedIn: 'root'
})

export class CanalService {
  private apiURL = 'http://localhost:8080/Sala/canalesVenta'; 

  constructor(private http: HttpClient) {
  }

  getCanales(): Observable<Canal[]> {
    return this.http.get<Canal[]>(`${this.apiURL}/all`);
  }

}