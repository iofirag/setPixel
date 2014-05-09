import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class Circle extends Shape {
	Color color = Color.BLACK;
	List<Point> points;
	int radius=-1;

	/* Ctor. */
	public Circle() {
		super();
	}

	public Circle(Color c, List<Point> circlePoint, int radius) {
		super();
		this.color = c;
		this.points = circlePoint;
		this.radius = radius;
	}

	public Circle(List<Point> circlePoint, int radius) {
		super();
		this.points = circlePoint;
		this.radius = radius;
	}

	public Circle(Color c, List<Point> point) {
		super();
		this.color = c;
		this.radius = calculateRadius(point);
		//point.remove(1);
		this.points = point;
	}
	public Circle(List<Point> point) {
		super();
		this.radius = calculateRadius(point);
		//point.remove(1);		//remove the other point in list, because we don't need to save that because we have done calculate the radius.
		this.points = point;
	}
	
	/* Getters & Setters */
	@Override
	public Color getColor() {
		return color;
	}
	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public List<Point> getPoints() {
		return points;
	}

	@Override
	public void setPoints(List<Point> circlePoint) {
		this.points = circlePoint;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	/* Draw */
	@Override
	public void draw() {
		main.pane.drawCircle(color, points);
	}

	public static int calculateRadius(List<Point> points) {
		int x0 = (int) points.get(0).getX();
		int y0 = (int) points.get(0).getY();
		int x1 = (int) points.get(1).getX();
		int y1 = (int) points.get(1).getY();
		
		// Length from start to end
		int counter = 0;
		// /* absolute length end-start */
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
	
	@Override
	public String toString(){
		//NamedColor nc;
		//nc.compareTo();
		
		return "2,"
			+points.get(0).x + ","	//x0
			+points.get(0).y + ","	//y0
			+points.get(1).x + ","	//x1
			+points.get(1).y + ","	//y1
			+"black.";				//color
	}
}
