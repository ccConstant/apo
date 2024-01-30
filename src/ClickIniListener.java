import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickIniListener implements MouseListener{
	
	Controller c;
	
	public ClickIniListener(Controller c) {
		this.c = c;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		c.changeStateClick(e.getX(), e.getY());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
