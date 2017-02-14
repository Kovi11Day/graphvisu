package user.client3.visualize;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import user.client3.graphes.Graphe1;
import user.client3.structgraphe.MyEdge;
import user.client3.structgraphe.MyGraphe;
import user.client3.structgraphe.MyVertexe;
import converters.Converter;
import converters.GenericGraphToFXGraphConverter;
import edu.uci.ics.jung.graph.Graph;
import fxunits.FXgraphe;


//**************************************************/
//build jung graph (50 nodes, 5 edges)
//Vertex types are defined by user
//each node stores a string
//EXPERIMENT: change  layouts
//Convert jung graph to FXgraph and visualise
//**************************************************/
public class Demo1 extends Application{
	@Override
	public void start(Stage stage) throws Exception {
		//provide client graph here
		Graphe1 dataGraph = new Graphe1();
		//convert-client-to-fx
		Converter<Graph<MyVertexe,MyEdge>,FXgraphe> converter = new GenericGraphToFXGraphConverter<>(dataGraph.getVertices(),dataGraph.getEdges());
		FXgraphe visuGraphe = converter.convert(700,700, "GridLayout");
	
		//display
		
		visuGraphe.getJungGraphe();
		
		   StackPane pane = new StackPane();
		   pane.setPrefSize(700,700); //set a default size for your stackpane
		   pane.getChildren().add(visuGraphe);
		   StackPane.setAlignment(visuGraphe,Pos.CENTER);
		   
		Scene s = new Scene(pane);	
		
		//root.getChildren().add(pane);
		stage.setScene(s);
		stage.show();
		
	}
	public static void main (String args[]){
		Application.launch(Demo1.class,args);
	}
}
