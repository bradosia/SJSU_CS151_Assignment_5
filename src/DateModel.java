
/**
 * @project Simple GUI Calendar
 * @author Branden Lee
 * @version 0.01
 */

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.text.DateFormatSymbols;

/*
 * Responsible for keeping track of the currently selected date and notifying the views.
 * Serves as part of the model of the project.
 */

public class DateModel {

	// instance variables
	public LocalDate date;
	private ArrayList<ChangeListener> listeners;

	/**
	 * Constructor of Month object
	 * 
	 * @param date takes a LocalDate which the whole month is centered around
	 */
	public DateModel(LocalDate date) {
		this.date = date;
		listeners = new ArrayList<ChangeListener>();
	}

	/**
	 * Sets the new date
	 * 
	 * @param date_ the new date
	 */
	public void setDate(LocalDate date_) {
		date = date_;
	}

	/**
	 * Creates a 7 by 6 grid with dates
	 * 
	 * @return dateArray
	 */
	public LocalDate[] getDateArray() {
		LocalDate firstOfMonth = this.date.withDayOfMonth(1);
		LocalDate dateFirst = firstOfMonth.minusDays(firstOfMonth.getDayOfWeek().getValue());
		LocalDate[] dateArray = new LocalDate[42];
		for (int i = 0; i < 42; i++) {
			dateArray[i] = dateFirst;
			dateFirst = dateFirst.plusDays(1);
		}
		return dateArray;
	}

	/**
	 * Returns an array of dates for the current week
	 * 
	 * @return dateArray
	 * 
	 */
	public LocalDate[] getWeekArray() {
		LocalDate currentDate = this.date;
		LocalDate[] result = new LocalDate[7];
		for (int day = 0; day < 7; day++) {
			if (day == 0) {
				if (currentDate.getDayOfWeek().getValue() != 7) {
					currentDate = currentDate.minusDays(currentDate.getDayOfWeek().getValue());
				}
			}
			result[day] = currentDate;
			currentDate = currentDate.plusDays(1);
		}
		return result;
	}

	/**
	 * Returns a string of the month name + year
	 * Example: "July 2017"
	 * 
	 * @return a String of month name + year
	 */
	public String monthYearString() {
		return this.date.getMonth().getDisplayName(TextStyle.FULL, SimpleCalendarTester.language) + " "
				+ this.date.getYear();
	}

	/**
	 * Gets the currently selected date.
	 * 
	 * @return the selected date
	 */
	public LocalDate getSelectedDate() {
		return date;
	}

}
