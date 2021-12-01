
/**
 * @project Simple GUI Calendar
 * @author Branden Lee
 * @version 0.01
 */

import java.time.*;
import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * the event model
 */
public class EventModel {

	public String eventTitle;
	public LocalDate eventDate;
	public LocalTime timeStart;
	public LocalTime timeFinish;

	public EventModel(String title, LocalDate date, LocalTime startTime, LocalTime endTime) {
		this.eventTitle = title;
		this.eventDate = date;
		this.timeStart = startTime;
		if (!(endTime == null))
			this.timeFinish = endTime;
	}

	public EventModel() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return this.eventTitle;
	}

	/**
	 * Returns the short date as a string
	 * 
	 * @return eventDate the short date string
	 */
	public String getShortDate() {
		int month = eventDate.getMonthValue();
		String zeroMonth = "";
		if (month < 10)
			zeroMonth = "0";
		int day = eventDate.getDayOfMonth();
		String zeroDay = "";
		if (day < 10)
			zeroDay = "0";
		int year = eventDate.getYear();
		return (zeroMonth + month + "/" + zeroDay + day + "/" + year);
	}

	public String getDate() {
		return eventDate.toString();
	}

	public String getTimeStart() {
		return timeStart.toString();
	}

	public String getTimeEnd() {
		if (timeFinish == null) {
			return "";
		}
		return timeFinish.toString();
	}
}
