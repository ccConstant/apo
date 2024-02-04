import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * ActionListener pour la génération d'une grille en fonction du type sélectionné.
 */
public class GenererListener implements ActionListener {
    private Controller c; // Contrôleur de l'application
    private JComboBox<String> type; // JComboBox contenant les types de grille

    /**
     * Constructeur de GenererListener.
     *
     * @param c     Le contrôleur de l'application.
     * @param cbAuto Le JComboBox contenant les types de grille.
     */
    public GenererListener(Controller c, JComboBox<String> cbAuto) {
        this.c = c;
        this.type = cbAuto;
    }

    /**
     * Méthode appelée lorsqu'un événement se produit.
     *
     * @param e L'événement d'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        c.generer(type.getSelectedItem().toString()); // Lance la fonction genererGrille de la fenêtre principale
    }
}
