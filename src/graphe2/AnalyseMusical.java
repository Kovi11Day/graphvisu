package graphe2;

import java.awt.Dimension;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import musicalLayout.GridLayout2;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import graphvisunits2.VisuEdge;
import graphvisunits2.VisuVertex;

public class AnalyseMusical extends Application{
	
	private MusicalGraph graphe; 
	
	public void init(){
    	Graph<VisuVertex,VisuEdge> jungGraph = BuildGraph.buildRandomMusicalGraph(24,10);
		this.graphe = new MusicalGraph(jungGraph);
		//ATTENTION!!!: ne pas oublier de set ligne et col (sinon algo ne marche pas)
		this.graphe.setNbLigne(24);
		this.graphe.setNbcol(10);

		Layout<VisuVertex,VisuEdge> layout = new GridLayout2<VisuVertex,VisuEdge>(graphe.getJungGraphe(), 110, 30);
		//Layout<VisuVertex,VisuEdge> layout = new CircleLayout<VisuVertex,VisuEdge>(graphe.getJungGraphe());
    	layout.setSize(new Dimension(200,200));

    	//layout.setSize(new Dimension(1200,1200));
    	graphe.applyLayout(layout);
    	
		/*
		 * DEMO: recherche pattern
		 */
		/*ArrayList<String> pattern = new ArrayList<>();
		pattern.add("T1"); pattern.add("T1"); //pattern.add("T1");
		//ATTENTION: pattern doit etre plus long que 1
    	for(MusicalEdge e: graphe.chercherCheminAvecPattern(pattern)){
    		e.getEdgeLine().setStroke(Color.RED);
    		e.getEdgeLine().setStrokeWidth(4.);
			e.getLabel().setTextFill(Color.RED);
    		e.getLabel().setVisible(true);
    	}*/
    	
    	/*
    	 * DEMO: recherche etiquette
    	 */
    	
    	for(MusicalEdge e: graphe.chercherCheminAvecEtiquette("T1")){
  		e.getEdgeLine().setStroke(Color.RED);
		e.getEdgeLine().setStrokeWidth(4.);
		e.getLabel().setTextFill(Color.RED);
		e.getLabel().setVisible(true);
		}
		
	
	}

	private void setGlobalEventHandler(StackPane root, StackPane pane) {
	    root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	           ev.consume(); 
	        }
	        
	    });
	}
	@Override
	public void start(Stage stage) throws Exception {
		   StackPane pane = new StackPane();
		   pane.setPrefSize(1000,1000);
		   pane.getChildren().add(this.graphe);

		   StackPane.setAlignment(this.graphe,Pos.CENTER);
		   
		Scene s = new Scene(pane);	
		   setGlobalEventHandler(pane,pane);
		stage.setScene(s);
		stage.show();
		
	}
	public static void main (String args[]){
		Application.launch(AnalyseMusical.class,args);
	}
	

}