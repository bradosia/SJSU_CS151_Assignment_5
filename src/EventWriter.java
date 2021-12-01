
/**
 * @project Simple GUI Calendar
 * @author Branden Lee
 * @version 0.01
 */

import java.io.*;

/**
 * Writes events to a file
 */

public class EventWriter {

	private PrintWriter printWriter;

	public EventWriter(PrintWriter printWriter_) {
		this.printWriter = printWriter_;
	}

	/**
	 * writes the event to a file
	 * 
	 * @param event_ event to write
	 * @throws IOException if an exception occurs
	 */
	public void write(EventModel event_) throws IOException {
		printWriter.println(event_.getTitle());
		printWriter.println(event_.getDate());
		printWriter.println(event_.getTimeStart());
		printWriter.println(event_.getTimeEnd());
	}
}