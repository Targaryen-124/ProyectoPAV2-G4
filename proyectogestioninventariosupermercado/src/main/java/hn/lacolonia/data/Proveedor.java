package hn.lacolonia.data;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;

@Entity
public class Proveedor extends AbstractEntity {

    private String identificacion;
    private String nombre;
    private String direccion;
    private String telefono;
    private String productos;
    
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getProductos() {
		return productos;
	}
	public void setProductos(String productos) {
		this.productos = productos;
	}
    
}
