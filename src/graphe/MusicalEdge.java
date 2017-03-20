package graphe;

import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import graphvisunits.VisuEdge;
import graphvisunits.VisuVertex;

public class MusicalEdge extends VisuEdge{

	public MusicalEdge(VisuVertex start, VisuVertex end) {
		super(start, end);
		
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
}
