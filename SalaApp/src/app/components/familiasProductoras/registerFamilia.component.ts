import { Component } from '@angular/core';
import { FamiliaProductoraService, FamiliaProductora, FamiliaProductoraPost } from '../../services/familiaproductora.service';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  imports: [FormsModule, CommonModule],
  standalone: true,
  selector: 'app-registerFamilia',
  templateUrl: './registerFamilia.component.html'
})

export class RegisterFamiliaComponent {
  familia: FamiliaProductora = { id: 0, nombre: '', fecha_inicio: new Date(), zona: '' };
  familiaPost: FamiliaProductoraPost = { id: 0, nombre: '', fecha_inicio: '', zona: ''}
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private familiaService: FamiliaProductoraService) {}

  register(registerForm: NgForm) {
    if (registerForm.valid) {
      this.familiaPost.nombre = this.familia.nombre
      this.familiaPost.zona = this.familia.zona
      this.familiaPost.fecha_inicio = this.familia.fecha_inicio.toString()
      this.familiaService.createFamiliaProductora(this.familiaPost).subscribe(
        response => {
          console.log('Familia Productora creada', response);
          this.successMessage = "Familia Productora creada con exito";
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

