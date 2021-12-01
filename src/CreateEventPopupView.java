
/**
 * @project Simple GUI Calendar
 * @author Branden Lee
 * @version 0.01
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.*;
import javax.swing.*;

/*
 * The popup for entering event information
 */
public class CreateEventPopupView extends JFrame {

	public CreateEventPopupView(DateModel dateModelSelected, AppController appController) {

		// create the grid
		JPanel popupPanel = new JPanel();
		popupPanel.setBorder(BorderFactory.createEmptyBorder(11, 11, 11, 11));
		popupPanel.setLayout(new GridLayout(5, 2));

		// create the labels and inputs for the grid
		JLabel labelEventTitle = new JLabel("Title:");
		JTextField inputTextEventTitle = new JTextField(7);
		JLabel labelTimeStart = new JLabel("Start Time (hh:mm AM/PM):");
		JTextField inputTextTimeStart = new JTextField(7);
		JLabel labelTimeEnd = new JLabel("End Time (hh:mm AM/PM):");
		JTextField inputTextTimeEnd = new JTextField(7);
		JLabel labelEventDate = new JLabel("Date (MM/DD/YYYY):");
		JTextField inputTextDate = new JTextField(7);
		JButton cancel = new JButton("Cancel");
		JButton save = new JButton("Save");

		// cancel button
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// save the event
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titleString = inputTextEventTitle.getText();
				LocalTime startTime = null;
				LocalTime endTime = null;
				LocalDate eventDate = null;
				try {
					startTime = LocalTime.parse(inputTextTimeStart.getText(), DateTimeFormatter.ofPattern("hh:mm a"));
				} catch (Exception e1) {
					System.out.println("Error parsing the start time.");
				}
				try {
					endTime = LocalTime.parse(inputTextTimeEnd.getText(), DateTimeFormatter.ofPattern("hh:mm a"));
				} catch (Exception e2) {
					System.out.println("Error parsing the end time.");
				}
				try {
					eventDate = LocalDate.parse(inputTextDate.getText(), DateTimeFormatter.ofPattern("MM/dd/uuuu"));
				} catch (Exception e3) {
					System.out.println("Error parsing the event date.");
				}
				if (titleString != "" && startTime != null && endTime != null && eventDate != null) {
					appController.addEvent(titleString, eventDate, startTime, endTime);
					dispose();
				}
			}
		});

		// add leading zero to month
		String monthString = "";
		if (dateModelSelected.date.getMonthValue() < 10) {
			monthString = "0" + dateModelSelected.date.getMonthValue();
		} else {
			monthString = String.valueOf(dateModelSelected.date.getMonthValue());
		}

		// add leading zero to day
		String dayString = "";
		if (dateModelSelected.date.getDayOfMonth() < 10) {
			dayString = "0" + dateModelSelected.date.getDayOfMonth();
		} else {
			dayString = String.valueOf(dateModelSelected.date.getDayOfMonth());
		}

		// add input field defaults
		inputTextTimeStart.setText("09:00 AM");
		inputTextTimeEnd.setText("05:30 PM");
		inputTextDate.setText(monthString + "/" + dayString + "/" + dateModelSelected.date.getYear());

		// add all elements
		popupPanel.add(labelEventTitle);
		popupPanel.add(inputTextEventTitle);
		popupPanel.add(labelTimeStart);
		popupPanel.add(inputTextTimeStart);
		popupPanel.add(labelTimeEnd);
		popupPanel.add(inputTextTimeEnd);
		popupPanel.add(labelEventDate);
		popupPanel.add(inputTextDate);
		popupPanel.add(cancel);
		popupPanel.add(save);

		add(popupPanel);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
