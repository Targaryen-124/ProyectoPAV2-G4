package hn.lacolonia.model;

import hn.lacolonia.data.KardexResponse;
import hn.lacolonia.data.ProductosResponse;
import hn.lacolonia.data.ProveedoresResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DatabaseRepository {
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})    
	@GET("/pls/apex/pav2_202110060116/pgis/productos")
	Call<ProductosResponse> consultarProductos();
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})    
	@GET("/pls/apex/pav2_202110060116/pgis/proveedores")
	Call<ProveedoresResponse> consultarProveedores();
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})    
	@GET("/pls/apex/pav2_202110060116/pgis/kardex")
	Call<KardexResponse> consultarKardex();
}
