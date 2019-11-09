package com.leonardovsilva.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeLine implements Comparable<TimeLine> {

	// 2016-10-02T11:37:35.2300892-03:00 yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX
	// 2001-07-04T12:08:56.235-0700 yyyy-MM-dd'T'HH:mm:ss.SSSZ
	// 2001-07-04T12:08:56.235-07:00 yyyy-MM-dd'T'HH:mm:ss.SSSXXX
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");

	public TimeLine(String timeStamp, String revenue) {
		this.timeStamp = this.converTimeStamp(timeStamp);
		this.revenue = revenue;
	}

	private String transactionId;
	private Date timeStamp;
	private String storeName;
	private String revenue;
	private List<Product> products;

	private Date converTimeStamp(String timeStamp) {
		try {
			return formatter.parse(timeStamp);
		} catch (ParseException e) {
			return null;
		}
	}

	public String converTimeStampToString(Date timeStamp) {
		return formatter.format(timeStamp);
	}

	@Override
	public int compareTo(TimeLine timeLine) {
		return getTimeStamp().compareTo(timeLine.getTimeStamp());
	}
}
