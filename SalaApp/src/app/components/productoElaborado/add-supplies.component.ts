import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InsumoService } from '../../services/insumo.service';
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
  selectedInsumo: number | null = 0;
  cantidad: number = 0;
  errorMessage: string | null = null;
  successMessage: string | null = null;
  nuevoInsumo: ItemDeInsumo = {cantidad: 0, insumo: 0}

  constructor(private route: ActivatedRoute, private insumoService: InsumoService, private productoService: ProductoService) {}

  ngOnInit() {
    const productId = this.route.snapshot.paramMap.get('productId');
    if (productId) {
      this.productId = productId;
    } else {
      this.errorMessage = 'ID de producto no encontrado';
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
    if (insumoForm.valid) {
        this.nuevoInsumo = {
            cantidad: this.cantidad,
            insumo: this.selectedInsumo ?? 0
        }
          };
          this.productoService.agregarInsumos(this.productId, this.nuevoInsumo).subscribe(
            response => {
              this.successMessage = "Insumo agregado con éxito";
              insumoForm.reset();
              this.selectedInsumo = 0;
              this.cantidad = 0;
            },
            error => {
              console.log(this.nuevoInsumo)
              console.error('Error al agregar insumo', error);
              this.errorMessage = 'Error al agregar insumo';
            }
          );
        }
    }
