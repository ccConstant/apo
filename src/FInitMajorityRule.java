import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FInitMajorityRule extends JFrame implements FInit{
    static int HAUTEUR = 600;
    final static int LARGEUR = 900;

	private Controller c;
	private int rows;
	private int cols;
	private Simulation sim;
	private DessinGrille dg;
	private int gridWidth;
	private int gridHeight;

    public FInitMajorityRule(Controller c, int cols, int rows) {
    	
    	this.c = c;
        this.rows = rows;
        this.cols = cols;

        this.gridWidth = 400; 
        this.gridHeight = 400; 
        
        setTitle("Paramètres: Automate régle de la majorité: ");
        setSize(LARGEUR, HAUTEUR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        /*sim = new SimulationMajorityRule();
        ((SimulationMajorityRule())sim).init_simulation(rows, cols, null, null, null, null, );*/

        JPanel mainPanel = new JPanel(new BorderLayout());   
        dg = new DessinGrille(rows, cols, gridWidth, gridHeight, sim.getAutomate());
        dg.setFocusable(true);
        dg.addMouseListener(new ClickIniListener(c));
        mainPanel.add(dg, BorderLayout.NORTH);     
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        
        
        getContentPane().add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel gridBtn = new JPanel();
        gridBtn.setLayout(new GridLayout(1, 2));

        JButton lancer = new JButton("Lancer");
        lancer.addActionListener(e -> {

        });

        JButton fermer = new JButton("Quitter");
        fermer.addActionListener(e -> dispose());

        gridBtn.add(lancer);
        gridBtn.add(fermer);

        return gridBtn;
    }
	
    public DessinGrille getDessin() {
    	return dg;
    }
    
	public Simulation getSimu() {
		
		return sim;
	}
	
	public int getRows() {
		return rows;
	}
    
	public int getCols() {
		return cols;
	}

}
