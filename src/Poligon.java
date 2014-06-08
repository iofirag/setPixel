import java.awt.Color;
import java.awt.Point;
import java.util.List;


public class Poligon extends Shape {
	Color color=Color.BLACK;
	List<Point> points;
	int poligon_vertex;
	
	
	/* Ctor. */
	public Poligon() {
		super();
	}
	public Poligon(List<Point> polygonPoints, int poligon_vertex) {
		super();
		this.points = polygonPoints;
		this.poligon_vertex = poligon_vertex;
	}
	public Poligon(Color c, List<Point> polygonPoints, int poligon_vertex) {
		super();
		this.color = c;
		this.points = polygonPoints;
		this.poligon_vertex = poligon_vertex;
		
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
	public void setPoints(List<Point> polygonPoints) {
		this.points = polygonPoints;
	}
	public int getPoligon_vertex() {
		return poligon_vertex;
	}
	public void setPoligon_vertex(int poligon_vertex) {
		this.poligon_vertex = poligon_vertex;
	}	
	
	
	/* Draw*/
	@Override
	public void draw(){
		main.pane.regularPolygon(color, points, poligon_vertex);
	}
	
	@Override
	public String toString(){
		//NamedColor nc;
		//nc.compareTo();
		
		return "3,"
			+points.get(0).x + ","	//x0
			+points.get(0).y + ","	//y0
			+points.get(1).x + ","	//x1
			+points.get(1).y + ","	//y1
			+poligon_vertex +  ","	//num of vertex
			+"black.";				//color
	}
}
