import java.awt.Color;
import java.awt.Point;
import java.util.List;


public class Bezier extends Shape {
	Color c=Color.BLACK;
	List<Point> bezierPoints;
	
	/* Ctor. */
	public Bezier() {
		super();
	}
	public Bezier(List<Point> bezierPoints) {
		super();
		this.bezierPoints = bezierPoints;
	}
	public Bezier(Color c, List<Point> bezierPoints) {
		super();
		this.c = c;
		this.bezierPoints = bezierPoints;
	}

	/* Getters & Setters */
	@Override
	public List<Point> getPoints() {
		return bezierPoints;
	}
	@Override
	public void setPoints(List<Point> bezierPoints) {
		this.bezierPoints = bezierPoints;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}

	
	
	/* Draw*/
	@Override
	public void draw(){
		main.pane.drawBezierCurve(c, bezierPoints);
	}
}
