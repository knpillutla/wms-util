package com.threedsoft.util.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DateTimeUtil {
	public static String getFormattedStringForHour(LocalDateTime dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:00:00");
		return dateTime.format(formatter);
	}
	public static String getFormattedStringForCurrentHour() {
		return getFormattedStringForHour(LocalDateTime.now());
	}

	public static List<LocalDateTime> getSortedDateTime(int numOfDays) {
		// get data for last 3 days
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime monitorStartDateTime = now.minusDays(numOfDays);
		int numOfCounterHours = numOfDays*24;
		List<LocalDateTime> hourList = new ArrayList();
		for(int i=0;i<=numOfCounterHours;i++) {
			LocalDateTime counterDateTime = monitorStartDateTime.plusHours(i);
			//String hourKey = getFormattedStringForHour(counterDateTime);
			hourList.add(counterDateTime);
		}
		return hourList;
	}	

	public static List<String> getFormattedHourListFromDate(int numOfDays) {
		// get data for last 3 days
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime monitorStartDateTime = now.minusDays(numOfDays);
		int numOfCounterHours = numOfDays*24;
		List<String> formattedHourList = new ArrayList();
		for(int i=0;i<=numOfCounterHours;i++) {
			LocalDateTime counterDateTime = monitorStartDateTime.plusHours(i);
			String hourKey = getFormattedStringForHour(counterDateTime);
			formattedHourList.add(hourKey);
		}
		return formattedHourList;
	}	

	public static Map<String, LocalDateTime> getFormattedHourListFromDateMap(int numOfDays) {
		// get data for last 3 days
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime monitorStartDateTime = now.minusDays(numOfDays);
		int numOfCounterHours = numOfDays*24;
		Map<String, LocalDateTime> formattedHourMap = new TreeMap();
		for(int i=0;i<=numOfCounterHours;i++) {
			LocalDateTime counterDateTime = monitorStartDateTime.plusHours(i);
			String hourKey = getFormattedStringForHour(counterDateTime);
			formattedHourMap.put(hourKey, counterDateTime);
		}
		return formattedHourMap;
	}	

}
