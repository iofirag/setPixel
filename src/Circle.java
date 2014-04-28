import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class Circle extends Shape {
	Color c = Color.BLACK;
	List<Point> points;
	int radius;

	/* Ctor. */
	public Circle() {
		super();
	}

	public Circle(Color c, List<Point> circlePoint, int radius) {
		super();
		this.c = c;
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
		this.c = c;
		this.radius = myJPanel.calculateRadius(point);
		point.remove(0);
		this.points = point;
	}
	public Circle(List<Point> point) {
		super();
		this.radius = myJPanel.calculateRadius(point);
		point.remove(1);		//remove the other point in list, because we don't need to save that because we have done calculate the radius.
		this.points = point;
	}
	
	/* Getters & Setters */
	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
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
		main.pane.drawCircle(c, points, radius);
	}

	@Override
	public String toString(){
		//NamedColor nc;
		//nc.compareTo();
		
		return "2,"
			+points.get(0).x + ","	//x0
			+points.get(0).y + ","	//y0
			+radius +		   ","	//radius
			+"black.";				//color
	}
}
