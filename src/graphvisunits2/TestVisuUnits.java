package graphvisunits2;

import java.awt.Dimension;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;

public class TestVisuUnits extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		//Create Jung Graph
		Graph<VisuVertex,VisuEdge> graph = new DirectedSparseMultigraph<>();
		//Create Vertices (store them to be able to add edges)
		ArrayList<VisuVertex> arr = new ArrayList<>();
		for (int i = 0; i < 10; i++){
			arr.add(new VisuVertex());
			graph.addVertex(arr.get(i));
		}
		//add edges
		graph.addEdge(new VisuEdge(arr.get(0), arr.get(1)), arr.get(0), arr.get(1));
		graph.addEdge(new VisuEdge(arr.get(1), arr.get(2)), arr.get(1), arr.get(2));
		graph.addEdge(new VisuEdge(arr.get(2), arr.get(3)), arr.get(2), arr.get(3));
		graph.addEdge(new VisuEdge(arr.get(3), arr.get(4)), arr.get(2), arr.get(4));
		
		//create VisuGraph
		VisuGraph g = new VisuGraph(graph);
		
		//layout
		CircleLayout<VisuVertex, VisuEdge> l = new CircleLayout<>(graph);
		l.setSize(new Dimension(500, 500));

		g.applyLayout(l);
		
		//application
		   StackPane pane = new StackPane();
		   pane.setPrefSize(1000,1000); 
		   pane.getChildren().add(g);
		   StackPane.setAlignment(g,Pos.CENTER);
		   
		Scene s = new Scene(pane);	

		stage.setScene(s);
		stage.show();
		
	}
	public static void main (String args[]){
		Application.launch(TestVisuUnits.class,args);
	}
	

}
