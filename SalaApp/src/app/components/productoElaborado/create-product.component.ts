import { Component } from '@angular/core';
import { ProductoService, Producto } from '../../services/producto.service';
import { NgForm, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  imports: [FormsModule, CommonModule],
  standalone: true,
  selector: 'app-create-product',
  templateUrl: './create-product.component.html'
})

export class CreateProductComponent {
  producto: Producto = { id: 0, nombre: '', fechaEnvasado: '', fechaVencimiento: '', precioVenta: 0,
   cantidadProductos: 0, costoTotal: 0, insumos: []}
  errorMessage: string = '';
  successMessage: string = '';
  loteId: string | null = null;

  constructor(private productoService: ProductoService, 
              private route: ActivatedRoute,
              private router: Router) {}

  ngOnInit() {
    this.loteId = this.route.snapshot.paramMap.get('id');
  }


  registerProduct(productForm: NgForm) {
    if (productForm.valid && this.loteId) {
      this.productoService.createProduct(this.producto, this.loteId).subscribe(
        response => {
          console.log('Producto creado', response);
          if (response && response.id) {
            this.successMessage = "Producto creado con éxito";
            console.log(response.id)
            this.router.navigate([`/agregarInsumos/${response.id}`]);
          } else {
            console.error('Respuesta inválida del servidor: falta el ID del producto');
          }  
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
