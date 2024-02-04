import java.util.*;


public class Automate {
    int d;
    ArrayList<State> states= new ArrayList<State>() ;
    int voisins[][]; // doit être de taille [nombreVoisins][d]
    ArrayList<Cellule> cellules = new ArrayList<Cellule>() ;
    int longueur=1;
    int largeur=1;
    int nombreVoisins=0;
    int z=1; 
    
    /** 
     * Constructeur
     * @param d dimension de l'automate
     * @param states états dans lesquels les cellules de l'automate peuvent être
     * @param voisins position des différentes voisins d'une cellule
     * @param largeur de la grille
     */
    public Automate(int d, ArrayList<State> states, int voisins[][], int largeur) {
        this.d = d; 
        this.states = states;
        this.voisins = voisins; 
        this.largeur=largeur;
    }
    
    /** 
     * Constructeur
     * @param d dimension de l'automate
     * @param states états dans lesquels les cellules de l'automate peuvent être
     * @param voisins position des différentes voisins d'une cellule
     * @param longueur de la grille
     * @param largeur de la grille
     */
    public Automate(int d, ArrayList<State> states, int voisins[][], int longueur, int largeur) {
        this.d = d; 
        this.states = states;
        this.voisins = voisins; 
        this.longueur=longueur;
        this.largeur=largeur;
    }
    
    /** 
     * Constructeur
     * @param d dimension de l'automate
     * @param states états dans lesquels les cellules de l'automate peuvent être
     * @param voisins position des différentes voisins d'une cellule
     * @param longueur de la grille
     * @param largeur de la grille
     * @param z de la grille
     */
    public Automate(int d, ArrayList<State> states, int voisins[][], int longueur, int largeur, int z) {
        this.d = d; 
        this.states = states;
        this.voisins = voisins; 
        this.longueur=longueur;
        this.largeur=largeur;
        this.z=z;
    }
    
    /**
     * Setter du nombre de voisin considéré
     * @param nombre de voisins à considérer
     */
    public void setNombreVoisins(int nombreVoisins) {
    	this.nombreVoisins=nombreVoisins;
    }
    
    /**
     * Getter du nombre de voisin
     * @return int nombre de voisins d'une cellule
     */
    public int getNombreVoisins() {
    	return nombreVoisins;
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
     * Getter de la dimension de l'automate
     * @return int dimension de l'automate
     */
    public int getDimension(){
    	return d;
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
     * Getter d'une cellule de l'automate
     * @param x abscisse de la cellule à récupérer
     * @param y ordonnée de la cellule à récupérer
     * @param z hauteur de la cellule à récupérer
     * @return Cellule une cellule de l'automate
     */
    public Cellule getCelluleFromPosition(int x, int y,int z, boolean hexa) {
    	if (hexa) {
    		if (x >=0 && y >=0 && x<largeur*2 && y<longueur ) {
    			int i= x/2 ; 
        		return cellules.get(y*largeur+i);
        	}else {
        		return null ; 
        	}
    	}
    	if (x >=0 && y >=0 && x<largeur && y<longueur && z>=0 && z<this.z) {
    		return cellules.get(z * (largeur * this.z)+y*largeur+x);
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
     * @param hexa est-ce qu'il s'agit d'une grille hexagonale?
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
     * @param hexa est-ce qu'il s'agit d'une grille hexagonale?
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
     * Getter des différentes voisins d'une cellule 
     * @param c cellule dont veut récupérer les voisins
     * @return ArrayList<Cellule> une liste de Cellule contenant les voisins de la cellule passée en paramètre
     */
    public ArrayList<Cellule> getVoisins3D(Cellule c) {
    	int[] position=c.getPosition();
    	ArrayList<Cellule> cellulesVoisines=new ArrayList<Cellule>() ; 
    	for (int i=0 ; i<nombreVoisins ; i++) {
    		int abscisse=position[0]+voisins[i][0]; 
    		int ordonnee= position[1]+voisins[i][1] ;
    		int z= position[2]+voisins[i][2]; 
			//Cellule a=getCelluleFromPosition(abscisse, ordonnee, z, false);  //TODO
			//if (a!=null) {
				//cellulesVoisines.add(a);
			//}
    	}
    	return cellulesVoisines;
    }
    
    /**
     * Calcule le nombre cellules voisines qui se trouvent dans l'état passé en paramètre 
     * @param s état dans lequel se trouve les cellules comptabilisées
     * @param c cellule dont veut analyser les voisins
     * @param hexa est-ce qu'il s'agit d'une grille hexagonale?
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

    /**
     * Obtient les trois voisins d'une cellule en 1D.
     * Les cellules de bord cherchent de l'autre côté de la grille .
     *
     * @param c La cellule pour laquelle on veut obtenir les voisins.
     * @return ArrayList<Cellule> Liste de trois voisins (gauche, cellule elle-même , droite).
     */
    public ArrayList<Cellule> getThreeNeighbours(Cellule c) {
      int[] position = c.getPosition();
      ArrayList<Cellule> neighbours = new ArrayList<>();
  
      // Voisin de gauche
      int leftX = (position[0] - 1 + largeur) % largeur;
      Cellule leftNeighbour = getCelluleFromPosition(leftX, position[1], false);
      if (leftNeighbour != null) {
          neighbours.add(leftNeighbour);
      }
  
      // La cellule elle-même
      neighbours.add(c);
  
      // Voisin de droite
      int rightX = (position[0] + 1) % largeur;
      Cellule rightNeighbour = getCelluleFromPosition(rightX, position[1], false);
      if (rightNeighbour != null) {
          neighbours.add(rightNeighbour);
      }
  
      return neighbours;
  }


    /**
     * Met à jour toutes les cellules de l'automate en 1D en fonction de la règle spécifiée.
     *
     * @param rule La règle à appliquer pour la mise à jour des cellules.
     */
    public void updateCells1D(int rule) {
        for (Cellule cell : cellules) {
            cell.rechargement1D(this, rule);
        }
    }

    /**
    * Met à jour toutes les cellules de l'automate en utilisant la règle de la majorité.
    */
    public void updateCellsMajority() {
      for (Cellule cell : cellules) {
          cell.rechargementMajority(this);
      }
    }

    /**
     * Méthode de mise à jour d'une cellule selon la règle de la majorité.
     *
     * @param cell La cellule à mettre à jour.
     */
    public void rechargementMajority(Cellule cell) {
        ArrayList<Cellule> neighbours = getNeighbours(cell);
        int[] countStates = new int[states.size()];

        // Compter le nombre d'occurrences de chaque état parmi les voisins
        for (Cellule neighbour : neighbours) {
            countStates[getStateIndex(neighbour.getCurrentState())]++;
        }

        // Trouver l'état majoritaire
        int majorityState = findMajorityState(countStates);

        // Mettre à jour l'état de la cellule
        cell.setNextState(states.get(majorityState));
    }

    /**
     * Obtient les voisins d'une cellule en fonction de la dimension de l'automate.
     *
     * @param cell La cellule pour laquelle on veut obtenir les voisins.
     * @return ArrayList<Cellule> Liste des voisins de la cellule.
     */
    public ArrayList<Cellule> getNeighbours(Cellule cell) {
        if (d == 1) {
            return getNeighbours1D(cell);
        } else if (d == 2) {
            return getNeighbours2D(cell);
        } else {
            return new ArrayList<>();
        }
    }

     /**
     * Méthode pour obtenir les voisins d'une cellule en 1D.
     * Les cellules de bord cherchent de l'autre côté de la grille .
     * @param cell La cellule pour laquelle on veut obtenir les voisins.
     * @return ArrayList<Cellule> Liste des voisins (gauche, cellule elle-même et droite).
     */
    private ArrayList<Cellule> getNeighbours1D(Cellule cell) {
      int[] position = cell.getPosition();
      ArrayList<Cellule> neighbours = new ArrayList<>();

      // Voisin de gauche
      int leftX = (position[0] - 1 + largeur) % largeur;
      neighbours.add(getCelluleFromPosition(leftX, position[1], false));

      // La cellule elle-même
      neighbours.add(cell);

      // Voisin de droite
      int rightX = (position[0] + 1) % largeur;
      neighbours.add(getCelluleFromPosition(rightX, position[1], false));

      return neighbours;
    }


    /**
     * Méthode pour obtenir les voisins d'une cellule en 2D.
     * Les cellules de bord cherchent de l'autre côté de la grille comme si elle formait une sphère (ou cylindre).
     * 
     * @param cell La cellule pour laquelle on veut obtenir les voisins.
     * @return ArrayList<Cellule> Liste des voisins (8 voisins autour de la cellule plus la cellule elle-même).
     */
    private ArrayList<Cellule> getNeighbours2D(Cellule cell) {
      int[] position = cell.getPosition();
      ArrayList<Cellule> neighbours = new ArrayList<>();

      // Ajouter les voisins pour la dimension 2
      for (int i = -1; i <= 1; i++) {
          for (int j = -1; j <= 1; j++) {
              int x = (position[0] + i + largeur) % largeur;
              int y = (position[1] + j + longueur) % longueur;
              Cellule neighbourCell = getCelluleFromPosition(x, y, false);
              neighbours.add(neighbourCell);
          }
      }

      // La cellule elle-même
      neighbours.add(cell);

      
      return neighbours;
    }


     /**
     * Trouve l'état majoritaire parmi les états des voisins.
     *
     * @param countStates Tableau représentant le nombre d'occurrences de chaque état parmi les voisins.
     * @return int L'index de l'état majoritaire dans la liste des états.
     */
    private int findMajorityState(int[] countStates) {
      int majorityState = 0;
      int maxCount = 0;

      for (int i = 0; i < countStates.length; i++) {
          if (countStates[i] > maxCount) {
              maxCount = countStates[i];
              majorityState = i;
          }
      }

      return majorityState;
    }

    /**
     * Obtient l'index d'un état dans la liste des états.
     *
     * @param state L'état dont on veut obtenir l'index.
     * @return int L'index de l'état.
     */
    public int getStateIndex(State state) {
      return states.indexOf(state);
    }

      
}