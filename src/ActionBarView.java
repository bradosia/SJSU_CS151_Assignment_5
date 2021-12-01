import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.time.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This class is the main view and controller of the project. All panels
 * are created or composed in this class.
 * 
 * @author Tim Roesner
 * @version 1.0
 */

public class ActionBarView extends JPanel implements ChangeListener {

	private JPanel actionBarPanel;
	private AppController appControllerObj;

	/**
	 * Constructs a ControllView object.
	 * 
	 * @param month takes a month with a date
	 */
	public ActionBarView(AppController appControllerObj_) {
		appControllerObj = appControllerObj_;
		update();
	}

	public void update() {
		actionBarPanel = new JPanel();
		JButton btnLeft = new JButton("<");
		JButton btnRight = new JButton(">");
		JButton btnCreate = new JButton("Create");
		JButton btnToday = new JButton("Today");
		JButton btnQuit = new JButton("Quit");

		// Action for today button, sets date of month to today
		btnToday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appControllerObj.doToday();
			}
		});

		// Action for left arrow button
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appControllerObj.doGridLeft();
			}
		});

		// Action for right arrow button
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appControllerObj.doGridRight();
			}
		});

		// Action for create button, opens new frame: CreateView
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appControllerObj.createEvent();
			}
		});

		// Quit button, saves events and closes frame
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				appControllerObj.doExit();
			}
		});

		/*
		 * Add buttons to the action bar
		 */
		actionBarPanel.add(btnToday);
		actionBarPanel.add(btnLeft);
		actionBarPanel.add(btnRight);
		actionBarPanel.add(btnCreate);
		actionBarPanel.add(btnQuit);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		update();
	}

	public JPanel getPanel() {
		return actionBarPanel;
	}
}
