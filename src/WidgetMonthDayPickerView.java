import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @project Simple GUI Calendar
 * @author Branden Lee
 * @version 0.01
 */

/**
 * Widget
 * Month grid that allows individual days to be picked
 */
public class WidgetMonthDayPickerView {
	private JPanel monthPanel = new JPanel();
	private AppController appControllerObj;

	WidgetMonthDayPickerView(AppController appControllerObj_) {
		appControllerObj = appControllerObj_;
	}

	public void update(DateModel dateModelCurrent, DateModel dateModelDisplay) {
		ArrayList<JTextField> labelArray = new ArrayList<>();
		ArrayList<LocalDate> dateArray = new ArrayList<>();

		JLabel monthTitleText = new JLabel();

		monthPanel = new JPanel();

		monthPanel.setLayout(new BoxLayout(monthPanel, BoxLayout.Y_AXIS));
		monthPanel.setSize(400, 600);
		JPanel monthViewDays = new JPanel();
		monthViewDays.setLayout(new GridLayout(7, 7));

		monthTitleText.setText(dateModelDisplay.monthYearString());
		JPanel titleLine = new JPanel();
		titleLine.setLayout(new BoxLayout(titleLine, BoxLayout.X_AXIS));
		JTextField btnLeftArrow = new JTextField("<");
		JTextField btnRightArrow = new JTextField(">");
		btnLeftArrow.setEditable(false);
		btnRightArrow.setEditable(false);
		btnLeftArrow.setMaximumSize(new Dimension(22, 16));
		btnRightArrow.setMaximumSize(new Dimension(22, 16));

		// left arrow click
		btnLeftArrow.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				appControllerObj.doGridLeft();
			}
		});

		// right arrow click
		btnRightArrow.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				appControllerObj.doGridRight();
			}
		});
		// add title and arrows to the title line
		titleLine.add(monthTitleText);
		titleLine.add(btnLeftArrow);
		titleLine.add(btnRightArrow);
		monthPanel.add(titleLine);

		// Display week day symbols
		String[] weekDaySymArray = weekDaySymbol();
		for (int day = 1; day < 8; day++) {
			JLabel weekday = new JLabel(weekDaySymArray[day]);
			weekday.setHorizontalAlignment(SwingConstants.CENTER);
			monthViewDays.add(weekday);
		}

		// Display the 42 days on the grid
		LocalDate[] currentMonth = dateModelDisplay.getDateArray();
		for (LocalDate dateIt : currentMonth) {
			JTextField label = new JTextField(String.valueOf(dateIt.getDayOfMonth()));
			label.setEditable(false);
			label.setHorizontalAlignment(JTextField.CENTER);

			/*
			 * Todays date = gray
			 * Selected date = blue
			 */
			if (dateIt.equals(dateModelCurrent.date)) {
				label.setBackground(Color.gray);
			} else if (dateIt.equals(dateModelDisplay.date)) {
				label.setBackground(Color.blue);
			} else {
				label.setBackground(Color.white);
			}
			labelArray.add(label);
			dateArray.add(dateIt);

			// day clicked
			label.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					appControllerObj.doSelectDate(dateArray.get(labelArray.indexOf(label)));
				}
			});
			monthViewDays.add(label);
		}

		monthPanel.add(monthViewDays);
		monthPanel.revalidate();
		monthPanel.repaint();
	}

	public JPanel getPanel() {
		return monthPanel;
	}
	
	/**
	 * Short form of the weekday names
	 * 
	 * @return weekDayArray an array of weekday name strings
	 */
	public String[] weekDaySymbol() {
		DateFormatSymbols symbols = new DateFormatSymbols(SimpleCalendarTester.language);
		return symbols.getShortWeekdays();
	}
}
