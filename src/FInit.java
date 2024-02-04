/**
 * L'interface FInit définit les méthodes nécessaires pour initialiser une fenêtre.
 */
public interface FInit {
    /**
     * Récupère le composant de dessin de la grille.
     * @return Le composant de dessin de la grille.
     */
    public DessinGrille getDessin();

    /**
     * Récupère la simulation associée à la fenêtre.
     * @return La simulation associée à la fenêtre.
     */
    public Simulation getSimu();

    /**
     * Récupère le nombre de lignes de la grille.
     * @return Le nombre de lignes de la grille.
     */
    public int getRows();

    /**
     * Récupère le nombre de colonnes de la grille.
     * @return Le nombre de colonnes de la grille.
     */
    public int getCols();

    /**
     * Définit la visibilité de la fenêtre.
     * @param b La visibilité de la fenêtre.
     */
    public void setVisible(boolean b);
}
