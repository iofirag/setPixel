import java.awt.Color;
import java.awt.Point;
import java.util.List;


public class Line extends Shape {
	Color c=Color.BLACK;
	List<Point> linePoints;
	
	/* Ctor. */
	public Line() {
		super();
	}
	public Line(Color c, List<Point> linePoints) {
		super();
		this.c = c;
		this.linePoints = linePoints;
	}
	public Line(List<Point> linePoints) {
		super();
		this.linePoints = linePoints;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
	public List<Point> getLinePoints() {
		return linePoints;
	}
	public void setLinePoints(List<Point> linePoints) {
		this.linePoints = linePoints;
	}
	
	/* Getters & Setters */

}
