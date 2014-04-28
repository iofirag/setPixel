import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Bezier extends Shape {
	Color c=Color.BLACK;
	List<Point> points;
	
	/* Ctor. */
	public Bezier() {
		super();
	}
	public Bezier(List<Point> bezierPoints) {
		super();
		this.points = new ArrayList<>(bezierPoints);
	}
	public Bezier(Color c, List<Point> bezierPoints) {
		super();
		this.c = c;
		this.points = new ArrayList<>(bezierPoints);
	}

	/* Getters & Setters */
	@Override
	public List<Point> getPoints() {
		return points;
	}
	@Override
	public void setPoints(List<Point> points) {
		this.points = points;
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
		main.pane.drawBezierCurve(c, points);
	}
	
	@Override
	public String toString(){
		//NamedColor nc;
		//nc.compareTo();
		
		
		return "4,"
			+points.get(0).x + ","	//A.x
			+points.get(0).y + ","	//A.y
			
			+points.get(1).x + ","	//B.x
			+points.get(1).y + ","	//B.y
			
			+points.get(2).x + ","	//C.x
			+points.get(2).y + ","	//C.y
			
			+points.get(3).x + ","	//D.x
			+points.get(3).y + ","	//D.y
			
			+"black.";				//color
	}
}
