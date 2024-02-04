import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener pour le bouton de quitter.
 */
public class QuitterListener implements ActionListener {
	
    // Le contrôleur de l'application
    private Controller c;

    /**
     * Constructeur de QuitterListener.
     *
     * @param c Le contrôleur de l'application.
     */
    public QuitterListener(Controller c) {
        this.c = c;
    }

    /**
     * Méthode appelée lorsqu'un événement d'action se produit.
     *
     * @param e L'événement d'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        c.quitter(); // Appelle la méthode quitter du contrôleur
    }
}
