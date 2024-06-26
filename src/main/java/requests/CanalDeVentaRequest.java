package requests;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Modelo para la creación de canales de venta")

public class CanalDeVentaRequest {
    
        @Schema(description = "Nombre del canal de venta", example = "Tienda de barrio", required = true)
        private String nombre;

        @Schema(description = "Dirección del canal de venta", example = "Calle 123", required = true)
        private String ubicacion;
        
        
        @Schema(description = "ID del canal de venta", example = "1", required = true)
        private int id;
        
        public int getId() {
        	 return id;
        }
        
		public String getNombre() {
			return nombre;
		}
		
		public String getUbicacion() {
			return ubicacion;
		}
		
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		public void setUbicacion(String ubicacion) {
			this.ubicacion = ubicacion;
		}
}

