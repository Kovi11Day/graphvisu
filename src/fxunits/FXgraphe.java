package fxunits;


import java.util.Collection;

import edu.uci.ics.jung.graph.Graph;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;



public class FXgraphe extends Parent{
	private Graph<FXvertexe,FXedge> jungGraphe; 
	
	public FXgraphe(Graph<FXvertexe,FXedge> jungGraphe){
		this.jungGraphe = jungGraphe; 
		for(FXvertexe v: jungGraphe.getVertices()){
			this.getChildren().add(v);
		}
		for(FXedge e: jungGraphe.getEdges()){
			this.getChildren().add(e);
		}
		//Interaction
				this.setOnMouseEntered(new EventHandler<MouseEvent>(){
					public void handle(MouseEvent me){
					}
				});
				
				this.setOnMouseExited(new EventHandler<MouseEvent>(){
					public void handle(MouseEvent me){
					}
				});
				
				this.setOnMousePressed(new EventHandler<MouseEvent>(){
					public void handle(MouseEvent me){
						appuyer(me);
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
		FXvertexe vertexClicked = (FXvertexe)me.getParameter();
		Collection<FXedge> incidentEdges = this.jungGraphe.getInEdges(vertexClicked);
		for(FXedge edge: incidentEdges){
			Event.fireEvent(edge, new FXCommEvent(null));
		}
	}
	
	 public final void setOnFXCommEvent(
		        EventHandler<? super FXCommEvent> value) {
		    this.addEventHandler(FXCommEvent.COMM, value);
		}
	public Graph<FXvertexe,FXedge> getJungGraphe(){
		return this.jungGraphe;
	}
	
	public void appuyer(MouseEvent me){
	}
	
}
