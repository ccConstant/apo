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
    SimulationForestFire f;

    public DessinGrille(int rows, int cols, int startX, int startY, int gridWidth, int gridHeight, SimulationForestFire f) {
        this.rows = rows;
        this.cols = cols;
        this.startX = startX;
        this.startY = startY;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.f = f;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Automate a = f.getAutomate();
        
        // Calculer la longueur et la largeur de la cellule selon les dimensions de la grille
        int widthMax = gridWidth / cols;
        int heightMax = gridHeight / rows;
        int cellSize;
        if (widthMax > heightMax) {
        	cellSize = heightMax;
        }else {
        	cellSize = widthMax;
        }

        // Dessiner les lignes de la grille dans l'aire spécifique 
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
            	int x = startX + j * cellSize;
                int y = startY + i * cellSize;
                
            	Cellule c = a.getCelluleFromPosition(i, j);
                State s = c.getCurrentState();
                Color col = new Color(s.getR(), s.getG(), s.getB());
                g.setColor(col);
                g.fillRect(x, y, cellSize, cellSize);
                
                g.setColor(Color.BLACK);
                g.drawRect(x, y, cellSize, cellSize);
            }
        }
    }
    
    public SimulationForestFire getAuto() {
    	return f;
    }
}
