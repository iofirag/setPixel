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
    	int color = c.getRGB();
    	
        // Implement line drawing
    	/* ablolute length end-start */
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        /*  1 if start value < end value  for x&y
         *  else -1
         */
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;

        /* Positive value if x > y 
         * 
         * err = value if |________
         * 
         * else Negative
         * err = -value if
         * 		 |
         * 		 |
         *		 |__		
         */
        int err = dx - dy;

        while (true) {
        	putPixel(x0, y0, c);

        	/* if this start&end point in the same place so -> finish */
            if (x0 == x1 && y0 == y1) {
            	System.out.println("break");
                break;
            }
            
            /* level of stairs in line */
            int e2 = 2 * err;
            
            // if need to go in x
            if (e2 > -dy) {
            	System.out.println("x: " +e2 +"> -"+dy);
            	// minimize the error
                err = err - dy;
                // upgrade x0
                x0 = x0 + sx;
            }

            //if need to go in y
            if (e2 < dx) {
            	System.out.println("y: "+e2+"<"+dx);
            	// upgrade the error
                err = err + dx;
                // upgrade y0
                y0 = y0 + sy;
            }
        }
        repaint();
    }

    public int calculateRadius(int x0, int y0, int x1, int y1){
    	// Length from start to end
    	int counter=0;
    	/* ablolute length end-start */
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        /*  1 if start value < end value  for x&y
         *  else -1
         */
        int sx = (x0 < x1) ? 1 : -1;
        int sy = (y0 < y1) ? 1 : -1;

        /* Positive value if x > y 
         * 
         * err = value if |________
         * 
         * else Negative
         * err = -value if
         * 		 |
         * 		 |
         *		 |__		
         */
        int err = dx - dy;

        while (true) {
        	counter++;

        	/* if this start&end point in the same place so -> finish */
            if (x0 == x1 && y0 == y1) {
            	System.out.println("break");
                return counter;
            }
            
            /* levels of steps in line */
            int e2 = 2 * err;
            
            // if need to go in x
            if (e2 > -dy) {
            	System.out.println("x: " +e2 +"> -"+dy);
            	// minimize the error
                err = err - dy;
                // upgrade x0
                x0 = x0 + sx;
            }

            //if need to go in y
            if (e2 < dx) {
            	System.out.println("y: "+e2+"<"+dx);
            	// upgrade the error
                err = err + dx;
                // upgrade y0
                y0 = y0 + sy;
            }
        }
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