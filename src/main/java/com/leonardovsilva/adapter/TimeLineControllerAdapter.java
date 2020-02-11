package com.leonardovsilva.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardovsilva.port.GetTimeLineQuery;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TimeLineControllerAdapter {

	private final GetTimeLineQuery getTimeLineQuery;

	@GetMapping(path = "/timeline")
	ResponseEntity<TimeLineExternalUI> getTimeLine() {
		
		TimeLineExternalUI timeLineExternalUI = getTimeLineQuery.getTimeLine();
		
		if (timeLineExternalUI != null) {
			return new ResponseEntity<>(getTimeLineQuery.getTimeLine(),HttpStatus.OK);
		}
		
                return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		
	}
}
