package hn.lacolonia.data;

import java.time.LocalDate;
import jakarta.persistence.Entity;

@Entity
public class Kardex extends AbstractEntity {
	
	private String identificacion;
    private String codigoProducto;
    private LocalDate fechaEntradaSalidaProducto;
    private String transaccion;
    private int cantidad;
    private int existencia;
    
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public LocalDate getFechaEntradaSalidaProducto() {
		return fechaEntradaSalidaProducto;
	}
	public void setFechaEntradaSalidaProducto(LocalDate fechaEntradaSalidaProducto) {
		this.fechaEntradaSalidaProducto = fechaEntradaSalidaProducto;
	}
	public String getTransaccion() {
		return transaccion;
	}
	public void setTransaccion(String transaccion) {
		this.transaccion = transaccion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getExistencia() {
		return existencia;
	}
	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}
}
