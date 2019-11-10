package com.leonardovsilva.service;

import java.util.ArrayList;
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
		return parseToTimeLineUi(loadTimeLinePort.loadTimeLine());
	}
	
	@Override
	public List<TimeLineUI> parseToTimeLineUi(List<TimeLine> timeLines){
		
		List<TimeLineUI> timeLineUIList = new ArrayList<TimeLineUI>();
		
		for (TimeLine timeLine : timeLines) {
			TimeLineUI timeLineUI = new TimeLineUI()
					.setRevenue(timeLine.getRevenue())
					.setStoreName(timeLine.getStoreName())
					.setTransactionId(timeLine.getTransactionId())
					.setProducts(timeLine.getProducts())
					.setTimestamp(timeLine.converTimeStampToString(timeLine.getTimeStamp()));
	
			timeLineUIList.add(timeLineUI);
		}
		
		return timeLineUIList;
	}

}
