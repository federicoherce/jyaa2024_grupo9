import { Component } from '@angular/core';
import { InsumoService, Insumo } from '../../services/insumo.service';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  imports: [FormsModule, CommonModule],
  standalone: true,
  selector: 'app-registerInsumo',
  templateUrl: './registerInsumo.component.html'
})

export class RegisterInsumoComponent {
  insumo: Insumo = { id: 0, nombre: '', cantidad: 0, costo_unitario: '0.0' };
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private insumoService: InsumoService) {}

  register(registerForm: NgForm) {
    if (registerForm.valid) {
      this.insumoService.createInsumo(this.insumo).subscribe(
        response => {
          console.log('Insumo creado', response);
          this.successMessage = "Insumo creado con exito";
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

