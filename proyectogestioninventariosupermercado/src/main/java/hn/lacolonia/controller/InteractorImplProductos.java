package hn.lacolonia.controller;

import hn.lacolonia.data.ProductosResponse;
import hn.lacolonia.model.DatabaseRepositoryImpl;
import hn.lacolonia.views.productos.ViewModelProductos;

public class InteractorImplProductos implements InteractorProductos {
	
	private DatabaseRepositoryImpl modelo;
	private ViewModelProductos vista;
	
	public InteractorImplProductos(ViewModelProductos view) {
		super();
		this.vista = view;
		this.modelo = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 30000L);
	}

	@Override
	public void consultarProductos() {
		try {
			ProductosResponse respuesta = this.modelo.consultarProductos();
			if(respuesta == null || respuesta.getCount() == 0 || respuesta.getItems() == null) {
				this.vista.mostrarMensajeError("No hay productos a mostrar");
			}else {
				this.vista.mostrarProductosEnGrid(respuesta.getItems());
			}
		}catch(Exception error) {
			error.printStackTrace();
		}
		
	}

}
