package graphe;

import graphvisunits.VisuEdge;
import graphvisunits.VisuVertex;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class MusicalEdge extends VisuEdge{
	private Label edgeLabel;
	private int weight = 0;
	public MusicalEdge(VisuVertex start, VisuVertex end) {
		super(start, end);
		this.edgeLabel = new Label();

		this.setOnFXCommEvent(new EventHandler<FXCommEvent>(){
			public void handle(FXCommEvent me){
				commHandler(me);
			}
		});
		this.getChildren().add(this.edgeLabel);
	}
	
	/*sarra */
	/* autre constructeur*/
	public MusicalEdge(VisuVertex start, VisuVertex end,String label) {
		super(start, end);
		this.edgeLabel = new Label(label);
		this.setOnFXCommEvent(new EventHandler<FXCommEvent>(){
			public void handle(FXCommEvent me){
				commHandler(me);
			}
		});
		this.getChildren().add(this.edgeLabel);
	}
	/* fin sarra*/
	public void commHandler(FXCommEvent me){
		this.edgeLine.setStroke(Color.GREEN);
		me.consume(); 
	}
	 public final void setOnFXCommEvent(
		        EventHandler<? super FXCommEvent> value) {
		    this.addEventHandler(FXCommEvent.COMM, value);
	}
	 
	 
	 public int getWeight (){
		 return this.weight;
	 }
	 public Label getLabel (){
		 return this.edgeLabel;
	 }
	 public void setWeight(int weight){
		 if (weight < 0)
			 this.weight = 0;
			 
		 this.weight = weight;
	 }
}
