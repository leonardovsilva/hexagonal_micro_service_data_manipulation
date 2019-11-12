package com.leonardovsilva.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.leonardovsilva.common.SeachInJsonDSL;
import com.leonardovsilva.common.SeachInJsonDSL.Result;
import com.leonardovsilva.domain.Product;
import com.leonardovsilva.domain.TimeLine;

public class TimeLineExternalParser {

	private List<TimeLineExternalEntity> timeLineExternalEntities;

	public TimeLineExternalParser(TimeLineExternalEntity[] timeLineExternalEntities) {
		this.timeLineExternalEntities = Arrays.asList(timeLineExternalEntities);
	}

	public List<TimeLine> parseToTimeLine() {

		List<TimeLine> timeLines = new ArrayList<TimeLine>();

		for (TimeLineExternalEntity timeLineExternalEntity : this.timeLineExternalEntities) {
			if (timeLineExternalEntity.getRevenue() != null) {
				timeLines.add(convertToTimeLine(timeLineExternalEntity));
			}
		}
		
		Collections.sort(timeLines, Collections.reverseOrder());
		
		return timeLines;
	}

	private TimeLine convertToTimeLine(TimeLineExternalEntity timeLineExternalEntity) {

		SeachInJsonDSL seachInJsonDSL = new SeachInJsonDSL(this.timeLineExternalEntities);
		
		TimeLine timeLine = new TimeLine(timeLineExternalEntity.getTimestamp(), timeLineExternalEntity.getRevenue());
		timeLine.setTransactionId(seachInJsonDSL.searchFieldByPredicate("transaction_id").build().get(0).results.get(0));
		Result storeNameResult = seachInJsonDSL.searchFieldByPredicateInList(Arrays.asList("store_name"), 
				"transaction_id", timeLine.getTransactionId()).build().get(0);
		timeLine.setStoreName(storeNameResult != null ? storeNameResult.results.size() > 0 ? 
				storeNameResult.results.get(0): null : null);
		List<Result> productParams = seachInJsonDSL.clear().searchFieldByPredicateInList(Arrays.asList("product_name", "product_price"), 
				"transaction_id", timeLine.getTransactionId()).build();
		timeLine.setProducts(getProducts(productParams));
				
		return timeLine;
	}

	private List<Product> getProducts(List<Result> params){
		
		List<Product> products = new ArrayList<Product>();
		
		if(!params.isEmpty()) {
			for (Result result : params) {
				Product product = new Product();
				product.setName(result.results.get(0));
				product.setPrice(result.results.get(1));
				
				products.add(product);
			}
		}
		
		return products;
	}
}
