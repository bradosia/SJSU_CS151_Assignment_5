
/**
 * @project Simple GUI Calendar
 * @author Branden Lee
 * @version 0.01
 */

import java.time.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

/**
 * Tester class with the main method
 */

public class SimpleCalendarTester {

	/*
	 * Global language setting
	 */
	public static Locale language = Locale.ENGLISH;

	public static void main(String[] args) {
		JFrame appFrame = new JFrame();

		AppController controllerObj = new AppController();
		appFrame.setLayout(new FlowLayout());
		appFrame.add(controllerObj.getPanel());

		// Finalize frame setup
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.pack();
		appFrame.setVisible(true);
	}
}
