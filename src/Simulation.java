
public interface Simulation {
	 
	
	/** 
     * Rechargement des états des cellules en suivant le principe du jeu choisit et affichage de l'automate
     */
	public abstract void rechargement();
	
	/**
     * Getter de l'automate
     * @return Automate
     */
	public abstract Automate getAutomate();
}
