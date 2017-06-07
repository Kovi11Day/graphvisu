package graphe2;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import graphe.FXCommEvent;
import graphvisunits2.VisuVertex;

public class MusicalVertex extends VisuVertex{
	//logical order provided by client (optional)
	private double i = -1; 
	private double j = -1;
	//fields used for algorithms
	private double bf_distance = Double.MAX_VALUE;
	private MusicalEdge bf_predArrete = null;
	
	//constructor used by client
	public MusicalVertex(double i, double j) {
		super();
		this.i = i;
		this.j = j;
		//interactions
		/*this.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				appuyer(me);
			}
		});

		this.setOnFXCommEvent(new EventHandler<FXCommEvent>(){
			public void handle(FXCommEvent me){
				commHandler(me);
			}
		});*/
	}
	
	//constructor used for algorithm
	public MusicalVertex() {
		super();
	}
	
	//getters and setters
	public double getI() {
		return i;
	}

	public void setI(double i) {
		this.i = i;
	}

	public double getJ() {
		return j;
	}

	public void setJ(double j) {
		this.j = j;
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
