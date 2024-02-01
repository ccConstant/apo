import java.awt.*;
import javax.swing.*;

public class FenetreAccueil extends JFrame{

	final static int HAUTEUR = 250;
	final static int LARGEUR = 400;
	
	private JComboBox<String> cbAuto;
	private JSlider slDim;
	
	private JTextField tfIte;
	private JTextField tfTmp;
	
	
	private JSlider slX;
	private JSlider slY;
	private JSlider slZ;
	
	
	
	public FenetreAccueil(Controller c) {
		this.setTitle("Automate");
		this.setSize(LARGEUR,HAUTEUR);
		
		this.getContentPane().setLayout(new BorderLayout());
		
		//titre en haut
		JLabel title =  new JLabel("Automates",SwingConstants.CENTER);
		this.add(title, BorderLayout.NORTH);
		
		//grid contenant tous les élements du mileu de la fenetre
		JPanel gridMid = new JPanel(); 
		gridMid.setLayout(new GridLayout(8,2));
		
		//Combobox avec les types d'automates proposé
		JLabel lbAuto = new JLabel("Type de l'automate : ",SwingConstants.CENTER);
		String[] listAuto = { "Manuel", "1D", "Règle de majorité", "Jeu de la vie", "Feu de forêt" };
		cbAuto = new JComboBox<String>(listAuto);
		cbAuto.setSelectedIndex(0);
		
		gridMid.add(lbAuto);
		gridMid.add(cbAuto);
		
		//slider dimension
		JLabel lbDim = new JLabel("Dimension de l'automate : 1",SwingConstants.CENTER);
		slDim = new JSlider(1,3,1);
		//slDim.addChangeListener(new SliderChangeListener(slDim,lbDim,3));
		
		gridMid.add(lbDim);
		gridMid.add(slDim);
		
		//TextField itération
		JLabel lbIte = new JLabel("Nombre d'itération : ",SwingConstants.CENTER);
		tfIte = new JTextField("20");
		
		gridMid.add(lbIte);
		gridMid.add(tfIte);
		
		//TextField temps itération
		JLabel lbTmp = new JLabel("Temps d'une itération (s) : ",SwingConstants.CENTER);
		tfTmp = new JTextField("5");
				
		gridMid.add(lbTmp);
		gridMid.add(tfTmp);
		
		//Taille de l'automate
		JLabel lbTaille = new JLabel("Taille de l'automate : ",SwingConstants.CENTER);
		gridMid.add(lbTaille);
		gridMid.add(new JLabel("(Nombre cellule max : ???)"));
		
		// Slider 1 : colonnes
        JLabel lbX = new JLabel("Nombre en X : 10", SwingConstants.CENTER);
        slX = new JSlider(5, 100, 10);
        slX.addChangeListener(new SliderChangeListener(slX, lbX, 1));
        gridMid.add(lbX);
        gridMid.add(slX);

        // Slider 2 : lignes
        JLabel lbY = new JLabel("Nombre en Y : 10", SwingConstants.CENTER);
        slY = new JSlider(5, 100, 10);
        slY.addChangeListener(new SliderChangeListener(slY, lbY, 2));
        gridMid.add(lbY);
        gridMid.add(slY);
        
     // Slider 2 : hauteur
        JLabel lbZ = new JLabel("Nombre en Z : 10", SwingConstants.CENTER);
        slZ = new JSlider(5, 100, 10);
        slZ.addChangeListener(new SliderChangeListener(slZ, lbZ, 2));
        gridMid.add(lbZ);
        gridMid.add(slZ);
		
		this.add(gridMid, BorderLayout.CENTER);
		
		
		JPanel gridBas = new JPanel(); 
		gridBas.setLayout(new GridLayout(1,2));
		
		JButton generer = new JButton("Générer");
		//restart.addActionListener(new RejouerListener(this));
		JButton quitter = new JButton("Quitter");
		//save.addActionListener(new SaveListener(this));
		gridBas.add(generer);
		gridBas.add(quitter);
		
		quitter.addActionListener(new QuitterListener(c));
		generer.addActionListener(new GenererListener(c, cbAuto));
		
		this.add(gridBas, BorderLayout.SOUTH);
	}




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
