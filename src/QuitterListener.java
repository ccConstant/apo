import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitterListener implements ActionListener{
	
	private Controller c;
	
	public QuitterListener(Controller c) {
		this.c = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		c.quitter();
	}
}
