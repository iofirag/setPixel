import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Bezier extends Shape {
	Color color=Color.BLACK;
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
		this.color = c;
		this.points = new ArrayList<>(bezierPoints);
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
	public void setPoints(List<Point> points) {
		this.points = new ArrayList<>(points);
	}

	
	
	/* Draw*/
	@Override
	public void draw(){
		/*System.out.println("x0="+points.get(0).x);
		System.out.println("y0="+points.get(0).y);
		System.out.println("x1="+points.get(1).x);
		System.out.println("y1="+points.get(1).y);
		System.out.println("x2="+points.get(2).x);
		System.out.println("y2="+points.get(2).y);
		System.out.println("x3="+points.get(3).x);
		System.out.println("y3="+points.get(3).y);*/
		
		main.pane.drawBezierCurve(color, points);
		
		/*System.out.println("\nx0="+points.get(0).x);
		System.out.println("y0="+points.get(0).y);
		System.out.println("x1="+points.get(1).x);
		System.out.println("y1="+points.get(1).y);
		System.out.println("x2="+points.get(2).x);
		System.out.println("y2="+points.get(2).y);
		System.out.println("x3="+points.get(3).x);
		System.out.println("y3="+points.get(3).y);*/
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
