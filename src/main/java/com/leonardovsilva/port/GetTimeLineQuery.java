package com.leonardovsilva.port;

import java.util.List;

import com.leonardovsilva.adapter.TimeLineUI;
import com.leonardovsilva.domain.TimeLine;

public interface GetTimeLineQuery {

	List<TimeLineUI> getTimeLine();
	
	List<TimeLineUI> parseToTimeLineUi(List<TimeLine> timeLines);
}
