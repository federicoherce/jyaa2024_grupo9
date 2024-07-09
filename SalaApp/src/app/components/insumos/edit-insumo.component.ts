import { Component, OnInit } from '@angular/core';
import { InsumoService, Insumo } from '../../services/insumo.service';
import { ActivatedRoute } from '@angular/router';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-insumo',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './edit-insumo.component.html'
})

export class EditInsumoComponent implements OnInit {
  insumo: Insumo = { nombre: '', cantidad: 0, costo_unitario: 0 };
  errorMessage: string = '';
  successMessage: string = '';
  insumoId: number | null = null;

  constructor(private insumoService: InsumoService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.insumoId = +params['id'];
        this.loadInsumo(this.insumoId);
      }
    });
  }

  loadInsumo(id: number) {
    this.insumoService.getInsumo(id).subscribe(
      (insumo) => {
        this.insumo = {
          nombre: insumo.nombre,
          cantidad: insumo.cantidad,
          costo_unitario: insumo.costo_unitario
        };
      },
      (error) => {
        console.error('Error: ', error);
        this.errorMessage = 'No se pudo cargar el insumo';
      }
    );
  }

  saveInsumo(form: NgForm) {
    if (form.valid && this.insumoId !== null) {
      this.insumoService.updateInsumo(this.insumo).subscribe(
        response => {
          console.log('Insumo actualizado', response);
          this.successMessage = "Insumo actualizado con éxito";
        },
        error => {
          console.error('Error: ', error);
          this.errorMessage = error.error.message;
        }
      );
    } else {
      this.errorMessage = 'Por favor, complete correctamente los campos';
    }
  }
}
