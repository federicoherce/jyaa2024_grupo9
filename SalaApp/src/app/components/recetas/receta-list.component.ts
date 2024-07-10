import { Component, OnInit } from '@angular/core';
import { RecetaService, Receta } from '../../services/receta.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  imports: [CommonModule, RouterModule],
  standalone: true,
  selector: 'app-receta-list',
  templateUrl: './receta-list.component.html'
})
export class RecetaListComponent implements OnInit {
  recetas: Receta[] = [];
  errorMessage: string = '';

  constructor(private recetaService: RecetaService) {}

  ngOnInit() {
    this.recetaService.getRecetas().subscribe(
      recetas => {
        this.recetas = recetas;
      },
      error => {
        console.error('Error: ', error);
        this.errorMessage = error.error.message;
      }
    );
  }
}
