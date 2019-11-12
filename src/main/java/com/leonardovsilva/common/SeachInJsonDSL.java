package com.leonardovsilva.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.leonardovsilva.adapter.TimeLineExternalCustomDataEntity;
import com.leonardovsilva.adapter.TimeLineExternalEntity;

public class SeachInJsonDSL {
	
	private List<TimeLineExternalEntity> timeLineExternalEntities;
	private List<Result> result;
	
	public SeachInJsonDSL(List<TimeLineExternalEntity> timeLineExternalEntities) {
		this.timeLineExternalEntities = timeLineExternalEntities;
		this.result = new ArrayList<Result>();
	}
	
	public SeachInJsonDSL searchFieldByPredicate(String predicate) {

		List<TimeLineExternalCustomDataEntity> TimeLineExternalCustomDataEntinies = Arrays
				.asList(this.timeLineExternalEntities.get(0).getCustomData());

		Optional<TimeLineExternalCustomDataEntity> timeLineExternalCustomDataEntity = TimeLineExternalCustomDataEntinies
				.stream().filter(data -> data.getKey().equals(predicate)).findFirst();
		if (timeLineExternalCustomDataEntity.isPresent()) {
			Result result = new Result();
			result.results.add(timeLineExternalCustomDataEntity.get().getValue());
			this.result.add(result);
		}
		
		return this;
	}
	
	public SeachInJsonDSL searchFieldByPredicateInList(List<String> predicates, String predicateFilter, String predicateFilterValue) {

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
						this.result.add(result);
					}
				}
			}
		}

		return this;
	}
	
	public SeachInJsonDSL clear() {
		this.result.clear();
		
		return this;
	}
	
	public List<Result> build(){
		return this.result;
	}

	public class Result {
		public List<String> results = new ArrayList<String>();
	}
}
