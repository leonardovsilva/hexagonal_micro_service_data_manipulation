package com.leonardovsilva.adapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.leonardovsilva.configuration.ExternalApiProperty;
import com.leonardovsilva.domain.TimeLine;
import com.leonardovsilva.port.LoadTimeLinePort;

@Component
public class TimeLineExternalAdapter implements LoadTimeLinePort {

	@Bean
	public RestTemplate restTemplate() {
	 return new RestTemplate();
	}
	
	@Autowired
	ExternalApiProperty externalApiProperty;
	
	@Override
	public List<TimeLineUI> loadTimeLine() {
		ResponseEntity<LinkedHashMap<String, TimeLineExternalEntity[]>> timeLineExternalMap = restTemplate().exchange(
				externalApiProperty.getLink(), HttpMethod.GET, null,
				new ParameterizedTypeReference<LinkedHashMap<String, TimeLineExternalEntity[]>>() {});
		
		TimeLineExternalEntity[] timeLineExternalEntities = timeLineExternalMap.getBody().get("events");

		List<TimeLine> timeLines = new TimeLineExternalParser(timeLineExternalEntities).parseToTimeLine();
		
		return this.parseToTimeLineUi(timeLines);
	}
	
	private List<TimeLineUI> parseToTimeLineUi(List<TimeLine> timeLines){
		
		List<TimeLineUI> timeLineUIList = new ArrayList<TimeLineUI>();
		
		for (TimeLine timeLine : timeLines) {
			TimeLineUI timeLineUI = new TimeLineUI();
			timeLineUI.setRevenue(timeLine.getRevenue());
			timeLineUI.setStore_name(timeLine.getStoreName());
			timeLineUI.setTransaction_id(timeLine.getTransactionId());
			timeLineUI.setProducts(timeLine.getProducts());
			timeLineUI.setTimestamp(timeLine.converTimeStampToString(timeLine.getTimeStamp()));
			
			timeLineUIList.add(timeLineUI);
		}
		
		return timeLineUIList;
	}

}
