package graphe;

import java.awt.Dimension;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import musicalLayout.GridLayout2;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import graphvisunits.VisuEdge;
import graphvisunits.VisuVertex;
import javafx.event.EventHandler;
public class AnalyseMusical extends Application{
	private MusicalGraph graphe; 
	
	public void init(){
    	Graph<VisuVertex,VisuEdge> jungGraph = BuildGraph.buildRandomMusicalGraph();
		this.graphe = new MusicalGraph(jungGraph);
		/*sarra*/
		//Test
		//graphe.setWeight4Music("T1");
		//graphe.setWeight4Music("0.01.0");
		for(VisuEdge v: jungGraph.getEdges()) {
			if(v instanceof MusicalEdge){
				MusicalEdge e = (MusicalEdge) v;
				System.out.println("Poid :"+e.getWeight());
			}
		}
		//fin teste
		/*fin sarra*/
		this.graphe.setNbSommetsColonne(5);
		this.graphe.setNbSommetsLigne(5);

		
		this.graphe.addSrcDest();
    	Layout<VisuVertex,VisuEdge> layout = new GridLayout2<VisuVertex,VisuEdge>(graphe.getJungGraphe());
    	layout.setSize(new Dimension(1200,1200));
    	graphe.applyLayout(layout);
	}
	
	public void shortestPath(){
    	/*MusicalVertex source = graphe.getMusicalVertex(0);
    	MusicalVertex destination = graphe.getMusicalVertex(4);
    	source.getVertexShape().setFill(Color.AQUA);
    	destination.getVertexShape().setFill(Color.BLUEVIOLET);*/
    	MusicalVertex source = graphe.getSrc();
    	MusicalVertex destination = graphe.getDst();

    	//AlgoAnalyseMusical algo = new AlgoAnalyseMusical(graphe);
    	//HashMap<MusicalVertex, Couple> bellmanFord = algo.bellmanFord(source);
    	//ArrayList<MusicalEdge> shortestPath = this.graphe.shortestPath(source,destination);
    	ArrayList<MusicalEdge> shortestPath = this.graphe.findEtiquette(source,destination,"T2");

    	System.out.println("path:" + shortestPath);
    	MusicalEdge e; int count = 0;
    	for(int i = 0; i < shortestPath.size(); i++){
    		e = shortestPath.get(i);
    		count++;
    	//for(MusicalEdge e: shortestPath){
    		e.getEdgeLine().setStroke(Color.RED);
    		e.getLabel().setVisible(true);
    		//e.activate();
    		//e.refreshLine();
    		//e.getEdgeShape().setFill(Color.AQUA);
    	}
    	System.out.println("count=" + count);
    	for(int i = 0; i < shortestPath.size(); i++){
    		e = shortestPath.get(i);
    		e.getEdgeLine().setStroke(Color.RED);
    		e.getEdgeLine().setStrokeWidth(4.);
    		//e.refreshLine();
    	}
	}
	
	public void generateNewGraph(StackPane pane){
		   pane.getChildren().remove(this.graphe);
		   this.init();
		   this.shortestPath(); 
		   pane.getChildren().add(this.graphe);
	}
	private void setGlobalEventHandler(StackPane root, StackPane pane) {
	    root.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
	        if (ev.getCode() == KeyCode.ENTER) {
	        	System.out.println("yeaaa");
	        	generateNewGraph(pane);
	           ev.consume(); 
	        }
	        
	    });
	}
	@Override
	public void start(Stage stage) throws Exception {
		   StackPane pane = new StackPane();
		   //pane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
		  // pane.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>());
		   pane.setPrefSize(1000,1000); //set a default size for your stackpane
	    	this.shortestPath();
		   pane.getChildren().add(this.graphe);
		   //pane.getChildren().remove(this.graphe);
		   //generateNewGraph(pane);
		   StackPane.setAlignment(this.graphe,Pos.CENTER);
		   
		Scene s = new Scene(pane);	
		   setGlobalEventHandler(pane,pane);

		//root.getChildren().add(pane);
		stage.setScene(s);
		stage.show();
		
	}
	public static void main (String args[]){
		Application.launch(AnalyseMusical.class,args);
	}
	

}