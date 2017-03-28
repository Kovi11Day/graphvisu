package graphe;

import edu.uci.ics.jung.graph.Graph;
import graphvisunits.VisuEdge;
import graphvisunits.VisuGraph;
import graphvisunits.VisuVertex;

import java.util.ArrayList;
import java.util.Collection;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

//Contains graph of FX nodes and edges i.e. nodes and edges can be visualised
//Is a graph + a layout
//Allows to access specific vertex directly (with jung whole collection of vertices mut be obtained first)
public class MusicalGraph extends VisuGraph{
	private ArrayList<MusicalVertex> vertices;
	public MusicalGraph(Graph<VisuVertex, VisuEdge> jungGraphe) {
		super(jungGraphe);
		this.vertices = new ArrayList<>();
		for (VisuVertex v: jungGraphe.getVertices()){
			vertices.add((MusicalVertex)v);
		}
		//interactions
		this.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
			}
		});
		
		this.setOnMouseReleased(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
			}
		});	
		
		this.setOnFXCommEvent(new EventHandler<FXCommEvent>(){
			public void handle(FXCommEvent me){
				commHandler(me);
			}
		});
	}
	public void commHandler(FXCommEvent me){
		if(me.getParameter()==null){
			System.out.println("received event graph fired to edge throught bubbling...shouldn't happen");
		}
		MusicalVertex vertexClicked = (MusicalVertex)me.getParameter();
		Collection<VisuEdge> incidentEdges = this.jungGraphe.getInEdges(vertexClicked);
		for(VisuEdge edge: incidentEdges){
			Event.fireEvent((MusicalEdge)edge, new FXCommEvent(null));
		}
	}
	
	 public final void setOnFXCommEvent(
		        EventHandler<? super FXCommEvent> value) {
		    this.addEventHandler(FXCommEvent.COMM, value);
		}
	 
	 public MusicalVertex getMusicalVertex(double id){
		 for (MusicalVertex v: this.vertices){
			 if (v.getUniqueID()==id)
				 return v;			 
		 }
		 System.out.println("destination not found");
		 return null;
	 }
}
