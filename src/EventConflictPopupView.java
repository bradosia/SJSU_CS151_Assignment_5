
/**
 * @project Simple GUI Calendar
 * @author Branden Lee
 * @version 0.01
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * A popup for a schedule conflict
 */
public class EventConflictPopupView extends JFrame {

	/**
	 * Constructs an AlertView object.
	 */
	public EventConflictPopupView() {
		JPanel popupPanel = new JPanel();
		popupPanel.setBorder(BorderFactory.createEmptyBorder(11, 11, 11, 11));
		popupPanel.setLayout(new BoxLayout(popupPanel, BoxLayout.Y_AXIS));
		JLabel messageText = new JLabel("The event has a schedule conflict");
		
		// button + listener
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// do adds
		popupPanel.add(messageText);
		popupPanel.add(btnOk);
		add(popupPanel);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
}
