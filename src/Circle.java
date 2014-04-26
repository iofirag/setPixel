import java.awt.Color;
import java.awt.Point;
import java.util.List;


public class Circle extends Shape {
	Color c=Color.BLACK;
	List<Point> circlePoint;
	float radius;
	
	/* Ctor. */
	public Circle() {
		super();
	}

	public Circle(Color c, List<Point> linePoints, float radius) {
		super();
		this.c = c;
		this.circlePoint = linePoints;
		this.radius = radius;
	}
	public Circle(List<Point> linePoints, float radius) {
		super();
		this.circlePoint = linePoints;
		this.radius = radius;
	}

	
	/* Getters & Setters */
	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public List<Point> getLinePoints() {
		return circlePoint;
	}

	public void setLinePoints(List<Point> linePoints) {
		this.circlePoint = linePoints;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	
	
	
	
	
}
