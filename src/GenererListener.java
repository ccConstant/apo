import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GenererListener implements ActionListener {
	//Variable contenant la fenêtre principale
	private Controller c;
	
	public GenererListener (Controller c) {
		this.c = c;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		c.generer(); //lance la fonction genererGrille de la fenêtre principale
	}
	

}
