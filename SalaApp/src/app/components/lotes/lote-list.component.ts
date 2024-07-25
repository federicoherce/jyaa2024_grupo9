import { Component, OnInit } from '@angular/core';
import { LoteService, Lote } from '../../services/lote.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  imports: [CommonModule, RouterModule],
  standalone: true,
  selector: 'app-lote-list',
  templateUrl: './lote-list.component.html'
})
export class LoteListComponent implements OnInit {
  lotes: Lote[] = [];
  errorMessage: string = '';

  constructor(private loteService: LoteService) {}

  ngOnInit() {
    this.loteService.getLotes().subscribe(
      lotes => {
        this.lotes = lotes;
      },
      error => {
        console.error('Error: ', error);
      }
    );
  }
}