package graphe2;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import graphvisunits2.VisuEdge;
import graphvisunits2.VisuGraph;
import graphvisunits2.VisuVertex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MusicalGraph extends VisuGraph{
	//provided by client
	//private int nbSommetsColonne;
	//private int nbSommetsLigne;
	private int nbCol;
	private int nbLigne;
	//fields used for algorithms
	MusicalVertex src;
	MusicalVertex dst;

	public MusicalGraph(Graph<VisuVertex, VisuEdge> jungGraphe) {
		super(jungGraphe);
		/*
		this.setOnFXCommEvent(new EventHandler<FXCommEvent>(){
			public void handle(FXCommEvent me){
				commHandler(me);
			}
		});*/
	}
	
	/*public void commHandler(FXCommEvent me){
		if(me.getParameter()==null){
			System.out.println("received event graph fired to edge through bubbling...shouldn't happen");
		}
		MusicalVertex vertexClicked = (MusicalVertex)me.getParameter();
		Collection<VisuEdge> incidentEdges = this.jungGraphe.getOutEdges(vertexClicked);
		for(VisuEdge edge: incidentEdges){
			Event.fireEvent((MusicalEdge)edge, new FXCommEvent(null));
		}
	}*/
	
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
			double c = v1.getY() - m* v1.getX();
			if(1-m*m==0)tmp=50;
			else tmp =20/ (1-m*m);

			double r =0.5-(Math.random());

			labelX = (v1.getX() + v2.getX())*0.5;
			labelY= (v1.getY() + v2.getY())*0.5;

			edge.getLabel().setLayoutX(labelX);
			edge.getLabel().setLayoutY(labelY);
			edge.getLabel().setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 20));
			edge.getLabel().setVisible(false);
		}
	}
	//algorithms----------------------------------------------------------------------------------------------------------
	// affectation de poids
	 private void setWeight4Music(String et){
		 for(VisuEdge e :jungGraphe.getEdges()){
			 if(e instanceof MusicalEdge){
				 MusicalEdge edge = (MusicalEdge) e;
				 if(edge.getLabel().getText().equals(et)){
					 edge.setWeight(1);
				 }else{
					 edge.setWeight(2);
				 }
			 }
		 }
	 }
	 
	 // detecter et completer trous
	 private void grapheComplet() {
			Collection<VisuVertex> fv = jungGraphe.getVertices();
			Collection<VisuEdge> fe = jungGraphe.getEdges();
			MusicalVertex[] fvt = new MusicalVertex[fv.size()];
			MusicalEdge[] fet = new MusicalEdge[fe.size()];
			fv.toArray(fvt);
			fe.toArray(fet);

			for (int i = 0; i < nbCol; i++) {
				HashSet<MusicalVertex> x = new HashSet<MusicalVertex>();

				for (int j = 0; j < fvt.length; j++) {
					if (fvt[j].getI() == i && jungGraphe.outDegree(fvt[j]) == 0) {
						x.add(fvt[j]);
					}
				}
				if (x.size() == nbLigne && (i + 1) < nbCol) {
					HashSet<MusicalVertex> voisins = getVerticesColumn(i + 1, fvt);
					MusicalVertex[] array = new MusicalVertex[voisins.size()];
					voisins.toArray(array);
					for (int o = 0; o < fvt.length; o++) {
						for (int p = 0; p < array.length; p++) {
							if (fvt[o].getI() == i){
								MusicalEdge addedEdge = new MusicalEdge(fvt[o], array[p], "X");
								addedEdge.setWeight(1);
								this.jungGraphe.addEdge(addedEdge, fvt[o],array[p]);
							}
						}
					}
				}
			}

		}
	 
		private static HashSet<MusicalVertex> getVerticesColumn(int i, MusicalVertex[] f) {
			HashSet<MusicalVertex> h = new HashSet<MusicalVertex>();
			for (int k = 0; k < f.length; k++) {
				if (f[k].getI() == (i))
					h.add(f[k]);
			}
			return h;
		}
		
	// ajouter src et dst
		 private void addSrcDest(){
			 MusicalVertex src = new MusicalVertex();
			 MusicalVertex dst = new MusicalVertex();
			 this.setSrc(src);
			 this.setDst(dst);
			 MusicalEdge edge;
			 MusicalVertex mv;
			 for(VisuVertex v: this.jungGraphe.getVertices()){
				 	mv= (MusicalVertex)v;
				 	if (mv.getJ() == 0){
					 edge = (new MusicalEdge(src, mv, "X"));
					 edge.setWeight(1);
					 this.jungGraphe.addEdge(edge, src, mv);
				 	}
					if (mv.getJ() == this.nbCol-1){
						 edge = (new MusicalEdge(mv, dst, "X"));
						 edge.setWeight(1);
						 this.jungGraphe.addEdge(edge, mv, dst);
					 }
				 }
			 }
		 
	// bellman-ford (de src)
		 private void bellmanFord (){
				src.setBf_distance(0.0);
				MusicalEdge me; 
				MusicalVertex u , v;
				int nbVertices = this.jungGraphe.getVertices().size();
				for (int i = 0; i < nbVertices; i++){
					for (Object e: this.jungGraphe.getEdges()){
						 me = (MusicalEdge)e;
						 u =(MusicalVertex)jungGraphe.getSource(me);
						 v = (MusicalVertex)jungGraphe.getDest(me);
						 
						if (u.getBf_distance() + me.getWeight() < v.getBf_distance()){
							//System.out.println("u.distance="+u.getBf_distance()+" me.Weight+"+me.getWeight()+" v.distance"+v.getBf_distance()+"\n");
							v.setBf_distance(u.getBf_distance() + me.getWeight());
							v.setBf_predArrete(me);
						}
					}
				}
			}
		 
		//extraire chemin calculer par bellmanFord + interpreter resultat(de src a dst)
		 private ArrayList<MusicalEdge> extraireCheminDeBellmanFord(){
				ArrayList<MusicalEdge> result = new ArrayList<>();
				//extraire chemin
				MusicalEdge edgeIter = this.dst.getBf_predArrete(); 
				while(edgeIter != null){
					if (((MusicalVertex)jungGraphe.getDest(edgeIter)) != dst 
							&& ((MusicalVertex)jungGraphe.getSource(edgeIter)) != src){
						result.add(edgeIter);
						
						edgeIter.getEdgeLine().setStroke(Color.BLUEVIOLET);
						edgeIter.getEdgeLine().setStrokeWidth(4.);
						edgeIter.getLabel().setTextFill(Color.BLUEVIOLET);
						edgeIter.getLabel().setVisible(true);
					}
					edgeIter = ((MusicalVertex) jungGraphe.getSource(edgeIter)).getBf_predArrete();
				}
				
				//interpreter
				int ttlPoids = 0;
				for (MusicalEdge e: result){
					ttlPoids += e.getWeight();
				}
				
				if (ttlPoids == nbCol - 1)
					return result;
				else
					return new ArrayList<MusicalEdge>();
		 }
		 
		// mettre tout poids à 2: utiliser pour pattern
		 private void setWeightTo2(){
			 for(VisuEdge e :jungGraphe.getEdges()){
				 MusicalEdge edge = (MusicalEdge) e;
				 edge.setWeight(2);
			 }
		 }		 
		 
		 /*detecter suite de pattern et ajouter arrete de poids 1, fonction recursive
		  * principe: parcours en profondeur
		  * v1: debut de l'arete ajouter
		  * v2: fin de l'arete ajouter
		  */
		 private void ajouterAretePourPattern(MusicalVertex current, int curLabel, 
				 ArrayList<String> pattern, MusicalVertex v1, MusicalVertex v2, 
				 HashMap<MusicalEdge, ArrayList<MusicalEdge>> h,  ArrayList<MusicalEdge> arr){
			 if (current == dst)
				 return;
			// System.out.println("current ind=" + curLabel);
			 for(VisuVertex v: this.jungGraphe.getSuccessors(current)){
				 MusicalVertex succ =(MusicalVertex) v;
				 if (current == src || succ == dst){
					 ajouterAretePourPattern(succ, curLabel, pattern, v1, v2, h, arr);
				 }else{
					 VisuEdge e = jungGraphe.findEdge(current, succ);
					 if (e != null){ 
						 MusicalEdge me = (MusicalEdge)e;
						// if (me.getLabel()!= null){

						 //etiquette attendu
						 if (pattern.get(curLabel).equals(me.getLabel().getText())){
							 
							 if (curLabel == pattern.size() - 1){
								 MusicalEdge edge = (new MusicalEdge(v1, succ, "P"));
								 //To remove
								// edge.getEdgeLine().setStrokeWidth(2);
								// edge.getEdgeLine().setStroke(Color.CORAL);
								 //this.getChildren().add(edge);

								 //---
								 edge.setWeight(1);
								 
								 ArrayList<MusicalEdge> c_arr = (ArrayList<MusicalEdge>) arr.clone();
								 c_arr.add(me);
								 h.put(edge, c_arr);
												 
								 this.jungGraphe.addEdge(edge, v1, succ);	
								 ajouterAretePourPattern(succ, (curLabel + 1)%pattern.size(), pattern, null, null, h, new ArrayList<MusicalEdge>());
								 
							}else if (curLabel == 0){
								ArrayList<MusicalEdge> c_arr = (ArrayList<MusicalEdge>) arr.clone();
								 c_arr.add(me);
								 ajouterAretePourPattern(succ, (curLabel + 1)%pattern.size(), pattern, current, null, h, c_arr);
							}else{
								ArrayList<MusicalEdge> c_arr = (ArrayList<MusicalEdge>) arr.clone();
								 c_arr.add(me);
								 ajouterAretePourPattern(succ, (curLabel + 1)%pattern.size(), pattern, v1, v2, h, c_arr);
							}
							 
						 //pas etiquette attendu
						 }else{		

							 ajouterAretePourPattern(succ, 0, pattern, null, null, h, new ArrayList<MusicalEdge>() );
						 }
						 }
					 }
				 }
			 }
		// }
		 
		 private ArrayList<MusicalEdge> extraireCheminPattern(HashMap<MusicalEdge, ArrayList<MusicalEdge>> h, ArrayList<String> pattern){
				ArrayList<MusicalEdge> result = new ArrayList<>(); //aretes du graphe originale
				ArrayList<MusicalEdge> result2 = new ArrayList<>(); //aretes du graphe avec des aretes ajoutes par fonc internes

				//extraire chemin
				MusicalEdge edgeIter = this.dst.getBf_predArrete(); 
				while(edgeIter != null){
					if (((MusicalVertex)jungGraphe.getDest(edgeIter)) != dst 
							&& ((MusicalVertex)jungGraphe.getSource(edgeIter)) != src){
						result2.add(edgeIter);
						if(edgeIter.getLabel().getText().equals("P")){
							for(MusicalEdge edge: h.get(edgeIter) ){
								result.add(edge);
								edge.getEdgeLine().setStroke(Color.BLUEVIOLET);
								edge.getEdgeLine().setStrokeWidth(4.);
								edge.getLabel().setTextFill(Color.BLUEVIOLET);
								edge.getLabel().setVisible(true);
							}
						}else{
						result.add(edgeIter);
						edgeIter.getEdgeLine().setStroke(Color.BLUEVIOLET);
						edgeIter.getEdgeLine().setStrokeWidth(4.);
						edgeIter.getLabel().setTextFill(Color.BLUEVIOLET);
						edgeIter.getLabel().setVisible(true);
						}
					}
					edgeIter = ((MusicalVertex) jungGraphe.getSource(edgeIter)).getBf_predArrete();
				}
				
				//interpreter
				int ttlPoids = 0;
				int ttlReste = 0;
				if(result2.size() >= (this.nbCol-1)/pattern.size()){
				for(int i = 0; i < (this.nbCol-1)/pattern.size(); i++){
					System.out.println("*weight=" + result2.get(0).getWeight());
					System.out.println("*arete=" + result2.get(0).getLabel().getText());
					ttlPoids += result2.get(0).getWeight() * pattern.size();
					result2.remove(0);
				}
				
				//if(result2.size() > this.nbCol/pattern.size()){
				int j = 0;
				//for(int i = (this.nbCol-1)/pattern.size(); i < result2.size(); i++){
				for(MusicalEdge r: result2){
					if (r.getLabel().getText().equals(pattern.get(j))){
						ttlReste++;
						System.out.println("yo found one");
					}else{
						ttlReste += 2;
					}
					j=(j+1)%pattern.size();
				}
				}
				
				System.out.println("total poids= " + ttlPoids);
				System.out.println("total reste= " + ttlReste);

				if (ttlPoids + ttlReste == nbCol - 1){
					return result;
				}else{
					return new ArrayList<MusicalEdge>();
				}
				//interpreter
				/*for (MusicalEdge e: result2){
					ttlPoids += e.getWeight();
				}
				
				if (ttlPoids == (nbCol - 1)/pattern.size())
					return result;
				else if (((nbCol - 1)/pattern.size() + (2* ((nbCol - 1)%pattern.size()))) >=
							ttlPoids)
					//TODO: sortout ending of pattern
					return result; 
				else
					return new ArrayList<MusicalEdge>();*/
		 }
		 public ArrayList<MusicalEdge> chercherCheminAvecEtiquette(String etiquette){
			 this.setWeight4Music(etiquette);
			 this.grapheComplet();
			 this.addSrcDest();
			 this.bellmanFord();
			 return this.extraireCheminDeBellmanFord();
		 }
		 
		 
		 public ArrayList<MusicalEdge> chercherCheminAvecPattern(ArrayList<String> pattern){
			 this.setWeightTo2();
			 this.addSrcDest();
			 HashMap<MusicalEdge, ArrayList<MusicalEdge>> h = new HashMap<>();
			 ArrayList<MusicalEdge> arr = new ArrayList<MusicalEdge>();
			 this.ajouterAretePourPattern(src, 0, pattern, null, null, h, arr);
			 this.grapheComplet();
			 this.bellmanFord();
			 return this.extraireCheminPattern(h, pattern);
		 }
	//getters and setters--------------------------------------------------------------------------------------------------
	 public int getNbLigne(){
		 return this.nbLigne;
	 }
	 public void setNbLigne(int nb){
		 this.nbLigne = nb;
	 }
	 public int getNbcol(){
		 return this.nbCol;
	 }
	 
	 public void setNbcol(int nb){
		 this.nbCol = nb;
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
