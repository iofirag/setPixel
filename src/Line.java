import java.awt.Color;
import java.awt.Point;
import java.util.List;

public class Line extends Shape {
	Color color=Color.BLACK;
	List<Point> points;
	
	/* Ctor. */
	public Line() {
		super();
	}
	public Line(Color c, List<Point> linePoints) {
		super();
		this.color = c;
		this.points = linePoints;
	}
	public Line(List<Point> linePoints) {
		super();
		this.points = linePoints;
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
	public void setPoints(List<Point> linePoints) {
		this.points = linePoints;
	}
	
	
	/* Draw*/
	@Override
	public void draw(){
		main.pane.drawLine(color, points);
	}

	@Override
	public String toString(){
		//NamedColor nc;
		//nc.compareTo();
		
		return "1,"
			+points.get(0).x + ","	//x0
			+points.get(0).y + ","	//y0
			+points.get(1).x + ","	//x1
			+points.get(1).y + ","	//y1
			+"black.";				//color
	}
}
