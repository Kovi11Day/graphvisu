package graphvisunits;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class VisuVertex extends Parent{
	protected double uniqueID;
	protected double i; //logical order provided by client (optional)
	protected double j;
	protected double x = 0; //actual coordinates calculated by layout
	protected double y = 0;
	protected Shape vertexShape = new Circle(10); 
	
	public VisuVertex (double uniqueID, double i, double j){
		this.uniqueID = uniqueID;
		this.i = i;
		this.j = j;
		this.vertexShape.setFill(Color.BLACK); 
		this.getChildren().add(this.vertexShape);
		System.out.println("VisuVertex built with i=" + i + " j=" + j);
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
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Shape getVertexShape() {
		return vertexShape;
	}

	public void setVertexShape(Shape vertexShape) {
		this.vertexShape = vertexShape;
	}
	
}
