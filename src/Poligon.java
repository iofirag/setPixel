import java.awt.Color;
import java.awt.Point;
import java.util.List;


public class Poligon extends Shape {
	List<Point> polygonPoints;
	int poligon_vertex;
	Color c=Color.BLACK;
	
	/* Ctor. */
	public Poligon() {
		super();
	}
	public Poligon(List<Point> polygonPoints, int poligon_vertex) {
		super();
		this.polygonPoints = polygonPoints;
		this.poligon_vertex = poligon_vertex;
	}
	public Poligon(List<Point> polygonPoints, int poligon_vertex, Color c) {
		super();
		this.polygonPoints = polygonPoints;
		this.poligon_vertex = poligon_vertex;
		this.c = c;
	}
	
	/* Getters & Setters */
	public List<Point> getPolygonPoints() {
		return polygonPoints;
	}
	public void setPolygonPoints(List<Point> polygonPoints) {
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
}
