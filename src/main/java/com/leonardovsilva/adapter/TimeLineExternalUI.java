package com.leonardovsilva.adapter;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeLineExternalUI {

	private List<TimeLineUI> timeline;
	private String serverInstance;
	
}
