package graphe;

import java.awt.Dimension;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import musicalLayout.GridLayout2;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import graphvisunits.VisuEdge;
import graphvisunits.VisuGraph;
import graphvisunits.VisuVertex;

public class AnalyseMusical extends Application{
	private MusicalGraph graphe; 
	
	public void init(){
    	Graph<VisuVertex,VisuEdge> jungGraph = BuildGraph.buildRandomMusicalGraph();
		this.graphe = new MusicalGraph(jungGraph);
		
    	Layout<VisuVertex,VisuEdge> layout = new GridLayout2<VisuVertex,VisuEdge>(graphe.getJungGraphe());
    	layout.setSize(new Dimension(700,700));
    	graphe.applyLayout(layout);
	}
	@Override
	public void start(Stage stage) throws Exception {
		   StackPane pane = new StackPane();
		   pane.setPrefSize(700,700); //set a default size for your stackpane
		   pane.getChildren().add(this.graphe);
		   StackPane.setAlignment(this.graphe,Pos.CENTER);
		   
		Scene s = new Scene(pane);	
		
		//root.getChildren().add(pane);
		stage.setScene(s);
		stage.show();
		
	}
	public static void main (String args[]){
		Application.launch(AnalyseMusical.class,args);
	}
	

}
