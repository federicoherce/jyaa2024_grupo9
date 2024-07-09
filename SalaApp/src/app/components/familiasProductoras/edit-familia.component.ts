import { Component, OnInit } from '@angular/core';
import { FamiliaProductoraService, FamiliaProductora } from '../../services/familiaproductora.service';
import { ActivatedRoute } from '@angular/router';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-familia',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './edit-familia.component.html'
})

export class EditFamiliaComponent implements OnInit {
  familia: FamiliaProductora = { id:0, nombre: '', fecha_inicio: '', zona: '' };
  errorMessage: string = '';
  successMessage: string = '';
  familiaId: number | null = null;

  constructor(private familiaProductoraService: FamiliaProductoraService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.familiaId = +params['id'];
        this.loadFamiliaProductora(this.familiaId);
      }
    });
  }

  loadFamiliaProductora(id: number) {
    this.familiaProductoraService.getFamiliaProductora(id).subscribe(
      (familia) => {
        this.familia = {
          id: familia.id,
          nombre: familia.nombre,
          fecha_inicio: familia.fecha_inicio,
          zona: familia.zona
        };
      },
      (error) => {
        console.error('Error: ', error);
        this.errorMessage = 'No se pudo cargar la familia productora';
      }
    );
  }

  saveFamilia(form: NgForm) {
    if (form.valid && this.familiaId !== null) {
      this.familiaProductoraService.updateFamiliaProductora(this.familia).subscribe(
        response => {
          console.log('Familia Productora actualizada', response);
          this.successMessage = "Familia Productora actualizada con éxito";
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
