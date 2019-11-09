package com.leonardovsilva.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.leonardovsilva.adapter.TimeLineUI;
import com.leonardovsilva.domain.TimeLine;
import com.leonardovsilva.port.GetTimeLineQuery;
import com.leonardovsilva.port.LoadTimeLinePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class GetTimeLineService implements GetTimeLineQuery {

	private final LoadTimeLinePort loadTimeLinePort;
	
	@Override
	public List<TimeLineUI> getTimeLine() {
		return loadTimeLinePort.loadTimeLine();
	}

}
