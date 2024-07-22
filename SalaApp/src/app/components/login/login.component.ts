import { Component } from '@angular/core';
import { LoginService, Usuario} from '../../services/login.service';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  imports: [FormsModule, CommonModule],
  standalone: true,
  selector: 'app-login',
  templateUrl: './login.component.html'
})

export class LoginComponent {
  user: Usuario = { email: '', password: ''};
  errorMessage: string = '';
  successMessage: string = '';

  constructor(private loginService: LoginService) {}

  login(loginForm: NgForm) {
    this.loginService.login(this.user).subscribe(
        (response: any) => {
          localStorage.setItem('token', response.token);
        
        if (response.token) {
            this.successMessage = 'Usuario logueado correctamente';
            }},
        error => {
          this.errorMessage = 'Usuario o contraseña incorrectos';
    }
  )}
}
