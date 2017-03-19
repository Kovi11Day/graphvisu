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
	private Graph<VisuVertex,VisuEdge> graphe; 
	private Layout<VisuVertex,VisuEdge> layout;
	
	public void init(){
    	System.out.println("init starting");
		this.graphe = BuildGraph.buildRandomMusicalGraph();
    	System.out.println("build graph over");

    	layout = new GridLayout2<VisuVertex,VisuEdge>(this.graphe);
    	layout.setSize(new Dimension(700,700));
    	System.out.println("init over");
	}
	@Override
	public void start(Stage stage) throws Exception {
		   StackPane pane = new StackPane();
		   pane.setPrefSize(700,700); //set a default size for your stackpane
		   VisuGraph visuGraphe = new VisuGraph(this.graphe);
		   pane.getChildren().add(visuGraphe);
		  // Circle c = new Circle(100);
		   //c.setFill(Color.AQUA);
		   //pane.getChildren().add(c);
		   StackPane.setAlignment(visuGraphe,Pos.CENTER);
		   
		Scene s = new Scene(pane);	
		
		//root.getChildren().add(pane);
		stage.setScene(s);
		stage.show();
		
	}
	public static void main (String args[]){
		Application.launch(AnalyseMusical.class,args);
	}
	

}
