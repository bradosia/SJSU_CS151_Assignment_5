
/**
 * @project Simple GUI Calendar
 * @author Branden Lee
 * @version 0.01
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Controller for the Application
 *
 */
public class AppController {
	private DateModel dateModelCurrent;
	private DateModel dateModelDisplay;
	private WidgetMonthDayPickerView widgetMonthDayPickerView;
	private ActionBarView actionBarView;
	private WidgetDayGridView dayView;
	JPanel rightPanel;
	JPanel mainPanel;
	private ArrayList<EventModel> eventsList = null;
	private File eventsFile = new File("./events.txt");

	AppController() {
		ZoneId zoneIdObj = ZoneId.of("America/Los_Angeles");
		LocalDate dateNow = LocalDate.now(zoneIdObj);
		dateModelCurrent = new DateModel(dateNow);
		dateModelDisplay = new DateModel(dateNow);
		new EventModel();

		// main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		// load the events
		eventsList = loadEvents();
		if (eventsList == null) {
			eventsList = new ArrayList<EventModel>();
		}

		update();
	}

	public void update() {
		widgetMonthDayPickerView = new WidgetMonthDayPickerView(this);
		widgetMonthDayPickerView.update(dateModelCurrent, dateModelDisplay);
		actionBarView = new ActionBarView(this);

		dayView = new WidgetDayGridView(dateModelCurrent, dateModelDisplay, this);

		mainPanel.removeAll();

		rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		rightPanel.add(actionBarView.getPanel());
		rightPanel.add(dayView);

		mainPanel.add(widgetMonthDayPickerView.getPanel());
		mainPanel.add(rightPanel);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	public void MonthDayPickerDaySelected(LocalDate dateSelected) {
		dateModelDisplay.setDate(dateSelected);
		update();
	}

	public void doToday() {
		dateModelDisplay.setDate(dateModelCurrent.date);
		update();
	}

	public void createEvent() {
		new CreateEventPopupView(dateModelDisplay, this);
	}

	public void doGridLeft() {
		dateModelDisplay.setDate(dateModelDisplay.date.minusDays(1));
		update();
	}

	public void doGridRight() {
		dateModelDisplay.setDate(dateModelDisplay.date.plusDays(1));
		update();
	}

	public void doExit() {
		saveEvents(eventsList);
		System.exit(0);
	}

	public JPanel getPanel() {
		return mainPanel;
	}

	public void doSelectDate(LocalDate dateSelected) {
		dateModelDisplay.setDate(dateSelected);
		update();
		// Debug
		// System.out.println(dateModelDisplay.date.getDayOfYear() + " " +
		// dateModelDisplay.date.getYear());
		// System.out.println(dateSelected.getDayOfYear() + " " +
		// dateSelected.getYear());
	}

	public void addEvent(String titleString, LocalDate eventDate, LocalTime startTime, LocalTime endTime) {
		EventModel newEvent = new EventModel(titleString, eventDate, startTime, endTime);
		if (isEventConflict(newEvent)) {
			new EventConflictPopupView();
		} else {
			eventsList.add(newEvent);
			update();
		}
	}

	public ArrayList<EventModel> getEvents() {
		return eventsList;
	}

	/**
	 * Checks if the events conflicts
	 * 
	 * @return true if conflicts and false if not
	 */
	public boolean isEventConflict(EventModel event_) {
		Boolean result = false;
		for (EventModel event : eventsList) {
			if (event.eventDate.equals(event_.eventDate)) {
				if (event_.timeStart.isBefore(event.timeFinish) && event_.timeFinish.isAfter(event.timeStart)) {
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * load events from the file
	 * 
	 * @return ArrayList list of events
	 */
	public ArrayList<EventModel> loadEvents() {
		ArrayList<EventModel> newEventList = new ArrayList<>();
		if (!(eventsFile.exists())) {
			System.out.println("The events file: " + eventsFile.getName() + " does not exist.");
			return null;
		}
		try {
			FileReader fileReader = new FileReader(eventsFile);
			BufferedReader br = new BufferedReader(fileReader);
			EventReader eventReader = new EventReader(br);

			while (true) {
				EventModel eventNew = eventReader.read();
				if (eventNew == null)
					break;
				newEventList.add(eventNew);
			}

			br.close();
			fileReader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Loaded " + newEventList.size() + " event(s).");
		return newEventList;
	}

	/**
	 * saves events to a file
	 * 
	 * @param eventsList a list of events
	 */
	public void saveEvents(ArrayList<EventModel> eventsList) {
		try {
			FileWriter fileWriter = new FileWriter(eventsFile);
			PrintWriter printWritwe = new PrintWriter(fileWriter);
			EventWriter eventWriter = new EventWriter(printWritwe);

			for (EventModel event : eventsList) {
				eventWriter.write(event);
			}
			printWritwe.close();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
