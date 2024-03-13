package hn.lacolonia.views.kardex;

import java.util.List;

import hn.lacolonia.data.Kardex;

public interface ViewModelKardex {
	
	void mostrarKardexEnGrid(List<Kardex> items);
	void mostrarMensajeError(String mensaje);
}
