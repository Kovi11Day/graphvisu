package graphe;

import edu.uci.ics.jung.algorithms.layout.Layout;
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
//Allows to access specific vertex directly (with jung whole collection of vertices mut be obtained first)...WHY?
//musical analysis algortihmes can be applied to it
public class MusicalGraph extends VisuGraph{
	//private ArrayList<MusicalVertex> vertices;
	public MusicalGraph(Graph<VisuVertex, VisuEdge> jungGraphe) {
		super(jungGraphe);
		/*this.vertices = new ArrayList<>();
		for (VisuVertex v: jungGraphe.getVertices()){
			vertices.add((MusicalVertex)v);
		}*/
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
		Collection<VisuEdge> incidentEdges = this.jungGraphe.getOutEdges(vertexClicked);
		for(VisuEdge edge: incidentEdges){
			Event.fireEvent((MusicalEdge)edge, new FXCommEvent(null));
		}
	}
	//ameliorer pour eviter moins d'appels vers bellmanFord
	public ArrayList<MusicalEdge> shortestPath(MusicalVertex src, MusicalVertex dest){
		apply_bellmanFord (src);
    	//MusicalVertex dest = this.getMusicalVertex(4);

		ArrayList<MusicalEdge> result = new ArrayList<>();
		while(dest.getBf_predArrete()!=null){
			result.add(dest.getBf_predArrete());
			dest = (MusicalVertex) this.jungGraphe.getSource(dest.getBf_predArrete());
		}
		System.out.println("Result:");
		MusicalVertex source, destination;
		for(VisuEdge e: result){
			source = (MusicalVertex) this.jungGraphe.getSource(e);
			destination = (MusicalVertex) this.jungGraphe.getDest(e);
			System.out.println("source: ("+ source.getI() + "," + source.getJ() + ")" + 
					"dest: ("+ destination.getI() + "," + destination.getJ() + ")");
		}
		return result;
	}
	public void apply_bellmanFord (MusicalVertex src){
		//arg passed by val or by ref??
		//MusicalVertex src = this.getMusicalVertex(0);
		src.setBf_distance(0.0);
		//this.getMusicalVertex(0).setBf_distance(0.0);
		MusicalEdge me; 
		MusicalVertex u , v;
		int nbVertices = this.jungGraphe.getVertices().size();
		for (int i = 0; i < nbVertices; i++){
			for (Object e: this.jungGraphe.getEdges()){
				 me = (MusicalEdge)e;
				 u =(MusicalVertex)jungGraphe.getSource(me);
				 v = (MusicalVertex)jungGraphe.getDest(me);
			//System.out.println("u.distance="+u.getBf_distance()+" me.Weight+"+me.getWeight()+" v.distance"+v.getBf_distance()+"\n");
				 
				if (u.getBf_distance() + me.getWeight() < v.getBf_distance()){
					System.out.println("u.distance="+u.getBf_distance()+" me.Weight+"+me.getWeight()+" v.distance"+v.getBf_distance()+"\n");
					v.setBf_distance(u.getBf_distance() + me.getWeight());
					v.setBf_predArrete(me);
				}
			}
		}
		for(VisuVertex vertex: this.jungGraphe.getVertices()){
			MusicalVertex ver = (MusicalVertex)vertex;
			System.out.println("vertex:(" + ver.getI() +","+ver.getJ()+ "):"
					+"pred:"+ver.getBf_predArrete()+ "                  distance:" + ver.getBf_distance());
		}
	}
	public void applyLayout(Layout<VisuVertex,VisuEdge> layout ){
		super.applyLayout(layout);
		//calculer position des labels des arretes
		MusicalEdge edge;
		MusicalVertex v1;
		MusicalVertex v2;
		double labelX;double labelY;
		for(VisuEdge e: this.jungGraphe.getEdges()){
			edge = (MusicalEdge)e;
			v1 = (MusicalVertex)e.getStartVertex();
			v2 = (MusicalVertex)e.getEndVertex();
			labelX = (v1.getX() + v2.getX())*0.5;
			labelY = (v1.getY() + v2.getY())*0.5;
			edge.getLabel().setLayoutX(labelX);
			edge.getLabel().setLayoutY(labelY);
		}
	}
	
	 public final void setOnFXCommEvent(
		        EventHandler<? super FXCommEvent> value) {
		    this.addEventHandler(FXCommEvent.COMM, value);
		}
	 
	 public MusicalVertex getMusicalVertex(double id){
		 MusicalVertex m;
		 for (VisuVertex v : this.jungGraphe.getVertices()){
			 m = (MusicalVertex)v;
			 if (m.getUniqueID()==id)
				 return m;		
		 }
		 /*for (MusicalVertex v: this.vertices){
			 if (v.getUniqueID()==id)
				 return v;			 
		 }*/
		 System.out.println("destination not found");
		 return null;
	 }
}
