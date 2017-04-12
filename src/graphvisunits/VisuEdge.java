package graphvisunits;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class VisuEdge extends Parent{
	protected VisuVertex startVertex;
	protected VisuVertex endVertex;
	/*
	protected double startVertex;
	protected double endVertex;
	protected double startX;
	protected double startY;
	protected double endX;
	protected double endY;
*/
	protected Line edgeLine;
	
	public VisuEdge(VisuVertex start, VisuVertex end){
		/*
		this.startVertex = start.getUniqueID();
		this.startX = start.getX();
		this.startY = start.getY();
		
		this.endVertex = end.getUniqueID();
		this.endX = end.getX();
		this.endY = end.getY();
		*/
		this.startVertex = start;
		this.endVertex = end;
		this.edgeLine = new Line(startVertex.getX(), startVertex.getY(), endVertex.getX(), endVertex.getY());
		//this.edgeLine.setFill(Color.BLACK);
		this.edgeLine.setStroke(Color.BLACK);

		this.getChildren().add(this.edgeLine);
	}
	
	public void activate(){
		//this.edgeLine.setFill(Color.RED);
		this.edgeLine.setStroke(Color.RED);
	}
	
	public void disactivate(){
		this.edgeLine.setFill(Color.BLACK);
	}
	
	public void refreshLine(){
		this.edgeLine.setStartX(this.startVertex.getX());
		this.edgeLine.setStartY(this.startVertex.getY());
		this.edgeLine.setEndX(this.endVertex.getX());
		this.edgeLine.setEndY(this.endVertex.getY());
	}
	//getters and setters
	public VisuVertex getStartVertex() {
		return startVertex;
	}

	public void setStartVertex(VisuVertex startVertex) {
		this.startVertex = startVertex;
	}

	public VisuVertex getEndVertex() {
		return endVertex;
	}

	public void setEndVertex(VisuVertex endVertex) {
		this.endVertex = endVertex;
	}

	public Line getEdgeLine() {
		return edgeLine;
	}

	
}
