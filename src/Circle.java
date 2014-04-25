import java.awt.Color;


public class Circle extends Shape {
	int x0;
	int y0;
	float radius;
	Color c=Color.BLACK;
	
	/* Ctor. */
	public Circle() {
		super();
	}
	public Circle(int x0, int y0, int releaseX, int releaseY, float radius) {
		super();
		this.x0 = x0;
		this.y0 = y0;
		this.radius = radius;
	}
	public Circle(int x0, int y0, float radius, Color c) {
		super();
		this.x0 = x0;
		this.y0 = y0;
		this.radius = radius;
		this.c = c;
	}
	
	/* Getters & Setters */
	public int getX0() {
		return x0;
	}
	public void setX0(int x0) {
		this.x0 = x0;
	}
	public int getY0() {
		return y0;
	}
	public void setY0(int y0) {
		this.y0 = y0;
	}
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
	
	
	
}
