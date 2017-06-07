package graphvisunits2;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class VisuVertex extends Parent{
	//actual coordinates calculated by layout
	protected double x = 0; 
	protected double y = 0;
	//default graphical representation of a vertex
	protected Circle vertexShape = new Circle(10); 

	public VisuVertex (){
		this.vertexShape.setFill(Color.BLACK); 
		this.vertexShape.setCenterX(this.x);
		this.vertexShape.setCenterY(this.y);
		this.getChildren().add(this.vertexShape);
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
}
