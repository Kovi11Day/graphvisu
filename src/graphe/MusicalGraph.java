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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

//Contains graph of FX nodes and edges i.e. nodes and edges can be visualised
//Is a graph + a layout
//Allows to access specific vertex directly (with jung whole collection of vertices mut be obtained first)...WHY?
//musical analysis algortihmes can be applied to it
public class MusicalGraph extends VisuGraph{
	private int nbSommetsColonne;
	private int nbSommetsLigne;
	MusicalVertex src;
	MusicalVertex dst;

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
			System.out.println("received event graph fired to edge through bubbling...shouldn't happen");
		}
		MusicalVertex vertexClicked = (MusicalVertex)me.getParameter();
		Collection<VisuEdge> incidentEdges = this.jungGraphe.getOutEdges(vertexClicked);
		for(VisuEdge edge: incidentEdges){
			Event.fireEvent((MusicalEdge)edge, new FXCommEvent(null));
		}
	}
	public ArrayList<MusicalEdge> findEtiquette (MusicalVertex src, MusicalVertex dest, String etiquette){
		//BuildGraph.grapheComplet(jungGraphe, getNbSommetsColonne());
		this.setWeight4Music(etiquette);
		//apply_bellmanFord (src);  //TODO: apply only when required (when structure graph modified)
		ArrayList<MusicalEdge> result = this.shortestPath(src, dest);
		for(MusicalEdge e: result){
			e.getLabel().setVisible(true);
    		e.getEdgeLine().setStroke(Color.BLUEVIOLET);
    		e.getEdgeLine().setStrokeWidth(4.);
		}
		int total = 0;
		for(MusicalEdge e: result){
			total += e.getWeight();
		}
		if (total > this.nbSommetsLigne - 1 ){
			return new ArrayList<MusicalEdge>();
		}else
			return result;

	}
	//ameliorer pour eviter moins d'appels vers bellmanFord
	public ArrayList<MusicalEdge> shortestPath(MusicalVertex src, MusicalVertex dest){
		//1)REMPLIR TROU
			this.jungGraphe=BuildGraph.grapheComplet(jungGraphe, getNbSommetsColonne());
		//2)AJOUTER SOURCE ET DEST
			//this.addSrcDest();
		//3)PONDERER ARRETES
		//4)BELLMANFORD
		apply_bellmanFord (src);  //TODO: apply only when required (when structure graph modified)
		//5)RECUPERATION CHEMIN
		ArrayList<MusicalEdge> result = new ArrayList<>();
		while(dest.getBf_predArrete()!=null){
			result.add(dest.getBf_predArrete());
			dest = (MusicalVertex) this.jungGraphe.getSource(dest.getBf_predArrete());
		}
		result.remove(nbSommetsLigne);
		result.remove(0);
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
		double labelX,labelX1;double labelY,labelY1;double m,tmp;
		for(VisuEdge e: this.jungGraphe.getEdges()){
			edge = (MusicalEdge)e;
			v1 = (MusicalVertex)e.getStartVertex();
			v2 = (MusicalVertex)e.getEndVertex();
			
			//Calculate label coordinates (eviter chauvauchement)
			if (v2.getX() - v1.getX()==0)m=Double.MAX_VALUE;
			else m = (v2.getY() - v1.getY() )/(v2.getX() - v1.getX());
			System.out.println("gradddddd="+m);
			double c = v1.getY() - m* v1.getX();
			if(1-m*m==0)tmp=50;
			else tmp =20/ (1-m*m);
			System.out.println("tmppppppp="+tmp);
			//labelX = v1.getX() +tmp;// Math.sqrt(tmp);
			//labelY = m*labelX+c;
			//double r =0.5-(Math.random()/5);
			double r =0.5-(Math.random());

			labelX = (v1.getX() + v2.getX())*0.5; //+ 0.2*(v1.getX()-v2.getX());
			labelY= (v1.getY() + v2.getY())*0.5;// + 0.2*(v1.getY()-v2.getY());
			//labelX = (v2.getX() + labelX1)*0.5;
			//labelY= (v2.getY() + labelY1)*0.5;
			edge.getLabel().setLayoutX(labelX);
			edge.getLabel().setLayoutY(labelY);
			edge.getLabel().setFont(new Font("Arial",12));
			edge.getLabel().setVisible(false);
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
	 
	 /*sarrra */
	 // modifie le poid en fonction de l'etiquette de nom "et"
	 public void setWeight4Music(String et){
		 for(VisuEdge e :jungGraphe.getEdges()){
			 if(e instanceof MusicalEdge){
				 MusicalEdge edge = (MusicalEdge) e;
				 if(edge.getLabel().getText().equals(et)){
					 edge.setWeight(1);
				 }
				 else{
					 edge.setWeight(2);
				 }
			 }
		 }
	 }
	 // fin sarra

	 public void addSrcDest(){
		 MusicalVertex src = new MusicalVertex(-1, 6, 6);
		 MusicalVertex dst = new MusicalVertex(-1, this.nbSommetsColonne+1, this.nbSommetsLigne+1);
			//this.jungGraphe.addVertex(src);
			//this.jungGraphe.addVertex(dst);
		 this.setSrc(src);
		 this.setDst(dst);
		 MusicalEdge edge;
		 MusicalVertex mv;
		 for(VisuVertex v: this.jungGraphe.getVertices()){
			 	mv= (MusicalVertex)v;
			 	if (mv.getI() == 0){
				 edge = (new MusicalEdge(src, mv,"X"));
				 edge.setWeight(1);
				 this.jungGraphe.addEdge(edge, src, mv);
			 	}
				if (mv.getI() == this.nbSommetsColonne-1){
					 edge = (new MusicalEdge(mv, dst,"X"));
					 edge.setWeight(1);
					 this.jungGraphe.addEdge(edge, mv, dst);
				 	}
			 	
			 }
		 }
	
	 public int getNbSommetsColonne(){
		 return this.nbSommetsColonne;
	 }
	 public void setNbSommetsColonne(int nb){
		 this.nbSommetsColonne = nb;
	 }
	 public int getNbSommetsLigne(){
		 return this.nbSommetsLigne;
	 }
	 
	 public void setNbSommetsLigne(int nb){
		 this.nbSommetsLigne = nb;
	 }
	 
	 public void setSrc(MusicalVertex src){
		 this.src = src;
		 if (this.src != null){
			 this.jungGraphe.removeVertex(this.src);
		 }
		 this.jungGraphe.addVertex(src);
	 }
	 public void setDst(MusicalVertex dst){
		 this.dst = dst;
		 if (this.src != null){
			 this.jungGraphe.removeVertex(this.dst);
		 }
		 this.jungGraphe.addVertex(dst);
	 }
	 public MusicalVertex getSrc(){
		 return this.src;
	 }
	 public MusicalVertex getDst(){
		 return this.dst;
	 }
}