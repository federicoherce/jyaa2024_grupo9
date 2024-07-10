import { Component, OnInit } from '@angular/core';
import { ProductoService, Producto } from '../../services/producto.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  imports: [CommonModule, RouterModule],
  standalone: true,
  selector: 'app-list-products',
  templateUrl: './list-products.component.html'
})
export class ProductListComponent implements OnInit {
  productos: Producto[] = [];
  errorMessage: string = '';

  constructor(private productoService: ProductoService) {}

  ngOnInit() {
    this.productoService.getProductos().subscribe(
      productos => {
        this.productos = productos;
      },
      error => {
        console.error('Error: ', error);
      }
    );
  }
}