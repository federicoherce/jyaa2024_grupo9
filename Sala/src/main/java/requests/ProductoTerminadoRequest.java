package requests;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo para la creación de productos")
public class ProductoTerminadoRequest {
	
	    @Schema(description = "Nombre del producto", example = "Mermelada de ciruela", required = true)
	    private String nombre;

	    @Schema(description = "Fecha de envasado del producto", example = "2024-06-26", required = true)
	    private LocalDate fechaEnvasado;

	    @Schema(description = "Fecha de vencimiento del producto", example = "2024-10-26", required = true)
	    @JsonProperty("fechaVencimiento")
	    private LocalDate fechaVencimiento;
	    
	    @Schema(description = "Precio de venta del producto", example = "1000", required = true)
	    private Double precioVenta;
	    
	    @Schema(description = "Cantidad total de stock", example = "150", required = true)
	    private int cantidadProductos;

		public String getNombre() {
			return nombre;
		}

		public LocalDate getFechaEnvasado() {
			return fechaEnvasado;
		}

		public LocalDate getFechaVencimiento() {
			return fechaVencimiento;
		}

		public Double getPrecioVenta() {
			return precioVenta;
		}

		public int getCantidadProductos() {
			return cantidadProductos;
		}
	    
	    

}
