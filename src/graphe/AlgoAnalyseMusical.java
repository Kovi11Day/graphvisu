package graphe;

import edu.uci.ics.jung.graph.Graph;
import graphvisunits.VisuEdge;
import graphvisunits.VisuVertex;

import java.util.ArrayList;
import java.util.HashMap;

public class AlgoAnalyseMusical {
	private MusicalGraph musicalGraph;
	private Graph<VisuVertex, VisuEdge> jungGraph;
	public AlgoAnalyseMusical (MusicalGraph musicalGraph){
		this.musicalGraph = musicalGraph;
		this.jungGraph = musicalGraph.getJungGraphe();
	}
	class Couple{
		Double distance;
		//MusicalVertex pred;
		MusicalEdge predArrete;
	public  Couple(Double distance, MusicalEdge predArrete){
		this.distance = distance;
		this.predArrete = predArrete;
	}
	public void setDistance(double distance){this.distance=distance;}
	public void setPredArrete(MusicalEdge predArrete){this.predArrete=predArrete;}
	public String toString(){
		super.toString();
		return "distance="+distance+" pred="+predArrete+"\n";
	}
	}
	
	/*allPaths est le resultat de obtenue apres la fonction de bellmanFord, 
	 * la fonction calculatePath permet d'extraire un chemin de la source precisé lors de 
	 * l'appel a la fonction de bellmanFord vers le sommet dest*/
	public ArrayList<MusicalEdge> calculatePath (MusicalVertex dest, HashMap<MusicalVertex, Couple> allPaths){
		ArrayList<MusicalEdge> result = new ArrayList<>();
		MusicalEdge edgeIter = allPaths.get(dest).predArrete;
		if (edgeIter == null){
			System.out.println("calculatePath1= " );
			return result;
		}
		while(edgeIter != null){
			result.add(edgeIter);
			edgeIter = allPaths.get((VisuVertex) jungGraph.getSource(edgeIter)).predArrete;
		}
		System.out.println("calculatePath= " + result);
		return result;
	}
	public HashMap<MusicalVertex, Couple> bellmanFord(MusicalVertex src){
		//use id or instance of obj??
		HashMap<MusicalVertex, Couple> table = new HashMap<>();
		//initialisation du tableau avec distance = infini et pred = null
		for(Object v: jungGraph.getVertices())
			table.put((MusicalVertex)v, new Couple(/*Double.MAX_VALUE*/100.0, null));
		
		//initiaisation de la src
		//table.put(src, new Couple(0.0, null));
		Couple source = table.get(src);
		System.out.println("src.distance="+table.get(src).distance);
		source.distance=0.0;
		//source.predArrete=null;
		//System.out.println("src.distance="+source.distance+ " src.pred="+source.predArrete+"\n");
		System.out.println("src.distance="+table.get(src).distance);

		MusicalEdge me;
		//boucle principale sur le nb de sommets
		Couple u; Couple v;
		for (int i = 1; i < table.size(); i++){
			for (Object e: jungGraph.getEdges()){
				 me = (MusicalEdge)e;
				 u = table.get((MusicalVertex)jungGraph.getSource(me));
				 v = table.get((MusicalVertex)jungGraph.getDest(me));
				//System.out.println("u.distance="+u.distance+" me.Weight+"+me.getWeight()+" v.distance"+v.distance+"\n");
				if (u.distance.equals(0.0)){
					System.out.println("yeaaaaaa");
				}
				if (u.distance + me.getWeight() < v.distance){
					System.out.println("u.distance="+u.distance+" me.Weight+"+me.getWeight()+" v.distance"+v.distance+"\n");
					/*table.put((MusicalVertex)jungGraph.getDest(me), 
							new Couple(u.distance + me.getWeight(), me));*/
					v.setDistance(u.distance + me.getWeight());
					v.setPredArrete(me);
					//v.distance = u.distance + me.getWeight();
					//v.predArrete = me;
					//v.pred = (MusicalVertex) jungGraph.getSource(me);
				}
			}
		}
		
		//no need to check for negative cycle since weight cannot be negative
		System.out.println("bellman-ford table: " + table.toString());
		return table;
	}
}
