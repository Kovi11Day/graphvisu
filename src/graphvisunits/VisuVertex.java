package graphvisunits;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class VisuVertex extends Parent{
	protected double uniqueID;
	protected double i; //logical order provided by client (optional)
	protected double j;
	protected double x = 0; //actual coordinates calculated by layout
	protected double y = 0;
	

	protected int out=0;
	protected int in =0;
	
	protected Circle vertexShape = new Circle(10); 
	
	public VisuVertex (double uniqueID, double i, double j){
		this.uniqueID = uniqueID;
		this.i = i;
		this.j = j;
		this.vertexShape.setFill(Color.BLACK); 
		this.vertexShape.setCenterX(this.x);
		this.vertexShape.setCenterY(this.y);
		this.getChildren().add(this.vertexShape);
	}
	
	public void activate (){
		this.vertexShape.setFill(Color.RED);
	}
	
	public void disactivate (){
		this.vertexShape.setFill(Color.BLACK);
	}

	public double getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(double uniqueID) {
		this.uniqueID = uniqueID;
	}

	public double getI() {
		return i;
	}

	public void setI(double i) {
		this.i = i;
	}

	public double getJ() {
		return j;
	}

	public void setJ(double j) {
		this.j = j;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
		this.vertexShape.setCenterX(x);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
		this.vertexShape.setCenterY(y);
	}

	public Shape getVertexShape() {
		return vertexShape;
	}

	public void setVertexShape(Circle vertexShape) {
		this.vertexShape = vertexShape;
	}
	public int getIn(){
		return this.in;
	}
	
	public int getOut(){
		return this.out;
	}
	
	public void setIn(){
		this.in++;
	}
	
	public void setOut(){
		this.out++;
	}

}
