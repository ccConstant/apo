import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class FInitManuel2 extends JFrame implements FInit{

    private int rows;
    private int cols;
    private int z;
    private int dim;
    
    private int gridWidth;
    private int gridHeight;

    private Controller c;
    private DessinGrille dg;
    
    private Automate auto;
    
    private int[][] voisins;
    private ArrayList<State> arrayList;
	private JComboBox<State> CBState;
	
	private Map<String, State> regle;
    
	public FInitManuel2(Controller c, int cols, int rows, int[][] voisins, ArrayList<State> arrayList, int z, int dim) {
        this.c = c;
        this.rows = rows;
        this.cols = cols;
        this.z = z;
        this.dim = dim;
        this.gridWidth = 500; 
        this.gridHeight = 450; 
        
        regle = new HashMap<String, State>();
        
        this.arrayList = arrayList;
        this.voisins = voisins;

        setTitle("Choix états et voisins");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 560);
        setLocationRelativeTo(c.getAccueil());
        State def = arrayList.get(0);
        
        for(State state : arrayList) {
        	if(state.getByDefault()) {
        		def = state;
        	}
        }
        
        State rien =new State("rien", -1, -1, -1, false);
        ArrayList<State> states=new ArrayList<State>();
        states.addAll(arrayList);
        states.add(rien);
        switch(dim) {
        case 1 : auto = new Automate(2,states , null, 3);break;
        case 2 : auto = new Automate(2,states , null, 3, 3);break;
        case 3 : auto = new Automate(2,states , null, 3, 3, 3);break;
        }
        auto.initCellules(rien);
        for (int i = 0; i<voisins.length;i++) {
        	Cellule cell = auto.getCelluleFromPosition( voisins[i][1]+1, voisins[i][0]+1, false);
        	System.out.println(voisins.length);
        	cell.setCurrentState(def);
        }
        auto.print();
        
        elementFenetre();

        setVisible(true);
        
	}

	public void elementFenetre() {
        this.getContentPane().setLayout(new BorderLayout());
        
        JPanel grid = new JPanel();
        grid.setLayout(new GridLayout(2, 1));
        
       
        
        
        
        dg = new DessinGrille(auto.getLongueur(), auto.getLargeur(), gridWidth, gridHeight, auto);
        dg.setFocusable(true);
        dg.addMouseListener(new ClickIniListener(c));
        add(dg, BorderLayout.CENTER);
        
        JPanel grid1 = new JPanel();
        grid1.setLayout(new GridLayout(1, 3, 10, 5));
        
        grid1.add(new JLabel(" --->"));
        
        CBState = new JComboBox<State>();
        
        for (State state : arrayList) {
        	CBState.addItem(state);
        }
        
        grid1.add(CBState);
        
        JButton add = new JButton("Ajouter");
        add.addActionListener(e -> {
        	this.ajouterRegle();
        });
        
        grid1.add(add);
        
        grid.add(grid1);
        
        JButton opt = new JButton("Lancer la simulation");
        opt.addActionListener(e -> {
        	c.initManuel(voisins, arrayList, regle);
        });
        JButton quit = new JButton("Quitter");
        quit.addActionListener(e -> {
        	this.dispose();
        });
        JPanel grid2 = new JPanel();
        grid2.setLayout(new GridLayout(1, 2));
		grid2.add(opt);
        grid2.add(quit);
        
        grid.add(grid2);
        add(grid, BorderLayout.SOUTH);
    }
	
    private void ajouterRegle() {
    	String key = "";
    	for (int i = 0; i<voisins.length;i++) {
        	Cellule cell = auto.getCelluleFromPosition( voisins[i][1]+1, voisins[i][0]+1, false);
        	key += cell.getCurrentState().getState();
        }
    	regle.put(key, (State)CBState.getSelectedItem());
    	System.out.println(regle);
	}

	public DessinGrille getDessin() {
    	return dg;
    }
    
	public Simulation getSimu() {
		
		return null;
	}
	
	public Automate getAuto() {
		return auto;
		
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
