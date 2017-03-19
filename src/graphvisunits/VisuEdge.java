package graphvisunits;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class VisuEdge extends Parent{
	protected double startVertex;
	protected double endVertex;
	protected double startX;
	protected double startY;
	protected double endX;
	protected double endY;

	protected Shape edgeShape;
	
	public VisuEdge(VisuVertex start, VisuVertex end){
		this.startVertex = start.getUniqueID();
		this.startX = start.getX();
		this.startY = start.getY();
		
		this.endVertex = end.getUniqueID();
		this.endX = end.getX();
		this.endY = end.getY();
		
		this.edgeShape = new Line(startX, startY, endX, endY);
		this.edgeShape.setFill(Color.BLACK);
		
		this.getChildren().add(this.edgeShape);

	}
	
	public void activate(){
		this.edgeShape.setFill(Color.RED);
	}
	
	public void disactivate(){
		this.edgeShape.setFill(Color.BLACK);
	}

	//getters and setters
	public double getStartVertex() {
		return startVertex;
	}

	public void setStartVertex(double startVertex) {
		this.startVertex = startVertex;
	}

	public double getEndVertex() {
		return endVertex;
	}

	public void setEndVertex(double endVertex) {
		this.endVertex = endVertex;
	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(double endY) {
		this.endY = endY;
	}

	public Shape getEdgeShape() {
		return edgeShape;
	}

	public void setEdgeShape(Shape edgeShape) {
		this.edgeShape = edgeShape;
	}
	
	
	
}
