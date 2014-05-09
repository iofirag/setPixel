import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Shape {
	
	public Shape(){
		
	}
	public Shape(Shape s){
		List<Point> p = new ArrayList<>(s.getPoints()) ;
		this.setPoints(p);
		this.setColor( s.getColor() );
	}
	
	public Color getColor() {
		return null;
	}
	public void setColor(Color color) {
	}
	public List<Point> getPoints() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setPoints(List<Point> linePoints) {
		// TODO Auto-generated method stub
	}
	
	
	/* Draw*/
	public void draw(){
	}

	public String toString(){
		return null;
	}
	
}
