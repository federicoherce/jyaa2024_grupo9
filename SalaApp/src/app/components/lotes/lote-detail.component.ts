// lote-detail.component.ts

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoteService, Lote, ItemDeMateriaPrima } from '../../services/lote.service';
import { MateriaPrimaService, MateriaPrima } from '../../services/materiaPrima.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  imports: [CommonModule, RouterModule],
  standalone: true,
  selector: 'app-lote-detail',
  templateUrl: './lote-detail.component.html'
})
export class LoteDetailComponent implements OnInit {
  lote: Lote | undefined;
  errorMessage: string = '';
  materiasPrimas: { [id: number]: MateriaPrima } = {};

  constructor(
    private route: ActivatedRoute,
    private loteService: LoteService,
    private materiaPrimaService: MateriaPrimaService
  ) {}

  ngOnInit() {
    const loteId = Number(this.route.snapshot.paramMap.get('id'));
    if (loteId) {
      this.loteService.getLote(loteId).subscribe(
        lote => {        
          this.lote = lote;
          this.loadMateriasPrimas();
        },
        error => {
          console.error('Error: ', error);
          this.errorMessage = error.error.message; 
        }
      );
    }
  }

  loadMateriasPrimas() {
    if (this.lote && this.lote.itemsDeMateriaPrima) {
      this.lote.itemsDeMateriaPrima.forEach(item => {
        this.materiaPrimaService.getMateriaPrima2(item.materiaPrimaId).subscribe(
          materiaPrima => {
            this.materiasPrimas[item.materiaPrimaId] = materiaPrima;
          },
          error => {
            console.error('Error: ', error);
            this.errorMessage = 'Error al cargar los detalles de las materias primas.';
          }
        );
      });
    }
  }

  getMateriaPrimaNombre(id: number): string {
    return this.materiasPrimas[id] ? this.materiasPrimas[id].nombre : 'Desconocido';
  }
}
