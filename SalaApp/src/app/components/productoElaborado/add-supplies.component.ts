/*import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Insumo, InsumoService } from '../../services/insumo.service';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ItemDeInsumo, ProductoService } from '../../services/producto.service';

@Component({
  imports: [FormsModule, CommonModule],
  standalone: true,
  selector: 'app-insumos',
  templateUrl: './add-suplies.component.html'
})
export class AgregarInsumosComponent implements OnInit {
  productId: string = '';
  insumos: any[] = [];
  selectedInsumo: Insumo | null = null;
  cantidad: number = 1;
  insumoNombre: string = '';
  message: string | null = null;
  nuevoInsumo: ItemDeInsumo = {cantidad: 0, insumo: 0, nombre: ''}

  constructor(private route: ActivatedRoute, private insumoService: InsumoService, private productoService: ProductoService) {}

  ngOnInit() {
    const productId = this.route.snapshot.paramMap.get('productId');
    if (productId) {
      this.productId = productId;
    } else {
      this.message = 'ID de producto no encontrado';
    }
    this.loadInsumos();
  }

  loadInsumos() {
    this.insumoService.getInsumos().subscribe(
      response => {
        this.insumos = response;
      },
      error => {
        console.error('Error al cargar insumos', error);
      }
    );
  }
  
  addInsumo(insumoForm: NgForm) {
    if (insumoForm.valid && this.selectedInsumo) {
        this.nuevoInsumo = {
            cantidad: this.cantidad,
            nombre: this.selectedInsumo.nombre,
            insumo: this.selectedInsumo.id ?? 0
        }
        console.log("NUEVO INSUMO " + this.nuevoInsumo.nombre);
          };
          this.productoService.agregarInsumos(this.productId, this.nuevoInsumo).subscribe(
            response => {
              this.message = "Insumo agregado con éxito";
              insumoForm.reset();
              this.selectedInsumo = null;
              this.cantidad = 1;
            },
            error => {
              console.log(this.nuevoInsumo)
              console.error('Error al agregar insumo', error);
              this.message = error.error.message;
            }
          );
        }
    }*/
