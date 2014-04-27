/*This application was created by Ofir Aghai & Vidran Abdovich , 22/4/2014
 * as part of a Graphics programming course.
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import Jama.Matrix;

public class myJPanel extends JPanel {
	private BufferedImage canvas;

	public myJPanel(int width, int height) {
		canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		// using auto the function getPreferredSize()
	}

	// *******override***********************************************
	/* open the pane in the size we want */
	public Dimension getPreferredSize() {
		return new Dimension(canvas.getWidth(), canvas.getHeight());
	}

	/* draw on the pane (NEEDED) */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(canvas, null, null);
	}

	// ********************************************************

	public void fillCanvas(Color c) {
		for (int x = 0; x < canvas.getWidth(); x++) {
			for (int y = 0; y < canvas.getHeight(); y++) {
				putPixel(x, y, c);
			}
		}
		repaint();
	}

	public void drawLine(Color c, List<Point> points) {
		System.out.println("drawLine");
		int x0=(int)points.get(0).getX();
		int y0=(int)points.get(0).getY();
		int x1=(int)points.get(1).getX();
		int y1=(int)points.get(1).getY();
		/**************************** BEGINING OF BREZENHAM'S LINE ALGORITHM *****************/
		// Implement line drawing
		// ablolute length end-start
		int dy = y1 - y0;
		int dx = x1 - x0;

		// sx sy - "1" if line from top to bottom "V"
		// "-1" if line from bottom to tom "^"
		// errp - the level of currently error from the line (want to be little)
		// xp,yp - current x,y point
		int sx, sy, errp, xp, yp;

		if (dx < 0) { // if line <--
			dx = dx * -1;
			sx = -1;
		} else { // if line -->
			sx = 1;
		}
		if (dy < 0) { // if line ^
			dy = dy * -1;
			sy = -1;
		} else { // if line V
			sy = 1;
		}

		putPixel(x0, y0, c);

		if (dx > dy) { // line |________
			errp = 2 * dy - dx;
			yp = y0;
			for (xp = x0; xp != x1; xp += sx) {
				if (errp > 0) {
					yp += sy;
					errp -= dx * 2;
				}
				errp += dy * 2;
				putPixel(xp, yp, c);
			}
		} else { // line |
			errp = 2 * dx - dy; // |
			xp = x0; // |__
			for (yp = y0; yp != y1; yp += sy) {
				if (errp > 0) {
					xp += sx;
					errp -= dy * 2;
				}
				errp += dx * 2;
				putPixel(xp, yp, c);
			}
		}
		/****************************** END OF BRAZENHAM LINE ALGORITHM ***************************/
		repaint();
	}

	static public int calculateRadius(int x0, int y0, int x1, int y1) {
		// Length from start to end
		int counter = 0;
		// /* ablolute length end-start */
		/**************************** BEGINING OF BREZENHAM'S LINE ALGORITHM *****************/
		int dy = y1 - y0;
		int dx = x1 - x0;

		int sx, sy, errp, xp, yp;

		if (dx < 0) {
			dx = dx * -1;
			sx = -1;
		} else {
			sx = 1;
		}
		if (dy < 0) {
			dy = dy * -1;
			sy = -1;
		} else {
			sy = 1;
		}

		counter++;
		if (dx > dy) {
			errp = 2 * dy - dx;
			yp = y0;
			for (xp = x0; xp != x1; xp += sx) {
				if (errp > 0) {
					yp += sy;
					errp -= dx * 2;
				}
				errp += dy * 2;
				counter++;
			}
		} else {
			errp = 2 * dx - dy;
			xp = x0;
			for (yp = y0; yp != y1; yp += sy) {
				if (errp > 0) {
					xp += sx;
					errp -= dy * 2;
				}
				errp += dx * 2;
				counter++;
			}
		}
		return counter;
	}


	// Implementation of circle drawing algorithm
	public void drawCircle(Color c, List<Point> points, int radius) {
		int x = radius;
		int y = 0;
		int radiusError = 1 - x;

		while (x >= y) {
			putPixel(x + (int)points.get(0).getX(), y + (int)points.get(0).getY(), c);
			putPixel(y + (int)points.get(0).getX(), x + (int)points.get(0).getY(), c);
			putPixel(-x + (int)points.get(0).getX(), y + (int)points.get(0).getY(), c);
			putPixel(-y + (int)points.get(0).getX(), x + (int)points.get(0).getY(), c);
			putPixel(-x + (int)points.get(0).getX(), -y + (int)points.get(0).getY(), c);
			putPixel(-y + (int)points.get(0).getX(), -x + (int)points.get(0).getY(), c);
			putPixel(x + (int)points.get(0).getX(), -y + (int)points.get(0).getY(), c);
			putPixel(y + (int)points.get(0).getX(), -x + (int)points.get(0).getY(), c);
			y++;
			if (radiusError < 0) {
				radiusError += 2 * y + 1;
			} else {
				x--;
				radiusError += 2 * (y - x + 1);
			}
		}
		repaint();
	}

	// Polygon drawing
	public void regularPolygon(Color c, List<Point> polygonPoints,int pointsNumber) {
		Point p[] = new Point[pointsNumber];
		int disatance = (int) Math
				.sqrt((polygonPoints.get(0).getX() - polygonPoints.get(1)
						.getX())
						* (polygonPoints.get(0).getX() - polygonPoints.get(1)
								.getX())
						+ (polygonPoints.get(0).getY() - polygonPoints.get(1)
								.getY())
						* (polygonPoints.get(0).getY() - polygonPoints.get(1)
								.getY()));
		for (int i = 0; i < pointsNumber; i++) {
			p[i] = new Point();
			p[i].setLocation(
					((int) (polygonPoints.get(0).getX() + disatance
							* Math.cos(i * 2 * Math.PI / pointsNumber))),
					(int) (polygonPoints.get(0).getY() + disatance
							* Math.sin(i * 2 * Math.PI / pointsNumber)));
		}
		polygonPoints.clear();
		polygonPoints.add(new Point((int) (p[0].getX()), (int) (p[0].getY())));
		Point pointToClose = new Point(((int) (p[0].getX())),
				(int) (p[0].getY()));
		for (int i = 0; i < pointsNumber; i++) {
			polygonPoints.add(new Point((int) (p[i].getX()),(int) (p[i].getY())));
			
			drawLine(c, polygonPoints);
			polygonPoints.remove(0);
		}
		// Drawing the last line to a point the started the shape.
		polygonPoints.add(pointToClose);
		drawLine(c, polygonPoints);
	}

	// This function recieves 4 points from the user using mouse listeners and
	// then calculates the
	// Bezier curve and draws it on the canvas.
	public void drawBezierCurve(Color color, List<Point> points) {
		int pointX = 0;
		int pointY = 0;
		int prevX = 0;
		int prevY = 0;

		double t = 0.00; // t value from bezier curve calculation formula
		// getting matrixes with Ax,Bx,Cx,Dx,Ay,By,Cy,Dy values
		Matrix resultsForX = bezierFormula(points.get(0).x, points.get(1).x,
				points.get(2).x, points.get(3).x);
		Matrix resultsForY = bezierFormula(points.get(0).y, points.get(1).y,
				points.get(2).y, points.get(3).y);

		// calculating x and y pixel position using bezier curve formula, x(t)
		// and y(t)
		// this is the point we draw the line FROM
		prevX = (int) ((resultsForX.get(0, 0) * Math.pow(t, 3))
				+ (resultsForX.get(1, 0) * Math.pow(t, 2))
				+ (resultsForX.get(2, 0) * t) + (resultsForX.get(3, 0)));
		prevY = (int) ((resultsForY.get(0, 0) * Math.pow(t, 3))
				+ (resultsForY.get(1, 0) * Math.pow(t, 2))
				+ (resultsForY.get(2, 0) * t) + (resultsForY.get(3, 0)));

		while (t <= 1.00) {
			// calculating x and y pixel position using bezier curve formula,
			// x(t) and y(t)
			// this is the point we draw the line TO
			pointX = (int) ((resultsForX.get(0, 0) * Math.pow(t, 3))
					+ (resultsForX.get(1, 0) * Math.pow(t, 2))
					+ (resultsForX.get(2, 0) * t) + (resultsForX.get(3, 0)));
			pointY = (int) ((resultsForY.get(0, 0) * Math.pow(t, 3))
					+ (resultsForY.get(1, 0) * Math.pow(t, 2))
					+ (resultsForY.get(2, 0) * t) + (resultsForY.get(3, 0)));
			
			List<Point> temp=new ArrayList<>();
			temp.add(new Point(prevX,prevY));
			temp.add(new Point(pointX,pointY));
			drawLine(color, temp);
			t += 0.001;
			// Setting current point to be the previous point. we will draw the
			// next line starting this from this point
			prevX = pointX;
			prevY = pointY;
		}
	}

	// This function recieves X or Y values and and multiplies them with Bezier
	// matrix.
	// Returns the multiplied matrix
	public Matrix bezierFormula(double v1, double v2, double v3, double v4) {

		double[][] values = { { v1, 0, 0, 0 }, { v2, 0, 0, 0 },
				{ v3, 0, 0, 0 }, { v4, 0, 0, 0 } };
		Matrix a = new Matrix(values);

		double[][] bezierMatrix = { { -1, 3, -3, 1 }, { 3, -6, 3, 0 },
				{ -3, 3, 0, 0 }, { 1, 0, 0, 0 } };
		Matrix b = new Matrix(bezierMatrix);

		Matrix result = b.times(a);
		return result;
	}

	/* getter & setter */
	public BufferedImage getCanvas() {
		return canvas;
	}

	public void setCanvas(BufferedImage canvas) {
		this.canvas = canvas;
	}

	public void putPixel(int x, int y, Color c) {
		int color = c.getRGB();
		try {
			canvas.setRGB(x, y, color);
		} catch (Exception e) {
			System.out.println("Coordinate out of bounds!");
		}
	}

	// Used to Bold up the Bezier curve points selected by the user
	public void putSuperPixel(int x, int y, Color c) {
		int color = c.getRGB();
		try {
			// fixing point accuracy
//			x -= 8;
//			y -= 53;
			canvas.setRGB(x, y, color);
			canvas.setRGB(x, y - 1, color);
			canvas.setRGB(x, y - 2, color);
			canvas.setRGB(x - 1, y - 1, color);
			canvas.setRGB(x + 1, y - 1, color);
		} catch (Exception e) {
			System.out.println("Coordinate out of bounds!");
		}
	}

}