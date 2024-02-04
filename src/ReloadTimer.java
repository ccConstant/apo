import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe représentant un minuteur pour recharger des éléments périodiquement.
 */
public class ReloadTimer {
    private Timer timer;
    private boolean isPaused;
    
    private int tps;
    private int nbIte;
    private Controller c;

    /**
     * Constructeur de la classe ReloadTimer.
     *
     * @param tps Le temps en secondes entre chaque rechargement.
     * @param nbIte Le nombre d'itérations avant d'arrêter le minuteur.
     * @param c Le contrôleur de l'application.
     */
    public ReloadTimer(int tps, int nbIte, Controller c) {
        timer = new Timer();
        isPaused = true;
        this.tps = tps;
        this.nbIte = nbIte;
        this.c = c;
    }

    /**
     * Démarre le rechargement périodique.
     */
    public void startReload() {
        timer.scheduleAtFixedRate(new TimerTask() {
            int cpt = 0;
            
            @Override
            public void run() {
                if (!isPaused) {
                    c.reload(); // Recharge périodiquement
                    cpt++;
                    if (cpt >= nbIte) {
                        cancel(); // Annuler le timer après le nombre d'itérations
                    }
                }
            }
        }, 0, tps * 1000); // Convertit le temps en millisecondes
    }

    /**
     * Met en pause le rechargement périodique.
     */
    public void pauseReload() {
        isPaused = true;
    }

    /**
     * Reprend le rechargement périodique.
     */
    public void resumeReload() {
        isPaused = false;
    }
    
    /**
     * Retourne l'état de pause du rechargement.
     *
     * @return true si le rechargement est en pause, sinon false.
     */
    public boolean getPaused() {
        return isPaused;
    }
}
