import java.awt.Color;


public class Bezier extends Shape {
	Color c=Color.BLACK;
	int bezierPoints;
	
	/* Ctor. */
	public Bezier() {
		super();
	}
	public Bezier(int bezierPoints) {
		super();
		this.bezierPoints = bezierPoints;
	}
	public Bezier(Color c, int bezierPoints) {
		super();
		this.c = c;
		this.bezierPoints = bezierPoints;
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
