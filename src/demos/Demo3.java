package demos;

import user.struct1.MyVertex;
import visualizeGraphe.FXgraphe;
import converters.Converter;
import converters.JungGraphToFXGraphConverter;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.io.GraphMLReader;
import graphml.GraphMLDemo.edge;
import graphml.GraphMLDemo.node;
import graphml.EdgeFactory;
import graphml.VertexFactory;
//**************************************************/
//import GraphML format file
//Convert jung graph to FXgraph and visualise
//**************************************************/
public class Demo3 extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		
		//read graph form file
		String filename = "attributes.graphml";
		
	    GraphMLReader<UndirectedGraph<node, edge>, node, edge> gmlr =
	            new GraphMLReader<UndirectedGraph<node, edge>, node, edge>(new VertexFactory(), new EdgeFactory());
	   final UndirectedGraph<node, edge> graph = new UndirectedSparseMultigraph<node, edge>();
	   gmlr.load(filename, graph);
	   
	   
	  
		//////convert-jung-to-fx
	   Converter<Graph<node,edge>,FXgraphe> converter2 = new JungGraphToFXGraphConverter<>(graph,"CircleLayout");//CircleLayout
		FXgraphe output2 = converter2.convert(1000,500);
		
		//display
		Group root = new Group();
		//Scene s = new Scene(root,1200,800);	
		
		   StackPane pane = new StackPane();
		   pane.setPrefSize(1200,800); //set a default size for your stackpane
		   pane.getChildren().add(output2);
		   StackPane.setAlignment(output2,Pos.CENTER);
		   
		Scene s = new Scene(pane);	
		
		//root.getChildren().add(pane);
		stage.setScene(s);
		stage.show();
	        
	}
	public static void main (String args[]){
		Application.launch(Demo3.class,args);
	}
}
