package hn.lacolonia.data;

import java.util.List;

public class KardexResponse {
	
	private List<Kardex> items;
	private int count;
	
	public List<Kardex> getItems() {
		return items;
	}
	public void setItems(List<Kardex> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
