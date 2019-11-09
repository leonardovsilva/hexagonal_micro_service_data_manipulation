package com.leonardovsilva.adapter;

import java.util.List;

import com.leonardovsilva.domain.Product;

import lombok.Data;

@Data
public class TimeLineUI {
	
	private String timestamp;
	private String revenue;
	private String transaction_id;
	private String store_name;
	private List<Product> products;
}
