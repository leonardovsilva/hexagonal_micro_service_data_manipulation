package com.leonardovsilva.port;

import java.util.List;

import com.leonardovsilva.adapter.TimeLineExternalUI;
import com.leonardovsilva.domain.TimeLine;

public interface GetTimeLineQuery {

	TimeLineExternalUI getTimeLine();
	
	TimeLineExternalUI parseToTimeLineUi(List<TimeLine> timeLines);
}
