package graphe;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import fxunits.FXgraphe;
import graphvisunits.VisuVertex;

public class MusicalVertex extends VisuVertex{
	//champs pour les algos
	//algo bellman-ford
	private double bf_distance = Double.MAX_VALUE;
	private MusicalEdge bf_predArrete = null;
	public MusicalVertex(double uniqueID, double i, double j) {
		super(uniqueID, i, j);
		//this.getChildren().add(this.vertexShape);
		//interactions
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
		this.vertexShape.setFill(Color.RED);
		me.consume();
	}
	 public final void setOnFXCommEvent(
		        EventHandler<? super FXCommEvent> value) {
		    this.addEventHandler(FXCommEvent.COMM, value);
		}
	 public void appuyer(MouseEvent me){
			this.vertexShape.setFill(Color.GREEN);
			MusicalGraph parentGraph = (MusicalGraph)this.getParent();	
			Event.fireEvent(parentGraph, new FXCommEvent(this));
			me.consume(); 
	}
	 public double getBf_distance(){
		 return this.bf_distance;
	 }
	 public void setBf_distance(double distance){
		 this.bf_distance=distance;
	 }
	 public MusicalEdge getBf_predArrete(){
		 return this.bf_predArrete;
	 }
	 public void setBf_predArrete(MusicalEdge predArrete){
		 this.bf_predArrete = predArrete;
	 }
	 
	
}
