package graphvisunits;

import java.awt.geom.Point2D;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import javafx.scene.Parent;

public class VisuGraph extends Parent{
	protected Graph<VisuVertex,VisuEdge> jungGraphe; 
	protected Layout<VisuVertex,VisuEdge> layout;
	
	public VisuGraph(Graph<VisuVertex,VisuEdge> jungGraphe){
		this.jungGraphe = jungGraphe; 
		this.layout = null;
		for(VisuVertex v: this.jungGraphe.getVertices()){
			this.getChildren().add(v);
		}
		for(VisuEdge e: this.jungGraphe.getEdges()){
			this.getChildren().add(e);
		}
	}
	
	public Graph<VisuVertex,VisuEdge> getJungGraphe(){
		return this.jungGraphe;
	}
	
	public Layout<VisuVertex,VisuEdge> getCurrentLayout(){
		return this.layout;
	}
	
	public void applyLayout(Layout<VisuVertex,VisuEdge> layout ){
		this.layout = layout;
		for(VisuVertex v: this.jungGraphe.getVertices()){
			//this.getChildren().add(v);
			Point2D p = this.layout.transform(v);
			v.setX(p.getX());
			v.setY(p.getY());
		}
		
		for(VisuEdge e: this.jungGraphe.getEdges()){
			//this.getChildren().add(v);
			e.refreshLine();
		}
	}
	
	//methods to update edges when nodes are updated (setting coordinates)
}
