import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;

public class Controller {

	private FenetreAccueil fa;
	private FGrille fg;
	private FInitLife fi;
	
	private ReloadTimer rt;
	
	public Controller() {
		fa = new FenetreAccueil(this);
		
	}

	public void afficherAccueil() {
		fa.setVisible(true);
		fa.setLocation(600,250);
		fa.setAlwaysOnTop(true);
	}

	public void quitter() {
		fa.dispose();
	}

	public Component getAccueil() {
		return fa;
	}
	
	public void generer() {
		fa.setVisible(false);
		int x = fa.getValX();
		int y = fa.getValY();
		int z = fa.getValZ();
		
		int tps = fa.getTps();
		int ite = fa.getIte();
		rt = new ReloadTimer(tps, ite, this);
		fi = new FInitLife(this, x, y);
		
	}

	public void reload() {
		fg.getSimu().rechargement();
		fg.getDessin().repaint();
		
	}
	
	public void pause(JButton ps) {
		if(rt.getPaused()) {
			rt.resumeReload();
			ps.setText("Pause");
		}else {
			rt.pauseReload();
			ps.setText("Reprendre");
		}
	}
	
	public void parametre() {
		
	}
	
	public void changeStateClick(int xClick, int yClick) {
		int size = fi.getDessin().getCellSize();
		int x = (xClick-10)/size;
		int y = (yClick-10)/size;
		System.out.println(x);
		System.out.println(y);
		if (x < fi.getCols() && y < fi.getRows()) {
			ArrayList<State> states = fi.getSimu().getAutomate().getStates();
			Cellule target = fi.getSimu().getAutomate().getCelluleFromPosition(x, y);
			State next = states.get((states.indexOf(target.getCurrentState())+1)%states.size());
			System.out.println(target);
			target.setCurrentState(next);
			fi.getDessin().repaint();
		}
	}

	public void lancer() {
		fi.setVisible(false);
		int x = fa.getValX();
		int y = fa.getValY();
		int z = fa.getValZ();
		fg = new FGrille(this, x, y, fi.getSimu());
		rt.startReload();
		
	}
	

}
