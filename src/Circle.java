import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class Circle extends Shape {
	Color color = Color.BLACK;
	List<Point> points;
	int radius;

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
		this.radius = myJPanel.calculateRadius(point);
		//point.remove(1);
		this.points = point;
	}
	public Circle(List<Point> point) {
		super();
		this.radius = myJPanel.calculateRadius(point);
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
		main.pane.drawCircle(color, points, radius);
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
