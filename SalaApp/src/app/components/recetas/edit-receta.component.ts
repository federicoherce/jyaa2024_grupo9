import { Component, OnInit } from '@angular/core';
import { RecetaService, Receta } from '../../services/receta.service';
import { ActivatedRoute } from '@angular/router';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-receta',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './edit-receta.component.html'
})

export class EditRecetaComponent implements OnInit {
  receta: Receta = { nombre: '', texto: '', usuarioMail: '' };
  errorMessage: string = '';
  successMessage: string = '';
  recetaId: number | null = null;

  constructor(private recetaService: RecetaService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      if (params['id']) {
        this.recetaId = +params['id'];
        this.loadReceta(this.recetaId);
      }
    });
  }

  loadReceta(id: number) {
    this.recetaService.getReceta(id).subscribe(
      (receta) => {
        this.receta = {
          nombre: receta.nombre,
          texto: receta.texto,
          usuarioMail: receta.usuarioMail
        };
      },
      (error) => {
        console.error('Error: ', error);
        this.errorMessage = 'No se pudo cargar la receta';
      }
    );
  }

  saveReceta(form: NgForm) {
    if (form.valid && this.recetaId !== null) {
      this.recetaService.updateReceta(this.receta).subscribe(
        response => {
          console.log('Receta actualizada', response);
          this.successMessage = "Receta actualizada con éxito";
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
