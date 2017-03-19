package graphvisunits;

import edu.uci.ics.jung.graph.Graph;
import javafx.scene.Parent;

public class VisuGraph extends Parent{
//private Graph<VisuVertex,VisuEdge> jungGraphe; 
	
	public VisuGraph(Graph<VisuVertex,VisuEdge> jungGraphe){
		//this.jungGraphe = jungGraphe; 
		for(VisuVertex v: jungGraphe.getVertices()){
			this.getChildren().add(v);
		}
		for(VisuEdge e: jungGraphe.getEdges()){
			this.getChildren().add(e);
		}
	}
	
	
}
