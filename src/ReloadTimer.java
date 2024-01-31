import java.util.Timer;
import java.util.TimerTask;

public class ReloadTimer {
    private Timer timer;
    private boolean isPaused;
    
    private int tps;
    private int nbIte;
	private Controller c;

    public ReloadTimer(int tps, int nbIte, Controller c) {
        timer = new Timer();
        isPaused = true;
        this.tps = tps;
        this.nbIte = nbIte;
        this.c = c;
    }

    public void startReload() {
    	
        timer.scheduleAtFixedRate(new TimerTask() {
            
            int cpt = 0;
            
            @Override
            public void run() {
                if (!isPaused) {
                    c.reload();
                    cpt++;
                    if (cpt >= nbIte) {
                        cancel(); // Annuler le timer après le nombre d'itérations
                    }
                }
            }
        }, 0, tps * 1000); 
    }

    public void pauseReload() {
        isPaused = true;
    }

    public void resumeReload() {
        isPaused = false;
    }
    
    public boolean getPaused() {
    	return isPaused;
    }

}
