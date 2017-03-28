package graphe;

import graphvisunits.VisuEdge;
import graphvisunits.VisuVertex;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MusicalEdge extends VisuEdge{
	private int weight = 0;
	public MusicalEdge(VisuVertex start, VisuVertex end) {
		super(start, end);
		//show weight of each edge
		
		this.setOnFXCommEvent(new EventHandler<FXCommEvent>(){
			public void handle(FXCommEvent me){
				commHandler(me);
			}
		});
		
	}
	public void commHandler(FXCommEvent me){
		this.edgeLine.setStroke(Color.RED);
		me.consume(); 
	}
	 public final void setOnFXCommEvent(
		        EventHandler<? super FXCommEvent> value) {
		    this.addEventHandler(FXCommEvent.COMM, value);
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
