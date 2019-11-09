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
