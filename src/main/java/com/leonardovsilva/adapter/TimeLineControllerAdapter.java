package com.leonardovsilva.adapter;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardovsilva.domain.TimeLine;
import com.leonardovsilva.port.GetTimeLineQuery;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TimeLineControllerAdapter {

	private final GetTimeLineQuery getTimeLineQuery;

	@GetMapping(path = "/timeline")
	List<TimeLineUI> getTimeLine() {
		
		return getTimeLineQuery.getTimeLine();
	}
}
