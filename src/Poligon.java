import java.awt.Color;
import java.awt.Point;
import java.util.List;


public class Poligon extends Shape {
	Color c=Color.BLACK;
	List<Point> polygonPoints;
	int poligon_vertex;
	
	
	/* Ctor. */
	public Poligon() {
		super();
	}
	public Poligon(List<Point> polygonPoints, int poligon_vertex) {
		super();
		this.polygonPoints = polygonPoints;
		this.poligon_vertex = poligon_vertex;
	}
	public Poligon(Color c, List<Point> polygonPoints, int poligon_vertex) {
		super();
		this.c = c;
		this.polygonPoints = polygonPoints;
		this.poligon_vertex = poligon_vertex;
		
	}
	
	/* Getters & Setters */
	@Override
	public List<Point> getPoints() {
		return polygonPoints;
	}
	@Override
	public void setPoints(List<Point> polygonPoints) {
		this.polygonPoints = polygonPoints;
	}
	public int getPoligon_vertex() {
		return poligon_vertex;
	}
	public void setPoligon_vertex(int poligon_vertex) {
		this.poligon_vertex = poligon_vertex;
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
		main.pane.regularPolygon(c, polygonPoints, poligon_vertex);
	}
}
