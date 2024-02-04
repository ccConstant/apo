import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class FInitManuel3 extends JFrame implements FInit{

    private int rows;
    private int cols;
    private int z;
    private int dim;
    
    private int gridWidth;
    private int gridHeight;

    private Controller c;
    private DessinGrille dg;
        
    private int[][] voisins;
    private ArrayList<State> arrayList;
	
	private Map<String, State> regle;
	
	private Simulation sim; 
    
	public FInitManuel3(Controller c, int cols, int rows, int[][] voisins, ArrayList<State> arrayList, Map<String, State> regle, int z, int dim) {
        this.c = c;
        this.rows = rows;
        this.cols = cols;
        this.z = z;
        this.dim = dim;
        this.gridWidth = 600; 
        this.gridHeight = 450; 
        
        this.arrayList = arrayList;
        this.voisins = voisins;
        this.regle = regle;

        setTitle("Choix états et voisins");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 560);
        setLocationRelativeTo(c.getAccueil());
        
        
        
        sim = new SimulationManuelle();
        switch(dim) {
        case 1 : ((SimulationManuelle)sim).init_simulation(2, arrayList, voisins, cols, regle, voisins.length);break;
        case 2 : ((SimulationManuelle)sim).init_simulation(2, arrayList, voisins, cols, rows, regle, voisins.length);break;
        case 3 : ((SimulationManuelle)sim).init_simulation(2, arrayList, voisins, cols, rows, z, regle, voisins.length);break;
        }
        
        elementFenetre();

        setVisible(true);
        
	}

	public void elementFenetre() {
        this.getContentPane().setLayout(new BorderLayout());
        
        
        dg = new DessinGrille(rows, cols, gridWidth, gridHeight, sim.getAutomate());
        dg.setFocusable(true);
        dg.addMouseListener(new ClickIniListener(c));
        add(dg, BorderLayout.CENTER);
        
        
        JButton opt = new JButton("Lancer la simulation");
        opt.addActionListener(e -> {
        	c.lancer();
        });
        JButton quit = new JButton("Quitter");
        quit.addActionListener(e -> {
        	this.dispose();
        });
        JPanel grid2 = new JPanel();
        grid2.setLayout(new GridLayout(1, 2));
		grid2.add(opt);
        grid2.add(quit);
        
        add(grid2, BorderLayout.SOUTH);
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
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		
	}
	
	
	
	
	
}
