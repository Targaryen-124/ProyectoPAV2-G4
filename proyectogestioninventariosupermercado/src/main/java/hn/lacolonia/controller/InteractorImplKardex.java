package hn.lacolonia.controller;

import hn.lacolonia.data.KardexResponse;
import hn.lacolonia.model.DatabaseRepositoryImpl;
import hn.lacolonia.views.kardex.ViewModelKardex;

public class InteractorImplKardex implements InteractorKardex {
	
	private DatabaseRepositoryImpl modelo;
	private ViewModelKardex vista;
	
	public InteractorImplKardex(ViewModelKardex view) {
		super();
		this.vista = view;
		this.modelo = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 30000L);
	}

	@Override
	public void consultarKardex() {
		try {
			KardexResponse respuesta = this.modelo.consultarKardex();
			if(respuesta == null || respuesta.getCount() == 0 || respuesta.getItems() == null) {
				this.vista.mostrarMensajeError("No hay kardex a mostrar");
			}else {
				this.vista.mostrarKardexEnGrid(respuesta.getItems());
			}
		}catch(Exception error) {
			error.printStackTrace();
		}
		
	}

}
