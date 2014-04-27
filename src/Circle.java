import java.awt.Color;
import java.awt.Point;
import java.util.List;


public class Circle extends Shape {
	Color c=Color.BLACK;
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

	public void setLinePoints(List<Point> circlePoint) {
		this.circlePoint = circlePoint;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
	
	/* Draw*/
	@Override
	public void draw(){
		main.pane.drawCircle(c, circlePoint, radius);
	}
	
	
}
