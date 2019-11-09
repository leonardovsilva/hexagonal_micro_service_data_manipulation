package com.leonardovsilva.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

		TimeLine timeLine = new TimeLine(timeLineExternalEntity.getTimestamp(), timeLineExternalEntity.getRevenue());
		timeLine.setTransactionId(this.searchFieldByPredicate(timeLineExternalEntity, "transaction_id"));
		Result storeNameResult = this.searchFieldByPredicateInList(Arrays.asList("store_name"), 
				"transaction_id", timeLine.getTransactionId()).get(0);
		timeLine.setStoreName(storeNameResult != null ? storeNameResult.results.size() > 0 ? 
				storeNameResult.results.get(0): null : null);
		List<Result> productParams = this.searchFieldByPredicateInList(Arrays.asList("product_name", "product_price"), 
				"transaction_id", timeLine.getTransactionId());
		timeLine.setProducts(getProducts(productParams));
				
		return timeLine;
	}

	private String searchFieldByPredicate(TimeLineExternalEntity timeLineExternalEntity, String predicate) {

		List<TimeLineExternalCustomDataEntity> TimeLineExternalCustomDataEntinies = Arrays
				.asList(timeLineExternalEntity.getCustomData());

		Optional<TimeLineExternalCustomDataEntity> timeLineExternalCustomDataEntity = TimeLineExternalCustomDataEntinies
				.stream().filter(data -> data.getKey().equals(predicate)).findFirst();
		if (timeLineExternalCustomDataEntity.isPresent()) {
			return timeLineExternalCustomDataEntity.get().getValue();
		} else {
			return null;
		}
	}

	private List<Result> searchFieldByPredicateInList(List<String> predicates, String predicateFilter, String predicateFilterValue) {

		List<Result> searchFieldResult = new ArrayList<Result>();

		if (predicateFilter != null) {
			for (TimeLineExternalEntity timeLineExternalEntity : this.timeLineExternalEntities) {
				
				List<TimeLineExternalCustomDataEntity> TimeLineExternalCustomDataEntinies = Arrays
						.asList(timeLineExternalEntity.getCustomData());

				Optional<TimeLineExternalCustomDataEntity> timeLineExternalCustomDataEntityFiltered = TimeLineExternalCustomDataEntinies
						.stream().filter(data -> data.getKey().equals(predicateFilter)
								&& data.getValue().equals(predicateFilterValue))
						.findFirst();

				if (timeLineExternalCustomDataEntityFiltered.isPresent()) {
					
					Result result = new Result();
					
					for (String predicate : predicates) {
						Optional<TimeLineExternalCustomDataEntity> timeLineExternalCustomDataEntity = TimeLineExternalCustomDataEntinies
								.stream().filter(data -> data.getKey().equals(predicate)).findFirst();

						if (timeLineExternalCustomDataEntity.isPresent()) {
							result.results.add(timeLineExternalCustomDataEntity.get().getValue());
						}
					}
					if(!result.results.isEmpty()) {
						searchFieldResult.add(result);
					}
				}
			}
		}

		return searchFieldResult;
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
	
	class Result {
		List<String> results = new ArrayList<String>();
	}

}
