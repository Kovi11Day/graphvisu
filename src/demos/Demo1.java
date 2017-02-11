package demos;

import converters.Converter;
import converters.JungGraphToFXGraphConverter;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import visualizeGraphe.FXgraphe;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//**************************************************/
//build jung graph (2 nodes, 1 edge)
//Convert jung graph to FXgraph and visualise
//**************************************************/

public class Demo1 extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		////////
		//build jung graph here
		Graph<String,String> graphe = new SparseGraph<String, String>();
    	graphe.addVertex("contact1");
    	graphe.addVertex("contact2");
    	graphe.addVertex("contact3");
    	graphe.addEdge("lien1","contact1", "contact2");
		///////
		
		
		//////convert-jung-to-fx
		Converter<Graph<String,String>,FXgraphe> converter1 = new JungGraphToFXGraphConverter<>(graphe,"CircleLayout");
		FXgraphe output1 = converter1.convert(300,300);

		
		//root.getChildren().add(output1);
		   StackPane pane = new StackPane();
		   pane.setPrefSize(1100,700); //set a default size for your stackpane
		   pane.getChildren().add(output1);
		   StackPane.setAlignment(output1,Pos.CENTER);
		   
		Scene s = new Scene(pane);	
		   
		//root.getChildren().add(pane);
		stage.setScene(s);
		stage.show();
		
		stage.setScene(s);
		stage.show();
		
	}
	public static void main (String args[]){
		Application.launch(Demo1.class,args);
	}
}
