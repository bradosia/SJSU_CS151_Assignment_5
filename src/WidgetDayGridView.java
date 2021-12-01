
/**
 * @project Simple GUI Calendar
 * @author Branden Lee
 * @version 0.01
 */

import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

/**
 * Widget
 * The 24 hour day view that shows the events
 */
public class WidgetDayGridView extends JPanel {

	private JLabel[] labelArray = new JLabel[24];
	private ArrayList<EventModel> eventsListDay = new ArrayList<>();
	private AppController appControllerObj;

	public WidgetDayGridView(DateModel dateModelCurrent, DateModel dateModelDisplay, AppController appControllerObj_) {
		appControllerObj = appControllerObj_;

		eventsListDay.clear();
		labelArray = new JLabel[24];

		// Create the panel with a GridBagLayout
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridBagLayout());
		GridBagConstraints gridBag = new GridBagConstraints();
		gridBag.fill = GridBagConstraints.HORIZONTAL;

		// filter the events occurring today
		for (EventModel event : appControllerObj.getEvents()) {
			if (event.eventDate.equals(dateModelDisplay.date)) {
				eventsListDay.add(event);
				// Debug
				System.out.println(event.getTitle());
			}
		}

		// Create the labels for each hour
		for (int hourInt = 0; hourInt < 24; hourInt++) {
			LocalTime hourTime = LocalTime.of(hourInt, 0);
			JLabel timeLbl = new JLabel(hourTime.toString());
			timeLbl.setHorizontalAlignment(SwingConstants.CENTER);
			timeLbl.setOpaque(true);
			timeLbl.setBackground(Color.white);
			timeLbl.setBorder(BorderFactory.createLineBorder(Color.black));

			// create the gaps
			JLabel gap = new JLabel();
			gap.setBackground(Color.white);
			gap.setOpaque(true);
			gap.setBorder(BorderFactory.createLineBorder(Color.black));
			labelArray[hourInt] = gap;

			/* Event Name
			 * Blue box
			 */
			for (EventModel event : eventsListDay) {
				if (event.timeStart.getHour() == hourInt) {
					labelArray[hourInt].setText(event.eventTitle);
					labelArray[hourInt].setBackground(Color.BLUE);
					labelArray[hourInt].setForeground(Color.white);
				}
				if (event.timeStart.getHour() < hourInt && hourInt < event.timeFinish.getHour()) {
					labelArray[hourInt].setBackground(Color.BLUE);
				}
			}

			// add the additional labels
			gridBag.gridx = 0;
			gridBag.gridy = hourInt;
			gridBag.gridwidth = 1;
			timeLbl.setPreferredSize(new Dimension(45, 26));
			gridPanel.add(timeLbl, gridBag);
			gridBag.gridx = 1;
			gridBag.gridy = hourInt;
			gridBag.gridwidth = 2;
			gap.setPreferredSize(new Dimension(250, 26));
			gridPanel.add(gap, gridBag);
		}

		// add scroll bar
		JScrollPane scrollPane = new JScrollPane(gridPanel);
		scrollPane.setPreferredSize(new Dimension(325, 200));
		add(scrollPane);
	}
}