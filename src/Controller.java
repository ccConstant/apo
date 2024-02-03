import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;

public class Controller {

	private FenetreAccueil fa;
	private FGrille fg;
	private FInit fi;
	
	private ReloadTimer rt;
	private boolean hexa;
	
	public Controller() {
		fa = new FenetreAccueil(this);
		hexa = false;
		
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
	
	public void generer(String type) {
		fa.setVisible(false);
		int x = fa.getValX();
		int y = fa.getValY();
		int z = fa.getValZ();
		
		switch(type) {
		case "Jeu de la vie" : fi = new FInitLife(this, x, y);break;
		case "Feu de forêt" : fi = new FInitLife(this, x, y);break;
		case "Règle de majorité" : fi = new FInitLife(this, x, y);break;
		case "1D" : fi = new FInit1D(this, x, 1);break;
		case "Manuel" : fi = new FInitManuel1(this, x, y);break;
		}
		
		
		int tps = fa.getTps();
		int ite = fa.getIte();
		rt = new ReloadTimer(tps, ite, this);
		

		
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
		Automate auto;
		if((!(fi instanceof FInitManuel1)) && (!(fi instanceof FInitManuel2))) {
			auto = fi.getSimu().getAutomate();
		} else if(fi instanceof FInitManuel1) {
			auto = ((FInitManuel1)fi).getAuto();
		} else {
			auto = ((FInitManuel2)fi).getAuto();
		}
		
		int size = fi.getDessin().getCellSize();
		int x = (xClick-10)/size;
		int y = (yClick-10)/size;
		if (x < fi.getCols() && y < fi.getRows()) {
			ArrayList<State> states = auto.getStates();
			Cellule target = auto.getCelluleFromPosition(x, y, hexa);
			State next = states.get((states.indexOf(target.getCurrentState())+1)%states.size());
			System.out.println(next.getR());
			if(next.getR() == -1) {
				next = states.get((states.indexOf(target.getCurrentState())+2)%states.size());
				System.out.println(next);
			}
			if(target.getCurrentState().getR() != -1) {
				target.setCurrentState(next);
			}
			
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
	
	public void setHexa(boolean b) {
		hexa = b;
	}

	public void nextManuel(int[][] voisins, ArrayList<State> arrayList) {
		fi.setVisible(false);
		fi = new FInitManuel2(this,fa.getValX(), fa.getValY() , voisins, arrayList);
		fi.setVisible(true);
	}
	
	
	

}
