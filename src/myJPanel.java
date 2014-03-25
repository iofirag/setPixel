import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.*;


public class myJPanel extends JPanel {

    private BufferedImage canvas;

    

	public myJPanel(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // using auto the function getPreferredSize()
    }
	
    //*******override***********************************************
    /* open the pane in the size we want */
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
    /* draw on the pane (NEEDED)*/
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }
    //********************************************************

    public void fillCanvas(Color c) {
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                putPixel(x, y, c);
            }
        }
        repaint();
    }

    public void drawLine(Color c, int x0, int x1, int y0, int y1) {
    	System.out.println("drawLine");
    	/****************************BEGINING OF BREZENHAM'S LINE ALGORITHM*****************/
    	// Implement line drawing 
    	//ablolute length end-start 
    	int dy = y1 - y0;
        int dx = x1 - x0;
        
        // sx sy	- "1" if line from top to bottom "V"
        //      	  "-1" if line from bottom to tom "^"
        // errp		- the level of currently error from the line (want to be little) 
        // xp,yp	-  current x,y point
        int sx,sy,   errp,    xp,yp;

        
        if (dx < 0){	// if line <--
            dx = dx * -1;
            sx = -1;
        }
        else{			// if line -->
            sx = 1;
        }
        if (dy < 0){	// if line ^
            dy = dy * -1;
            sy = -1;
        }
        else{			// if line V
            sy = 1;
        }    

        putPixel(x0, y0, c);
        
        if (dx > dy){						// line |________
            errp = 2*dy - dx;
            yp = y0;
            for (xp=x0; xp!=x1; xp+=sx){
                if (errp > 0){
                    yp+=sy;
                    errp -= dx * 2;
                }
                errp += dy * 2;
                putPixel(xp, yp, c);
            }
        }
        else{								// line	 |
        	errp = 2*dx - dy;				// 		 |
        	xp = x0;						//		 |__
        	for (yp=y0; yp!=y1; yp+=sy){
                if (errp > 0){
                    xp+=sx;
                    errp -= dy * 2;
                }
                errp += dx * 2;
                putPixel(xp, yp, c);
            }
        }
        /******************************END OF BRAZENHAM LINE ALGORITHM***************************/
        repaint();
    }

    public int calculateRadius(int x0, int y0, int x1, int y1){
    	// Length from start to end
    	int counter=0;
//    	/* ablolute length end-start */
    	/****************************BEGINING OF BREZENHAM'S LINE ALGORITHM*****************/
        int dy = y1 - y0;
        int dx = x1 - x0;
        
        int sx,sy,errp,xp,yp;

        if (dx < 0){
            dx = dx * -1;
            sx = -1;
        }
        else{
            sx = 1;
        }
        if (dy < 0){
            dy = dy * -1;
            sy = -1;
        }
        else{
            sy = 1;
        }    

        counter++;
        if (dx > dy){
            errp = 2*dy - dx;
            yp = y0;
            for (xp=x0; xp!=x1; xp+=sx){
                if (errp > 0){
                    yp+=sy;
                    errp -= dx * 2;
                }
                errp += dy * 2;
                counter++;
            }
        }
        else{
            errp = 2*dx - dy;
            xp = x0;
            for (yp=y0; yp!=y1; yp+=sy){
                if (errp > 0){
                    xp+=sx;
                    errp -= dy * 2;
                }
                errp += dx * 2;
                counter++;
            }
        }
        return counter;
    }
    
	  public void drawCircle(Color c, int x,int y, int radiusX, int radiusY) {
		  int radius = calculateRadius(x,y, radiusX, radiusY);
		  // Implement Circle drawing
		  int discriminant = (5 - radius<<2)>>2 ;
		    int i = 0, j = radius ;
		    while (i<=j) {
		        putPixel(x+i,y-j,c) ;
		        putPixel(x+j,y-i,c) ;
		        putPixel(x+i,y+j,c) ;
		        putPixel(x+j,y+i,c) ;
		        putPixel(x-i,y-j,c) ;
		        putPixel(x-j,y-i,c) ;
		        putPixel(x-i,y+j,c) ;
		        putPixel(x-j,y+i,c) ;
		        i++ ;
		        if (discriminant<0) {                
		            discriminant += (i<<1) + 1 ;
		        } else {
		            j-- ;
		            discriminant += (1 + i - j)<<1 ;
		        }
		    }
		  repaint();
	  }

	  
    public void drawPoligon(Color c, int x, int y, int vX, int vY, int vertex){
	    // Implement rectangle drawing
	    
	    repaint();
    }
	  
	  
//    public void drawRect(Color c, int x1, int y1, int width, int height) {
//    int color = c.getRGB();
//    // Implement rectangle drawing
//    for (int x = x1; x < x1 + width; x++) {
//        for (int y = y1; y < y1 + height; y++) {
//            canvas.setRGB(x, y, color);
//        }
//    }
//    repaint();
//}

//public void drawOval(Color c, int x1, int y1, int width, int height) {
//    // Implement oval drawing
//    repaint();
//}
	  
	  
    /* getter & setter */
    public BufferedImage getCanvas() {
		return canvas;
	}
	public void setCanvas(BufferedImage canvas) {
		this.canvas = canvas;
	}

	public void putPixel(int x, int y, Color c){
		int color = c.getRGB();
		try{
    		canvas.setRGB(x, y, color);
    	}catch(Exception e){
    		System.out.println("Coordinate out of bounds!");
    	}
	}

}