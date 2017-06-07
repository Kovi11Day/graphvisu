package graphe2;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import graphe.FXCommEvent;
import graphvisunits2.VisuEdge;
import graphvisunits2.VisuVertex;

public class MusicalEdge extends VisuEdge{
	//type of transition or inversion (provided by client)
	private Label edgeLabel;
	//field used for algorithm
	private int weight = 0;
	
	//constructor used by client
	public MusicalEdge(VisuVertex start, VisuVertex end, String label) {
		super(start, end);
		this.edgeLabel = new Label(label);
		/*this.setOnFXCommEvent(new EventHandler<FXCommEvent>(){
			public void handle(FXCommEvent me){
				commHandler(me);
			}
		});*/
		this.getChildren().add(this.edgeLabel);
	}
	/*
	//constructor used for algorithms
	public MusicalEdge(VisuVertex start, VisuVertex end) {
		super(start, end);
	}
	*/
	//getters and setters
	 public Label getLabel (){
		 return this.edgeLabel;
	 }
	 
	 public int getWeight (){
		 return this.weight;
	 }

	 public void setWeight(int weight){
		 if (weight < 0)
			 this.weight = 0;
			 
		 this.weight = weight;
	 }
}
