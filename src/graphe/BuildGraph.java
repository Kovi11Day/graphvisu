package graphe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import graphvisunits.VisuEdge;
import graphvisunits.VisuVertex;

public class BuildGraph {

	public static Graph<VisuVertex, VisuEdge> grapheComplet(
			Graph<VisuVertex, VisuEdge> jungGraph,
			int number_of_vertices_per_column) {
		Collection<VisuVertex> fv = jungGraph.getVertices();
		Collection<VisuEdge> fe = jungGraph.getEdges();
		VisuVertex[] fvt = new VisuVertex[fv.size()];
		VisuEdge[] fet = new VisuEdge[fe.size()];
		fv.toArray(fvt);
		fe.toArray(fet);

		/* NEED NUMBER OF VERTICES PER COLUMN */
		int colonne = fvt.length / number_of_vertices_per_column;

		/* pour chaque colonne */
		for (int i = 0; i < colonne; i++) {
			HashSet<VisuVertex> x = new HashSet<VisuVertex>();

			for (int j = 0; j < fvt.length; j++) {
				if (fvt[j].getI() == i && fvt[j].getOut() == 0) {
					x.add(fvt[j]);
				}
			}
			if (x.size() == number_of_vertices_per_column && (i + 1) < colonne) {
				HashSet<VisuVertex> voisins = getVerticesColumn(i + 1, fvt);
				VisuVertex[] array = new VisuVertex[voisins.size()];
				voisins.toArray(array);
				for (int o = 0; o < fvt.length; o++) {
					for (int p = 0; p < array.length; p++) {
						if (fvt[o].getI() == i){
							/*****modif_kovila:ajouter_poids****/
							//poids = 1??
							MusicalEdge addedEdge =new MusicalEdge(fvt[o], array[p]);
							addedEdge.setWeight(1);
							jungGraph.addEdge(addedEdge, fvt[o],array[p]);
							/*jungGraph.addEdge(
									new MusicalEdge(fvt[o], array[p]), fvt[o],
									array[p]);*/
						}
					}
				}
			}
		}

		return jungGraph;
	}
	public static String randomEtiquette(){
		Random rand = new Random();
		int pick = rand.nextInt(2)+1;
		switch (pick){
		case 1: return "T1";//Etiquettes.T1;
		case 2: return "T2";//Etiquettes.T2;
		//case 3: return "T3";//Etiquettes.T3;
		//case 4: return "T4";//Etiquettes.T4;
		//case 5: return "T5";//Etiquettes.T5;
		default: return "T1";//Etiquettes.T5;
		}
	}
	public static HashSet<VisuVertex> getVerticesColumn(int i, VisuVertex[] f) {
		HashSet<VisuVertex> h = new HashSet<VisuVertex>();
		for (int k = 0; k < f.length; k++) {
			if (f[k].getI() == (i))
				h.add(f[k]);
		}
		return h;
	}


	public static Graph<VisuVertex,VisuEdge> buildRandomMusicalGraph (){

		int nbRows = 5;
		int nbCol = 5;
		Graph<VisuVertex,VisuEdge> graph = new DirectedSparseMultigraph<>();

		double uniqueID = 0; 
		for (int i = 0; i < nbRows; i++){
			for (int j = 0 ; j < nbCol; j ++){
				//System.out.println("i=" + i + "j="  +j);
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
		int weight;
		int total = 0;
		for (int i = 0 ; i < nbCol - 1; i++){ //i rep col num here
			nbEdgesInCol = rand.nextInt(50); 
			total += nbEdgesInCol;
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
				/* sarra debut*/
				// probleme lors du parcour , il reparcour la meme areter
				//et ecrit la valeur du label par decu l'ancienne
				if(start != null && end != null){
					//probleme uniqueId n'est pas unique
					//je pensai que uniqId etait le nom unique du sommet
					// s= nom du label donne a l'arete
					String s = ""+start.getUniqueID()+end.getUniqueID();
					//MusicalEdge edge = (new MusicalEdge(start, end,s));
					MusicalEdge edge = (new MusicalEdge(start, end,BuildGraph.randomEtiquette()));
					
					//weight = rand.nextInt(10);
					//edge.setWeight(weight);
					//edge.getLabel().setText(String.valueOf(0));
					graph.addEdge(edge, start, end);
				}
				/*fin sarra*/

				/*if(start != null && end != null){
					MusicalEdge edge = (new MusicalEdge(start, end));
					weight = rand.nextInt(10)
							;
					edge.setWeight(weight);
					edge.getLabel().setText(String.valueOf(weight));
					graph.addEdge(edge, start, end);
				}*/
				//graph.addEdge(new VisuEdge(vertices, end), start, end);

			}
		}
		System.out.println("nb edges=" + total);
		return graph;
	}
}
