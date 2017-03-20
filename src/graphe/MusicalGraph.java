package graphe;

import java.util.Collection;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import edu.uci.ics.jung.graph.Graph;
import fxunits.FXedge;
import fxunits.FXvertexe;
import graphvisunits.VisuEdge;
import graphvisunits.VisuGraph;
import graphvisunits.VisuVertex;

public class MusicalGraph extends VisuGraph{

	public MusicalGraph(Graph<VisuVertex, VisuEdge> jungGraphe) {
		super(jungGraphe);
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
}
