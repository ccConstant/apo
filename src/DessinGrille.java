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
    
    private boolean hexa;

    private int cellSize;
    
    public DessinGrille(int cols, int rows, int gridWidth, int gridHeight, Automate a) {
        this.rows = rows;
        this.cols = cols;
        this.startX = 10;
        this.startY = 10;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.a = a;
        hexa = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!hexa) {
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
                    
                	Cellule c = a.getCelluleFromPosition(j, i, hexa);
                    State s = c.getCurrentState();
                    Color col = new Color(s.getR(), s.getG(), s.getB());
                    g.setColor(col);
                    g.fillRect(x, y, cellSize, cellSize);
                    
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, cellSize, cellSize);
                }
            }
        } else {
        	int widthMax = gridWidth / (rows);
            int heightMax = ((gridHeight / (cols))*2)/3;
            if (widthMax > heightMax) {
            	cellSize = heightMax;
            }else {
            	cellSize = widthMax;
            }
        		    for (int i = 0; i < rows; i++) {
        		        for (int j = 0; j < cols; j++) {
        		        	int starty = (int)Math.round(i * cellSize * (1.0/2));
        		        	int startx = (int)Math.round(j * cellSize * (3.0/2));
        		        	if (i%2 == 1) {
        		        		startx = (int)Math.round(j *  cellSize * (3.0/2) +  cellSize * (3.0/4));
        		        	}
        		            int[] xPoints = {
        		            	(int)Math.round(startX  + startx  +  cellSize * (1.0/4)),
        		            	(int)Math.round(startX  + startx  +  cellSize * (3.0/4)),
        		            	startX  + startx +  cellSize,
        		            	(int)Math.round(startX  + startx +  cellSize * (3.0/4)),
        		            	(int)Math.round(startX  + startx  +  cellSize * (1.0/4)),
        		                startX  + startx 
        		            };
        		            int[] yPoints = {
        		                startY + starty +  cellSize,
        		                startY + starty +  cellSize,
        		                (int)Math.round(startY + starty +  cellSize * (1.0/2)),
        		                startY + starty,
        		                startY + starty,
        		                (int)Math.round(startY + starty +  cellSize * (1.0/2))
        		            };
        		            
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
    
    public void setHexa(boolean b) {
    	hexa = b;
    	this.repaint();
    }
    
}
