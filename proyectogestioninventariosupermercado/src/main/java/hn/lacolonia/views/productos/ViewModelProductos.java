package hn.lacolonia.views.productos;

import java.util.List;

import hn.lacolonia.data.Producto;

public interface ViewModelProductos {
	
	void mostrarProductosEnGrid(List<Producto> items);
	void mostrarMensajeError(String mensaje);
}
