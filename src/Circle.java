import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class Circle extends Shape {
	Color c = Color.BLACK;
	List<Point> circlePoint;
	int radius;

	/* Ctor. */
	public Circle() {
		super();
	}

	public Circle(Color c, List<Point> circlePoint, int radius) {
		super();
		this.c = c;
		this.circlePoint = circlePoint;
		this.radius = radius;
	}

	public Circle(List<Point> circlePoint, int radius) {
		super();
		this.circlePoint = circlePoint;
		this.radius = radius;
	}

	public Circle(Color c, List<Point> point) {
		super();
		this.c = c;
		this.radius = myJPanel.calculateRadius(point);
		point.remove(0);
		this.circlePoint = point;
	}
	public Circle(List<Point> point) {
		super();
		this.radius = myJPanel.calculateRadius(point);
		point.remove(1);		//remove the other point in list, because we don't need to save that because we have done calculate the radius.
		this.circlePoint = point;
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
		return circlePoint;
	}

	@Override
	public void setPoints(List<Point> circlePoint) {
		this.circlePoint = circlePoint;
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
		main.pane.drawCircle(c, circlePoint, radius);
	}

}
