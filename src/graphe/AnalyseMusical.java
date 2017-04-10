package graphe;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import musicalLayout.GridLayout2;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import graphvisunits.VisuEdge;
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
	
	public void shortestPath(){
		//lancer algo de bellman 
    	MusicalVertex source = graphe.getMusicalVertex(0);
    	MusicalVertex destination = graphe.getMusicalVertex(4);
    	source.getVertexShape().setFill(Color.AQUA);
    	destination.getVertexShape().setFill(Color.BLUEVIOLET);
    	
    	//AlgoAnalyseMusical algo = new AlgoAnalyseMusical(graphe);
    	//HashMap<MusicalVertex, Couple> bellmanFord = algo.bellmanFord(source);
    	ArrayList<MusicalEdge> shortestPath = this.graphe.shortestPath(source,destination);
    	System.out.println("path:" + shortestPath);
    	MusicalEdge e;
    	for(int i = 0; i < shortestPath.size(); i++){
    		e = shortestPath.get(i);
    	//for(MusicalEdge e: shortestPath){
    		e.getEdgeLine().setStroke(Color.RED);
    		e.refreshLine();
    		//e.getEdgeShape().setFill(Color.AQUA);
    	}
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		   StackPane pane = new StackPane();
		   pane.setPrefSize(700,700); //set a default size for your stackpane
	    	this.shortestPath();
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
