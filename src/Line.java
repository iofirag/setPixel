import java.awt.Color;


public class Line extends Shape {
	int x0;
	int y0; 
	int x1;
	int y1;
	Color c=Color.BLACK;
	
	/* Ctor. */
	public Line() {
		super();
	}
	public Line(int x0, int x1, int y0, int y1) {
		super();
		this.x0 = x0;
		this.x1 = x1;
		this.y0 = y0;
		this.y1 = y1;
	}
	public Line(int x0, int x1, int y0, int y1, Color c) {
		super();
		this.x0 = x0;
		this.x1 = x1;
		this.y0 = y0;
		this.y1 = y1;
		this.c = c;
	}
	
	/* Getters & Setters */
	public int getX0() {
		return x0;
	}
	public void setX0(int x0) {
		this.x0 = x0;
	}
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getY0() {
		return y0;
	}
	public void setY0(int y0) {
		this.y0 = y0;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
}
