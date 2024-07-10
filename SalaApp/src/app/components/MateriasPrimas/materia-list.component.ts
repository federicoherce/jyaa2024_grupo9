import { Component, OnInit } from '@angular/core';
import { MateriaPrimaService, MateriaPrima } from '../../services/materiaPrima.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
    imports: [CommonModule, RouterModule],
    standalone: true,
    selector: 'app-materia-list',
    templateUrl: './materia-list.component.html'
    })
export class MateriaListComponent implements OnInit {
    materias: MateriaPrima[] = [];
    errorMessage: string = '';

    constructor(private materiaService: MateriaPrimaService) {}

    ngOnInit() {
        this.materiaService.getMateriasPrimas().subscribe(
        materias => {
            this.materias = materias;
        },
        error => {
            console.error('Error: ', error);
        }
        );
    }
}