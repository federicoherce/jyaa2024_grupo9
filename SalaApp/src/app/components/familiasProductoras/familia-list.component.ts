import { Component, OnInit } from '@angular/core';
import { FamiliaProductoraService, FamiliaProductora } from '../../services/familiaproductora.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  imports: [CommonModule, RouterModule],
  standalone: true,
  selector: 'app-familia-list',
  templateUrl: './familia-list.component.html'
})
export class FamiliaListComponent implements OnInit {
  familias: FamiliaProductora[] = [];
  errorMessage: string = '';

  constructor(private familiaService: FamiliaProductoraService) {}

  ngOnInit() {
    this.familiaService.getFamiliasProductoras().subscribe(
      familias => {
        this.familias = familias;
      },
      error => {
        console.error('Error: ', error);
        this.errorMessage = error.error.message;
      }
    );
  }
}

