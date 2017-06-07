package graphvisunits2;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class VisuEdge extends Parent{
	protected VisuVertex startVertex; 
	protected VisuVertex endVertex;
	protected Line edgeLine;

	public VisuEdge(VisuVertex start, VisuVertex end){
		this.startVertex = start;
		this.endVertex = end;

		this.edgeLine = new Line(startVertex.getX(), startVertex.getY(), endVertex.getX(), endVertex.getY());
		this.edgeLine.setStroke(Color.BLACK);

		this.getChildren().add(this.edgeLine);
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
