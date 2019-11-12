package com.leonardovsilva.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.leonardovsilva.adapter.TimeLineExternalUI;
import com.leonardovsilva.adapter.TimeLineUI;
import com.leonardovsilva.domain.TimeLine;
import com.leonardovsilva.port.GetTimeLineQuery;
import com.leonardovsilva.port.LoadTimeLinePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class GetTimeLineService implements GetTimeLineQuery {

	private final LoadTimeLinePort loadTimeLinePort;
	
	@Autowired
	Environment environment;
	
	@Override
	public TimeLineExternalUI getTimeLine() {
		return parseToTimeLineUi(loadTimeLinePort.loadTimeLine());
	}
	
	@Override
	public TimeLineExternalUI parseToTimeLineUi(List<TimeLine> timeLines){
		
		if(timeLines == null)
			return null;
		
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
		
		return new TimeLineExternalUI(timeLineUIList, environment.getProperty("local.server.port"));
	}

}
