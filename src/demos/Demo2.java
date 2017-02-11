package demos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import user.struct1.MyVertex;
import visualizeGraphe.FXgraphe;
import converters.Converter;
import converters.JungGraphToFXGraphConverter;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;


//**************************************************/
//build jung graph (50 nodes, 5 edges)
//Vertex types are defined by user
//each node stores a string
//EXPERIMENT: change  layouts
//Convert jung graph to FXgraph and visualise
//**************************************************/
public class Demo2 extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		////////
		//provide jung graph here
		LinkedList<MyVertex> sommets = new LinkedList<>();
    	Graph<MyVertex, String> graphe2 = new SparseGraph<MyVertex, String>();
    	int num = 100;
    	int e = 50;
		for (int i = 0; i < num; i++){
    		sommets.add(new  MyVertex(String.valueOf(i)));
		}

    	for (int i = 0; i < num; i++){
    		graphe2.addVertex(sommets.get(i));
    	}

    	for (int i = 0; i < e; i++){
    		Random r = new Random();
    		graphe2.addEdge(String.valueOf(i+e), sommets.get(r.nextInt(e)),sommets.get(r.nextInt(e)));
    	}
		///////
		
	
		//////convert-jung-to-fx
		Converter<Graph<MyVertex,String>,FXgraphe> converter2 = new JungGraphToFXGraphConverter<>(graphe2,"FRLayout");//CircleLayout
		FXgraphe output2 = converter2.convertExtra(1000,500);
		
		
		//Scene s = new Scene(root,1200,800);	
		
		   StackPane pane = new StackPane();
		   pane.setPrefSize(1100,700); //set a default size for your stackpane
		   pane.getChildren().add(output2);
		   StackPane.setAlignment(output2,Pos.CENTER);
		   
		Scene s = new Scene(pane);	
		   
		//root.getChildren().add(pane);
		stage.setScene(s);
		stage.show();
		
	}
	public static void main (String args[]){
		Application.launch(Demo2.class,args);
	}
}
