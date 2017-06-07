package graphe2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import graphvisunits2.VisuEdge;
import graphvisunits2.VisuVertex;

public class BuildGraph {
	public static String randomEtiquette(){
		Random rand = new Random();
		int pick = rand.nextInt(2)+1;//+1;
		switch (pick){
		case 1: return "T1";//Etiquettes.T1;
		case 2: return "T2";//Etiquettes.T2;
		/*case 3: return "T3";//Etiquettes.T3;
		case 4: return "T4";//Etiquettes.T4;
		case 5: return "T5";//Etiquettes.T5;
		case 6: return "T6";//Etiquettes.T1;
		case 7: return "T7";//Etiquettes.T2;
		case 8: return "T8";//Etiquettes.T3;
		case 9: return "T9";//Etiquettes.T4;
		case 10: return "T10";//Etiquettes.T5;
		case 11: return "T11";//Etiquettes.T5;
		case 12: return "T0";//Etiquettes.T5;
*/
		default: return "T1";//Etiquettes.T5;
		}
	}
	
	public static Graph<VisuVertex,VisuEdge> buildRandomMusicalGraph (int nbRows, int nbCol){
		Graph<VisuVertex,VisuEdge> graph = new DirectedSparseMultigraph<>();

		for (int i = 0; i < nbRows; i++){
			for (int j = 0 ; j < nbCol; j ++){
				graph.addVertex(new MusicalVertex(i, j));
			}
		}
		ArrayList<VisuVertex> vertices = new ArrayList<>();
		Collection<VisuVertex> colvertices = graph.getVertices();
		vertices.addAll(colvertices);
		//generating random musical graph
		Random rand = new Random();
		int nbEdgesInCol; 
		VisuVertex start = null; VisuVertex end = null;
		int find_i1 ;
		int find_i2;
		//int total = 0;
		for (int i = 0 ; i < nbCol - 1; i++){ //i rep col num here
			nbEdgesInCol = rand.nextInt(100); 
			//total += nbEdgesInCol;
			for (int j = 0; j < nbEdgesInCol; j++){//nb arretes a creer dans une colonne
				find_i1 = rand.nextInt(nbRows);
				find_i2 = rand.nextInt(nbRows);
				assert(vertices.size() > 0);
				for(VisuVertex vi: vertices){
					MusicalVertex v = (MusicalVertex)vi;
					if(v.getI() == find_i1 && v.getJ() == i)
						start = v;
					if(v.getI() == find_i2 && v.getJ() == i+1)
						end = v;
				}
				if(start != null && end != null){
					MusicalEdge edge = (new MusicalEdge(start, end,BuildGraph.randomEtiquette()));
					graph.addEdge(edge, start, end);
				}
			}
		}
		return graph;
	}
}
