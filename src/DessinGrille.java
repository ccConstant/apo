import javax.swing.*;
import java.awt.*;

/**
 * La classe DessinGrille étend JPanel et est utilisée pour dessiner la grille de simulation.
 */
public class DessinGrille extends JPanel {

    private int rows; // Nombre de lignes dans la grille
    private int cols; // Nombre de colonnes dans la grille
    private int startX; // Coordonnée X de départ pour dessiner la grille
    private int startY; // Coordonnée Y de départ pour dessiner la grille
    private int gridWidth; // Largeur de la grille
    private int gridHeight; // Hauteur de la grille
    
    private Automate a; // L'automate associé à la grille
    
    private boolean hexa; // Indique si la grille est hexagonale ou non

    private int cellSize; // Taille d'une cellule dans la grille
	private int z;
    
    /**
     * Constructeur de la classe DessinGrille.
     * @param cols Le nombre de colonnes dans la grille.
     * @param rows Le nombre de lignes dans la grille.
     * @param gridWidth La largeur de la grille.
     * @param gridHeight La hauteur de la grille.
     * @param a L'automate associé à la grille.
     */
    public DessinGrille(int cols, int rows, int gridWidth, int gridHeight, Automate a) {
        this.rows = rows;
        this.cols = cols;
        this.startX = 10;
        this.startY = 10;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.a = a;
        hexa = false;
        this.z = 0;
    }

    /**
     * Méthode de dessin de la grille.
     * @param g L'objet Graphics utilisé pour dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!hexa) { // Si la grille n'est pas hexagonale
        	// Calculer la taille de la cellule en fonction des dimensions de la grille
            int widthMax = gridWidth / rows;
            int heightMax = gridHeight / cols;
            if (widthMax > heightMax) {
            	cellSize = heightMax;
            } else {
            	cellSize = widthMax;
            }

            // Dessiner les lignes de la grille dans la zone spécifiée
            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < rows; j++) {
                	int x = startX + j * cellSize;
                    int y = startY + i * cellSize;
                    System.out.print(j);
                    System.out.println(" " + i);
                	Cellule c = a.getCelluleFromPosition(j, i, z, hexa);
                    State s = c.getCurrentState();
                    if(s.getR() != -1) {
                    	Color col = new Color(s.getR(), s.getG(), s.getB());
                        g.setColor(col);
                        g.fillRect(x, y, cellSize, cellSize);
                        
                        g.setColor(Color.BLACK);
                        g.drawRect(x, y, cellSize, cellSize);
                    }
                    
                }
            }
        } else { // Si la grille est hexagonale
        	// Calculer la taille de la cellule en fonction des dimensions de la grille
        	int widthMax = gridWidth / (rows);
            int heightMax = ((gridHeight / (cols))*2)/3;
            if (widthMax > heightMax) {
            	cellSize = heightMax;
            } else {
            	cellSize = widthMax;
            }
        	
        	// Dessiner les hexagones dans la grille
        	for (int i = 0; i < rows; i++) {
    		    for (int j = 0; j < cols; j++) {
    		        int starty = (int)Math.round(i * cellSize * (1.0/2));
    		        int startx = (int)Math.round(j * cellSize * (3.0/2));
    		        
    		        // Décalage pour les lignes impaires
    		        if (i % 2 == 1) {
    		            startx = (int)Math.round(j *  cellSize * (3.0/2) +  cellSize * (3.0/4));
    		        }
    		        
    		        // Points pour dessiner l'hexagone
    		        int[] xPoints = {
    		            (int)Math.round(startX + startx + cellSize * (1.0/4)),
    		            (int)Math.round(startX + startx + cellSize * (3.0/4)),
    		            startX + startx + cellSize,
    		            (int)Math.round(startX + startx + cellSize * (3.0/4)),
    		            (int)Math.round(startX + startx + cellSize * (1.0/4)),
    		            startX + startx
    		        };
    		        int[] yPoints = {
    		            startY + starty + cellSize,
    		            startY + starty + cellSize,
    		            (int)Math.round(startY + starty + cellSize * (1.0/2)),
    		            startY + starty,
    		            startY + starty,
    		            (int)Math.round(startY + starty + cellSize * (1.0/2))
    		        };
    		        
    		        // Remplissage et dessin de l'hexagone
    		        Cellule c = a.getCelluleFromPosition(i, j, true);
                    State s = c.getCurrentState();
                    Color col = new Color(s.getR(), s.getG(), s.getB());
                    g.setColor(col);
                    g.fillPolygon(xPoints, yPoints, 6);
                    
                    g.setColor(Color.BLACK);
    		        g.drawPolygon(xPoints, yPoints, 6);
    		    }
    		}
        }
    }
    
    /**
     * Méthode pour mettre à jour l'automate associé à la grille.
     * @param automate L'automate à mettre à jour.
     */
    public void updateAutomate(Automate automate) {
        this.a = automate;
        repaint(); // Redessiner la grille avec le nouvel automate
    }
    
    /**
     * Méthode pour obtenir l'automate associé à la grille.
     * @return L'automate associé à la grille.
     */
    public Automate getAuto() {
    	return a;
    }
    
    /**
     * Méthode pour obtenir la taille d'une cellule dans la grille.
     * @return La taille d'une cellule.
     */
    public int getCellSize() {
    	return cellSize;
    }
    
    /**
     * Méthode pour obtenir le nombre de lignes dans la grille.
     * @return Le nombre de lignes dans la grille.
     */
    public int getrows() {
    	return rows;
    }
    
    /**
     * Méthode pour obtenir le nombre de colonnes dans la grille.
     * @return Le nombre de colonnes dans la grille.
     */
    public int getcols() {
    	return cols;
    }
    
    /**
     * Méthode pour définir si la grille est hexagonale ou non.
     * @param b true si la grille est hexagonale, false sinon.
     */
    public void setHexa(boolean b) {
    	hexa = b;
    	this.repaint(); // Redessiner la grille avec le nouvel état hexagonal
    }

	public void setZ(int newZ) {
		z = newZ;
	}
    
}
