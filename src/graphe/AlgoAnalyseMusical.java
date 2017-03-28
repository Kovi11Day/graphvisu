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
	}
	
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
	public HashMap<MusicalVertex, Couple>  bellmanFord(MusicalVertex src){
		//use id or instance of obj??
		HashMap<MusicalVertex, Couple> table = new HashMap<>();
		//initialisation du tableau avec distance = infini et pred = null
		for(Object v: jungGraph.getVertices())
			table.put((MusicalVertex)v, new Couple(Double.MAX_VALUE, null));
		
		//initiaisation de la src
		table.put(src, new Couple(0.0, null));
		
		MusicalEdge me;
		//boucle principale sur le nb de sommets
		for (int i = 1; i < table.size(); i++){
			for (Object e: jungGraph.getEdges()){
				me = (MusicalEdge)e;
				Couple u = table.get(jungGraph.getSource(me));
				Couple q = table.get(jungGraph.getDest(me));

				if (u.distance + me.getWeight() < v.distance){
					v.distance = u.distance + me.getWeight();
					v.predArrete = me;
					//v.pred = (MusicalVertex) jungGraph.getSource(me);
				}
			}
		}
		
		//no need to check for negative cycle since weight cannot be negative
		System.out.println("bellman-ford table: " + table.toString());
		return table;
	}
}
