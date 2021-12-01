
/**
 * @project Simple GUI Calendar
 * @author Branden Lee
 * @version 0.01
 */

import java.time.*;
import java.io.*;

/**
 * Reads events from a file
 */

public class EventReader {

	private BufferedReader buffReader;

	public EventReader(BufferedReader buffReader_) {
		buffReader = buffReader_;
	}

	/**
	 * reads the events of a file
	 * 
	 * @return Event from file or null if error
	 * @throws IOException if there is an exception
	 */
	public EventModel read() throws IOException {

		String eventTitle = buffReader.readLine();
		if (eventTitle == null) {
			return null;
		}

		LocalDate eventDate = LocalDate.parse(buffReader.readLine());
		LocalTime timeStart = LocalTime.parse(buffReader.readLine());
		LocalTime timeEnd = LocalTime.parse(buffReader.readLine());
		return new EventModel(eventTitle, eventDate, timeStart, timeEnd);
	}
}