package com.leonardovsilva.adapter;

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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class TimeLineExternalAdapter implements LoadTimeLinePort {

	@Bean
	public RestTemplate restTemplate() {
	 return new RestTemplate();
	}
	
	@Autowired
	ExternalApiProperty externalApiProperty;
	
	@Override
	@HystrixCommand(fallbackMethod="loadTimeLineFallback")
	public List<TimeLine> loadTimeLine() {
		ResponseEntity<LinkedHashMap<String, TimeLineExternalEntity[]>> timeLineExternalMap = restTemplate().exchange(
				externalApiProperty.getLink(), HttpMethod.GET, null,
				new ParameterizedTypeReference<LinkedHashMap<String, TimeLineExternalEntity[]>>() {});
		
		TimeLineExternalEntity[] timeLineExternalEntities = timeLineExternalMap.getBody().get("events");

		return new TimeLineExternalParser(timeLineExternalEntities).parseToTimeLine();
	}
	
	private List<TimeLine> loadTimeLineFallback() {
		
		return null;
	}
}
