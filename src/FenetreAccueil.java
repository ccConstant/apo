import java.awt.*;
import javax.swing.*;

/**
 * La classe FenetreAccueil représente la fenêtre d'accueil de l'application.
 */
public class FenetreAccueil extends JFrame{

	final static int HAUTEUR = 250; // Hauteur de la fenêtre
	final static int LARGEUR = 400; // Largeur de la fenêtre
	
	// Éléments de la fenêtre
	private JComboBox<String> cbAuto; // ComboBox pour choisir le type d'automate
	private JSlider slDim; // Slider pour choisir la dimension de l'automate
	private JTextField tfIte; // TextField pour saisir le nombre d'itérations
	private JTextField tfTmp; // TextField pour saisir le temps d'une itération
	private JSlider slX; // Slider pour choisir le nombre de colonnes
	private JSlider slY; // Slider pour choisir le nombre de lignes
	private JSlider slZ; // Slider pour choisir le nombre en Z
	
	/**
	 * Constructeur de la classe FenetreAccueil.
	 * @param c Le contrôleur de l'application.
	 */
	public FenetreAccueil(Controller c) {
		this.setTitle("Automate"); // Titre de la fenêtre
		this.setSize(LARGEUR,HAUTEUR); // Taille de la fenêtre
		
		this.getContentPane().setLayout(new BorderLayout()); // Layout de la fenêtre
		
		// Titre en haut de la fenêtre
		JLabel title =  new JLabel("Automates",SwingConstants.CENTER);
		this.add(title, BorderLayout.NORTH);
		
		// Grid contenant tous les éléments au milieu de la fenêtre
		JPanel gridMid = new JPanel(); 
		gridMid.setLayout(new GridLayout(8,2)); // Grid avec 8 lignes et 2 colonnes
		
		// ComboBox avec les types d'automates proposés
		JLabel lbAuto = new JLabel("Type de l'automate : ",SwingConstants.CENTER);
		String[] listAuto = { "Manuel", "1D", "Règle de majorité", "Jeu de la vie", "Feu de forêt" };
		cbAuto = new JComboBox<String>(listAuto);
		cbAuto.setSelectedIndex(0); // Par défaut, sélectionne le premier élément de la ComboBox
		
		gridMid.add(lbAuto);
		gridMid.add(cbAuto);
		
		// Slider pour choisir la dimension de l'automate
		JLabel lbDim = new JLabel("Dimension de l'automate : 1",SwingConstants.CENTER);
		slDim = new JSlider(1,3,1); // Slider avec des valeurs de 1 à 3, valeur par défaut : 1
		
		gridMid.add(lbDim);
		gridMid.add(slDim);
		
		// TextField pour saisir le nombre d'itérations
		JLabel lbIte = new JLabel("Nombre d'itération : ",SwingConstants.CENTER);
		tfIte = new JTextField("20"); // Valeur par défaut : 20
		
		gridMid.add(lbIte);
		gridMid.add(tfIte);
		
		// TextField pour saisir le temps d'une itération
		JLabel lbTmp = new JLabel("Temps d'une itération (s) : ",SwingConstants.CENTER);
		tfTmp = new JTextField("5"); // Valeur par défaut : 5
		
		gridMid.add(lbTmp);
		gridMid.add(tfTmp);
		
		// Taille de l'automate
		JLabel lbTaille = new JLabel("Taille de l'automate : ",SwingConstants.CENTER);
		gridMid.add(lbTaille);
		gridMid.add(new JLabel("(Nombre cellule max : ???)")); // Nombre de cellules maximum inconnu
		
		// Slider pour choisir le nombre de colonnes
		JLabel lbX = new JLabel("Nombre en X : 10", SwingConstants.CENTER);
        slX = new JSlider(5, 100, 10); // Slider avec des valeurs de 5 à 100, valeur par défaut : 10
        slX.addChangeListener(new SliderChangeListener(slX, lbX, 1)); // Ajout d'un écouteur de changement
        
        gridMid.add(lbX);
        gridMid.add(slX);

        // Slider pour choisir le nombre de lignes
        JLabel lbY = new JLabel("Nombre en Y : 10", SwingConstants.CENTER);
        slY = new JSlider(5, 100, 10); // Slider avec des valeurs de 5 à 100, valeur par défaut : 10
        slY.addChangeListener(new SliderChangeListener(slY, lbY, 2)); // Ajout d'un écouteur de changement
        
        gridMid.add(lbY);
        gridMid.add(slY);
        
        // Slider pour choisir le nombre en Z
        JLabel lbZ = new JLabel("Nombre en Z : 10", SwingConstants.CENTER);
        slZ = new JSlider(5, 100, 10); // Slider avec des valeurs de 5 à 100, valeur par défaut : 10
        slZ.addChangeListener(new SliderChangeListener(slZ, lbZ, 2)); // Ajout d'un écouteur de changement
        
        gridMid.add(lbZ);
        gridMid.add(slZ);
		
		this.add(gridMid, BorderLayout.CENTER); // Ajout du grid au centre de la fenêtre
		
		
		JPanel gridBas = new JPanel(); 
		gridBas.setLayout(new GridLayout(1,2)); // Grid avec 1 ligne et 2 colonnes
		
		// Boutons pour générer et quitter
		JButton generer = new JButton("Générer");
		JButton quitter = new JButton("Quitter");
		
		// Ajout des écouteurs d'événements aux boutons
		quitter.addActionListener(new QuitterListener(c));
		generer.addActionListener(new GenererListener(c, cbAuto));
		
		gridBas.add(generer);
		gridBas.add(quitter);
		
		this.add(gridBas, BorderLayout.SOUTH); // Ajout du grid en bas de la fenêtre
	}

	// Méthodes pour récupérer les valeurs des composants de la fenêtre
	
	public int getValX() {
		return slX.getValue();
	}
	public int getValY() {
		return slY.getValue();
	}
	public int getValZ() {
		return slZ.getValue();
	}
	
	public int getIte() {
		return Integer.parseInt(tfIte.getText());
	}
	public int getTps() {
		return Integer.parseInt(tfTmp.getText());
	}
	
}
