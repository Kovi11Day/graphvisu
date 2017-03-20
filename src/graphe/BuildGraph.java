package graphe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import graphvisunits.VisuEdge;
import graphvisunits.VisuVertex;

public class BuildGraph {
	
	public static Graph<VisuVertex,VisuEdge> buildRandomMusicalGraph (){

		int nbRows = 5;
		int nbCol = 5;
		Graph<VisuVertex,VisuEdge> graph = new SparseMultigraph<>();

		double uniqueID = 0; 
		for (int i = 0; i < nbRows; i++){
			for (int j = 0 ; j < nbCol; j ++){
				System.out.println("i=" + i + "j="  +j);
				graph.addVertex(new MusicalVertex(uniqueID, i, j));
			}
			uniqueID++;
		}
		//System.out.println("built graph");
		ArrayList<VisuVertex> vertices = new ArrayList<>();
		Collection<VisuVertex> colvertices = graph.getVertices();
		vertices.addAll(colvertices);
		//generating random musical graph
		Random rand = new Random();
		int nbEdgesInCol = rand.nextInt(50); 
		VisuVertex start = null; VisuVertex end = null;
		int find_i1 ;
		int find_i2;
		for (int i = 0 ; i < nbCol - 1; i++){ //i rep col num here
			nbEdgesInCol = rand.nextInt(50); 
			for (int j = 0; j < nbEdgesInCol; j++){//nb arretes a creer dans une colonne
				find_i1 = rand.nextInt(nbRows);
				find_i2 = rand.nextInt(nbRows);
				assert(vertices.size() > 0);
				for(VisuVertex v: vertices){
					if(v.getJ() == find_i1 && v.getI() == i)
						start = v;
					if(v.getJ() == find_i2 && v.getI() == i+1)
						end = v;
				}
				if(start != null && end != null)
					graph.addEdge(new MusicalEdge(start, end), start, end);
				//graph.addEdge(new VisuEdge(vertices, end), start, end);

			}
		}
		return graph;
	}
}
