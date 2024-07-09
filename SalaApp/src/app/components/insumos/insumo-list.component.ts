import { Component, OnInit } from '@angular/core';
import { InsumoService, Insumo } from '../../services/insumo.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  imports: [CommonModule, RouterModule],
  standalone: true,
  selector: 'app-insumo-list',
  templateUrl: './insumo-list.component.html'
})
export class InsumoListComponent implements OnInit {
  insumos: Insumo[] = [];
  errorMessage: string = '';

  constructor(private insumoService: InsumoService) {}

  ngOnInit() {
    this.insumoService.getInsumos().subscribe(
      insumos => {
        this.insumos = insumos;
      },
      error => {
        console.error('Error: ', error);
        this.errorMessage = error.error.message;
      }
    );
  }
}
