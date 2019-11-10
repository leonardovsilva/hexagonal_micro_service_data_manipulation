package com.leonardovsilva.adapter;

import java.util.List;

import com.leonardovsilva.domain.Product;

import lombok.Data;
import lombok.Setter;
import lombok.AccessLevel;

@Data
public class TimeLineUI {
	
	@Setter(AccessLevel.PRIVATE)
	private String timestamp;
	
	@Setter(AccessLevel.PRIVATE)
	private String revenue;
	
	@Setter(AccessLevel.PRIVATE)
	private String transaction_id;
	
	@Setter(AccessLevel.PRIVATE)
	private String store_name;
	
	@Setter(AccessLevel.PRIVATE)
	private List<Product> products;
	
	public TimeLineUI setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		
		return this;
	}
	
	public TimeLineUI setRevenue(String revenue) {
		this.revenue = revenue;
		
		return this;
	}
	
	public TimeLineUI setTransactionId(String transaction_id) {
		this.transaction_id = transaction_id;
		
		return this;
	}
	
	public TimeLineUI setStoreName(String store_name) {
		this.store_name = store_name;
		
		return this;
	}
	
	public TimeLineUI setProducts(List<Product> products) {
		this.products = products;
		
		return this;
	}
}
