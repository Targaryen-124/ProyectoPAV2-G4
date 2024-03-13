package hn.lacolonia.model;

import java.io.IOException;

import hn.lacolonia.data.KardexResponse;
import hn.lacolonia.data.ProductosResponse;
import hn.lacolonia.data.ProveedoresResponse;
import retrofit2.Call;
import retrofit2.Response;

public class DatabaseRepositoryImpl {
	
	private static DatabaseRepositoryImpl INSTANCE;
	private DatabaseClient client;
	
	private DatabaseRepositoryImpl(String url, Long timeout) {
		client = new DatabaseClient(url, timeout);
	}
	
	public static DatabaseRepositoryImpl getInstance(String url, Long timeout) {
		if(INSTANCE == null) {
			synchronized (DatabaseRepositoryImpl.class) {
				if(INSTANCE == null) {
					INSTANCE = new DatabaseRepositoryImpl(url, timeout);
				}
			}
		}
		return INSTANCE;
	}
	
	public ProductosResponse consultarProductos() throws IOException {
		Call<ProductosResponse> call = client.getDB().consultarProductos();
		Response<ProductosResponse> response = call.execute();
		if(response.isSuccessful()){
			return response.body();
		}else {
			return null;
		}
	}
	
	public ProveedoresResponse consultarProveedores() throws IOException {
		Call<ProveedoresResponse> call = client.getDB().consultarProveedores();
		Response<ProveedoresResponse> response = call.execute();
		if(response.isSuccessful()){
			return response.body();
		}else {
			return null;
		}
	}
	
	public KardexResponse consultarKardex() throws IOException {
		Call<KardexResponse> call = client.getDB().consultarKardex();
		Response<KardexResponse> response = call.execute();
		if(response.isSuccessful()){
			return response.body();
		}else {
			return null;
		}
	}

}
