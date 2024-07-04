import { Component } from '@angular/core';
import { UserService, UsuarioRequest } from '../services/user.service';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  imports: [FormsModule, CommonModule],
  standalone: true,
  selector: 'app-register',
  templateUrl: './register.component.html'
})
export class RegisterComponent {
  user: UsuarioRequest = { email: '', nombre: '', apellido: '', password: '' };
  errorMessage: string = '';

  constructor(private userService: UserService) {}

  register(registerForm: NgForm) {
    if (registerForm.valid) {
      this.userService.createUser(this.user).subscribe(
        response => {
          console.log('User created:', response);
        },
        error => {
          console.error('There was an error!', error);
          this.errorMessage = error.error.message;
        }
      );
    } else {
      this.errorMessage = 'Por favor, complete todos los campos requeridos.';
    }
  }
}
