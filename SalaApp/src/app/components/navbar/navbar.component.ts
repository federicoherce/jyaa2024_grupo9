import { Component } from "@angular/core";
import { RouterModule } from "@angular/router";
import { Router } from '@angular/router'
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-navbar',
    standalone: true,
    imports: [RouterModule,FormsModule, CommonModule],
    templateUrl: './navbar.component.html'
  })

export class NavbarComponent{
  isAuthenticated: boolean = false;

  constructor(private router: Router) {}

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token) {
      this.isAuthenticated = true;
    }
  }

  logout() {
    localStorage.removeItem('token');
    this.isAuthenticated = false;
    this.router.navigate(['/login']);
  }
  
}