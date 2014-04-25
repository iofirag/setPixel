import java.awt.Color;


public class Bezier extends Shape {
	int bezierPoints;
	Color c=Color.BLACK;
	
	/* Ctor. */
	public Bezier() {
		super();
	}
	public Bezier(int bezierPoints) {
		super();
		this.bezierPoints = bezierPoints;
	}
	public Bezier(int bezierPoints, Color c) {
		super();
		this.bezierPoints = bezierPoints;
		this.c = c;
	}

	/* Getters & Setters */
	public int getBezierPoints() {
		return bezierPoints;
	}
	public void setBezierPoints(int bezierPoints) {
		this.bezierPoints = bezierPoints;
	}
	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}

}
