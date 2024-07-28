import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ItemDeInsumo, ProductoService } from '../../services/producto.service';
import { Canal, CanalService } from '../../services/canal.service';

@Component({
  imports: [FormsModule, CommonModule],
  standalone: true,
  selector: 'app-entregar-producto',
  templateUrl: './deliver-product.component.html'
})
export class EntregarProductoComponent implements OnInit {
  productId: string = '1';
  selectedCanal: number = 0;
  cantidad: number = 1
  canales: any[] = [];
  message: string | null = null;

  constructor(private route: ActivatedRoute, private canalesService: CanalService, private productoService: ProductoService) {}

  ngOnInit() {
    const productId = this.route.snapshot.paramMap.get('productId');
    if (productId) {
      this.productId = productId;
    }
    this.loadCanales();
  }

  loadCanales() {
    this.canalesService.getCanales().subscribe(
      response => {
        this.canales = response;
      },
      error => {
        console.error('Error al cargar canales', error);
      }
    );
  }

  entregarProducto(entregaForm: NgForm) {
    if (entregaForm.valid) {
          this.canalesService.entregarProductos(this.productId, this.selectedCanal, this.cantidad).subscribe(
            response => {
              this.message = "Productos entregados";
              entregaForm.reset();
              this.selectedCanal = 1;
              this.cantidad = 1;
            },
            error => {
              console.error('Error: ', error);
              this.message = error.error.message;
            }
          );     
    }
  
  }

}
