import { Component } from '@angular/core';
import { ProductoService, Producto } from '../../services/producto.service';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  imports: [FormsModule, CommonModule],
  standalone: true,
  selector: 'app-create-product',
  templateUrl: './create-product.component.html'
})

export class CreateProductComponent {
  producto: Producto = { nombre: '', fechaEnvasado: '', fecha_vencimiento: '', precioVenta: 0, cantidadProductos: 0};
  errorMessage: string = '';
  successMessage: string = '';
  loteId: string | null = null;

  constructor(private productoService: ProductoService, private route: ActivatedRoute) {}

  ngOnInit() {
    this.loteId = this.route.snapshot.paramMap.get('id');
  }


  registerProduct(productForm: NgForm) {
    if (productForm.valid && this.loteId) {
      this.productoService.createProduct(this.producto, this.loteId).subscribe(
        response => {
          console.log('Producto creado', response);
          this.successMessage = "Producto creado con exito";
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
