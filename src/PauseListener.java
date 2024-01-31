import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PauseListener implements ActionListener {
	
	private Controller c;
	public JButton ps;

	public PauseListener(Controller c, JButton ps) {
		this.c = c;
		this.ps = ps;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		c.pause(ps);

	}

}
