import java.util.ArrayList;
import java.util.Random;


public class Automate {
    int d;
    ArrayList<State> states= new ArrayList<State>() ;
    int voisins[][]; // doit être de taille [nombreVoisins][d]
    ArrayList<Cellule> cellules = new ArrayList<Cellule>() ;
    int longueur ;
    int largeur;
    int nombreVoisins=0;
    

    /** 
     * Constructeur
     * @param d dimension de l'automate
     * @param states états dans lesquels les cellules de l'automate peuvent être
     * @param voisins position des différentes voisins d'une cellule
     * @param longueur de la grille
     * @param largeur de la grille
     */
    public Automate(int d, ArrayList<State> states, int voisins[][], int longueur, int largeur) {
        this.d = 0; 
        this.states = states;
        this.voisins = voisins; 
        this.longueur=longueur;
        this.largeur=largeur;
    }

    /** 
     * Affiche un automate : pour cela on affiche toutes les cellules de l'automate en question 
     */
    public void print() { 
    	for (int i=1 ; i<=cellules.size(); i++) {
        	if (i%(largeur)==0) {
        		System.out.println(cellules.get(i-1).toString());
        	}else {
        		System.out.print(cellules.get(i-1).toString());
        	}
        }
    }
    
    /** 
     * Initialise l'état de toutes cellules de l'automate à l'état passé en paramètre
     * @param s état que l'on veut attribuer à toutes les cellules de l'automate 
     */
    public void initCellules(State s) {
    	System.out.println("longueur"+longueur);
		for (int i=0 ; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				int position[]=new int[2];
				position[0]=j;
				position[1]=i;	
				Cellule c=new Cellule(s, position);
				cellules.add(c);
				
			}
		}
    }
    
    /** 
     * Initialise aléatoirement l'état de toutes cellules de l'automate à un des états appartenant à la liste d'états passée en paramètre
     * @param states ArrayList d'états que l'on peut attribuer aux cellules de l'automate
     */
    public void initCellulesRandom(ArrayList<State> states) {
		for (int i=0 ; i<longueur; i++) {
			for (int j=0; j<largeur; j++) {
				int position[]=new int[2];
				position[0]=j;
				position[1]=i;	
				Random random=new Random();
				int index=(random.nextInt(states.size()));
				Cellule c=new Cellule(states.get(index), position);
				cellules.add(c);
			}
		}
    }
    
    /**
     * Getter des états de l'automate
     * @return ArrayList<State> list d'états de l'automate
     */
    public ArrayList<State> getStates(){
    	return states;
    }
    
    /**
     * Getter des cellules de l'automate
     * @return ArayList<Cellule> list de cellules de l'automate
     */
    public ArrayList<Cellule> getCellules(){
    	return cellules;
    }
    
    /**
     * Getter de la longueur du cadre
     * @return int longueur du cadre
     */
    public int getLongueur(){
    	return longueur;
    }
    
    /**
     * Getter de la largeur du cadre
     * @return int largeur du cadre
     */
    public int getLargeur(){
    	return largeur;
    }

    /**
     * Getter d'une cellule de l'automate
     * @param x abscisse de la cellule à récupérer
     * @param y ordonnée de la cellule à récupérer
     * @return Cellule une cellule de l'automate
     */
    public Cellule getCelluleFromPosition(int x, int y, boolean hexa) {
    	if (hexa) {
    		if (x >=0 && y >=0 && x<largeur*2 && y<longueur ) {
    			int i= x/2 ; 
        		return cellules.get(y*largeur+i);
        	}else {
        		return null ; 
        	}
    	}
    	if (x >=0 && y >=0 && x<largeur && y<longueur ) {
    		return cellules.get(y*largeur+x);
    	}else {
    		return null ; 
    	}
    	
    }
    
    /**
     * Getter de la cellule située au nord de la cellule traitée
     * @param x abscisse de la cellule dont on cherche la voisine du nord
     * @param y ordonnée de la cellule dont on cherche la voisine du nord 
     * @return Cellule une cellule de l'automate
     */
    public Cellule getCelluleNord(int x, int y, boolean hexa) {
    	if (hexa) {
    		return getCelluleFromPosition(x, y-2, hexa);
    	}
    	return getCelluleFromPosition(x, y-1, hexa);
    	
    }
    
    /**
     * Getter de la cellule située au sud de la cellule traitée
     * @param x abscisse de la cellule dont on cherche la voisine du sud
     * @param y ordonnée de la cellule dont on cherche la voisine du sud 
     * @return Cellule une cellule de l'automate
     */
    public Cellule getCelluleSud(int x, int y, boolean hexa) {
    	if (hexa) {
    		return getCelluleFromPosition(x, y+2, hexa);
    	}
    	return getCelluleFromPosition(x, y+1, hexa);
    	
    }
    
    /**
     * Getter de la cellule située à l'est de la cellule traitée
     * @param x abscisse de la cellule dont on cherche la voisine de l'est
     * @param y ordonnée de la cellule dont on cherche la voisine de l'est 
     * @return Cellule une cellule de l'automate
     */
    public Cellule getCelluleEst(int x, int y, boolean hexa) {
    	if (hexa) {
    		return getCelluleFromPosition(x+2, y, hexa);
    	}
    	return getCelluleFromPosition(x+1, y, hexa);
    	
    }
    
    /**
     * Getter de la cellule située à l'ouest de la cellule traitée
     * @param x abscisse de la cellule dont on cherche la voisine de l'ouest
     * @param y ordonnée de la cellule dont on cherche la voisine de l'ouest
     * @return Cellule une cellule de l'automate
     */
    public Cellule getCelluleOuest(int x, int y, boolean hexa) {
    	if (hexa) {
    		return getCelluleFromPosition(x-2, y, hexa);
    	}
    	return getCelluleFromPosition(x-1, y, hexa);
    	
    }
    
    /**
     * Getter des différentes voisins d'une cellule
     * @param c cellule dont veut récupérer les voisins
     * @return ArrayList<Cellule> une liste de Cellule contenant les voisins de la cellule passée en paramètre
     */
    public ArrayList<Cellule> getVoisins2D(Cellule c, boolean hexa) {
    	int[] position=c.getPosition();
    	ArrayList<Cellule> cellulesVoisines=new ArrayList<Cellule>() ; 
    	for (int i=0 ; i<nombreVoisins ; i++) {
    		int abscisse=position[0]+voisins[i][0]; 
    		int ordonnee= position[1]+voisins[i][1] ; 
			Cellule a=getCelluleFromPosition(abscisse, ordonnee, hexa); 
			if (a!=null) {
				cellulesVoisines.add(a);
			}
    	}
    	return cellulesVoisines;
    }
    
    /**
     * Calcule le nombre cellules voisines qui se trouvent dans l'état passé en paramètre 
     * @param s état dans lequel se trouve les cellules comptabilisées
     * @param c cellule dont veut analyser les voisins
     * @return int le nombre de cellules voisines de la cellule c se trouvant dans l'état s 
     */
    public int nbrVoisinsInState(State s, Cellule c, boolean hexa) {
    	ArrayList<Cellule> voisins=this.getVoisins2D(c, hexa);
		int cpt=0 ; 
		for (Cellule voisin : voisins) {
			if (voisin.getCurrentState().getState()==s.getState()) {
				cpt++;
			}
		}
		return cpt ; 
		
    }
    
    
    /**
     * Calcule le nombre de cellules de l'automate s qui sont dans l'état s
     * @param s état dans lequel se trouve les cellules comptabilisées
     * @return int le nombre de cellules de l'automate se trouvant dans l'état s 
     */
    public int nbrCellulesInState(State s) {
		int cpt=0 ; 
		for (Cellule cellule : cellules) {
			if (cellule.getCurrentState().getState().equals(s.getState())) {
				cpt++;
			}
		}
		return cpt ; 
		
    }

    /**
     * Calcule la position des 9 voisins
     */
    public void position9Voisins2D() {
    	nombreVoisins=9 ; 
    	voisins[0][0]=0 ; 
		voisins[0][1]=0; 
		voisins[1][0]=-1 ; 
		voisins[1][1]=-1 ; 
		voisins[2][0]=0 ; 
		voisins[2][1]=-1 ; 
		voisins[3][0]=1; 
		voisins[3][1]=-1; 
		voisins[4][0]=-1; 
		voisins[4][1]=0; 
		voisins[5][0]=1;  
		voisins[5][1]=0;  
		voisins[6][0]=-1;  
		voisins[6][1]=1; 
		voisins[7][0]=0;  
		voisins[7][1]=1; 
		voisins[8][0]=1;  
		voisins[8][1]=1; 	
    }
    
    /**
     * Calcule la position des 5 voisins
     */
    public void position5Voisins2D() {
    	nombreVoisins=5 ; 
    	voisins[0][0]=0 ; 
		voisins[0][1]=0; 
		voisins[1][0]=0 ; 
		voisins[1][1]=-1 ; 
		voisins[2][0]=-1; 
		voisins[2][1]=0; 
		voisins[3][0]=1;  
		voisins[3][1]=0;  
		voisins[4][0]=0;  
		voisins[4][1]=1;  	
    }
    
    /**
     * Calcule la position des 5 voisins
     */
    public void position7Voisins2D() {
    	nombreVoisins=5 ; 
    	//0;0
    	voisins[0][0]=0 ; 
		voisins[0][1]=0; 
		
		//voisin du haut
		voisins[1][0]=0 ; 
		voisins[1][1]=-2 ; 
		
		//voisin du bas 
		voisins[2][0]=0; 
		voisins[2][1]=2; 
		
		//voisin à droite 
		voisins[3][0]=1;  
		voisins[3][1]=1;
		
		voisins[4][0]=1;  
		voisins[4][1]=-1;  
		
		//voisin à gauche 
		voisins[3][0]=-1;  
		voisins[3][1]=1;
		
		voisins[4][0]=-1;  
		voisins[4][1]=-1; 
    }
    

    /** 
     * Initialise l'état de toutes cellules en mode hexagone de l'automate à l'état passé en paramètre
     * @param s état que l'on veut attribuer à toutes les cellules de l'automate 
     */
	public void initCellulesHexa(State s) {
		for (int i=0 ; i<longueur; i++) {
			if (i%2==1) {
				for (int j=1; j<2*largeur; j+=2) {
					int position[]=new int[2];
					position[0]=j;
					position[1]=i;	
					Cellule c=new Cellule(s, position);
					cellules.add(c);
				}
			}else {
				for (int j=0; j<2*largeur; j+=2) {
					int position[]=new int[2];
					position[0]=j;
					position[1]=i;	
					Cellule c=new Cellule(s, position);
					cellules.add(c);
				}
			}
		}
	}
}