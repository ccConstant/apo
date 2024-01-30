import javax.swing.*;
import java.awt.*;

public class DessinGrille extends JPanel { /* implements Serializable{
	
	private static final long serialVersionUID = 3L;*/


    private int rows;
    private int cols;
    private int startX;
    private int startY;
    private int gridWidth;
    private int gridHeight;
    
    private Automate a;

    private int cellSize;
    
    public DessinGrille(int cols, int rows, int gridWidth, int gridHeight, Automate a) {
        this.rows = rows;
        this.cols = cols;
        this.startX = 10;
        this.startY = 10;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.a = a;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Calculer la longueur et la largeur de la cellule selon les dimensions de la grille
        int widthMax = gridWidth / rows;
        int heightMax = gridHeight / cols;
        if (widthMax > heightMax) {
        	cellSize = heightMax;
        }else {
        	cellSize = widthMax;
        }

        // Dessiner les lignes de la grille dans l'aire spécifique 
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
            	int x = startX + j * cellSize;
                int y = startY + i * cellSize;
                
            	Cellule c = a.getCelluleFromPosition(j, i);
                State s = c.getCurrentState();
                Color col = new Color(s.getR(), s.getG(), s.getB());
                g.setColor(col);
                g.fillRect(x, y, cellSize, cellSize);
                
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }
    
    public Automate getAuto() {
    	return a;
    }
    
    public int getCellSize() {
    	return cellSize;
    }
    
    public int getrows() {
    	return rows;
    }
    
    public int getcols() {
    	return cols;
    }
}
