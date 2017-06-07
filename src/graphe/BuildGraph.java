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
		int pick = rand.nextInt(12);//+1;
		switch (pick){
		case 1: return "T1";//Etiquettes.T1;
		case 2: return "T2";//Etiquettes.T2;
		case 3: return "T3";//Etiquettes.T3;
		case 4: return "T4";//Etiquettes.T4;
		case 5: return "T5";//Etiquettes.T5;
		case 6: return "T6";//Etiquettes.T1;
		case 7: return "T7";//Etiquettes.T2;
		case 8: return "T8";//Etiquettes.T3;
		case 9: return "T9";//Etiquettes.T4;
		case 10: return "T10";//Etiquettes.T5;
		case 11: return "T11";//Etiquettes.T5;
		case 12: return "T0";//Etiquettes.T5;

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
	
	public static VisuVertex getVertex(Graph<VisuVertex,VisuEdge> graph, double i, double j ){
		MusicalVertex m;
		for (VisuVertex v: graph.getVertices()){
			m = (MusicalVertex)v;
			if (m.getI() == i && m.getJ() == j){
				return m;
			}
		}
		throw new RuntimeException("getVertex: vertex not in graph");
	}

	public static Graph<VisuVertex,VisuEdge> buildRandomMusicalGraph2 (){
		int nbRows = 5;
		int nbCol = 5;
		double uniqueID = 0; 

		Graph<VisuVertex,VisuEdge> g = new DirectedSparseMultigraph<>();
		
		//add vertices
		for (int i = 0; i < nbRows; i++){
			for (int j = 0 ; j < nbCol; j ++){
				g.addVertex(new MusicalVertex(uniqueID, i, j));
			}
			uniqueID++;
		}
		
		//add edges
		g.addEdge((new MusicalEdge(getVertex(g,0,0), getVertex(g,1,1),"T2")),getVertex(g,0,0), getVertex(g,1,1));
		g.addEdge((new MusicalEdge(getVertex(g,0,1), getVertex(g,1,1),"T1")),getVertex(g,0,1), getVertex(g,1,1));
		g.addEdge((new MusicalEdge(getVertex(g,0,2), getVertex(g,1,2),"T1")),getVertex(g,0,2), getVertex(g,1,2));
		g.addEdge((new MusicalEdge(getVertex(g,0,3), getVertex(g,1,2),"T4")),getVertex(g,0,3), getVertex(g,1,2));
		g.addEdge((new MusicalEdge(getVertex(g,0,4), getVertex(g,1,4),"T5")),getVertex(g,0,4), getVertex(g,1,4));

		
		g.addEdge((new MusicalEdge(getVertex(g,1,2), getVertex(g,2,0),"T1")),getVertex(g,1,2), getVertex(g,2,0));
		g.addEdge((new MusicalEdge(getVertex(g,1,2), getVertex(g,2,1),"T2")),getVertex(g,1,2), getVertex(g,2,1));
		g.addEdge((new MusicalEdge(getVertex(g,1,3), getVertex(g,2,3),"T3")),getVertex(g,1,3), getVertex(g,2,3));
		g.addEdge((new MusicalEdge(getVertex(g,1,3), getVertex(g,2,4),"T2")),getVertex(g,1,3), getVertex(g,2,4));

		/*
		g.addEdge((new MusicalEdge(getVertex(g,2,0), getVertex(g,3,3),"T1")),getVertex(g,2,0), getVertex(g,3,3));
		g.addEdge((new MusicalEdge(getVertex(g,2,1), getVertex(g,3,0),"T4")),getVertex(g,2,1), getVertex(g,3,0));
		g.addEdge((new MusicalEdge(getVertex(g,2,4), getVertex(g,3,3),"T1")),getVertex(g,2,4), getVertex(g,3,3));
		g.addEdge((new MusicalEdge(getVertex(g,2,4), getVertex(g,3,4),"T6")),getVertex(g,2,4), getVertex(g,3,4));
*/
		
		g.addEdge((new MusicalEdge(getVertex(g,3,0), getVertex(g,4,1),"T11")),getVertex(g,3,0), getVertex(g,4,1));
		g.addEdge((new MusicalEdge(getVertex(g,3,3), getVertex(g,4,0),"T10")),getVertex(g,3,3), getVertex(g,4,0));
		g.addEdge((new MusicalEdge(getVertex(g,3,3), getVertex(g,4,2),"T8")),getVertex(g,3,3), getVertex(g,4,2));
		g.addEdge((new MusicalEdge(getVertex(g,3,3), getVertex(g,4,3),"T1")),getVertex(g,3,3), getVertex(g,4,3));
		g.addEdge((new MusicalEdge(getVertex(g,3,4), getVertex(g,4,4),"T7")),getVertex(g,3,4), getVertex(g,4,4));
		 
		return g;
	}
	

	public static Graph<VisuVertex,VisuEdge> buildRandomMusicalGraph (){

		int nbRows = 10;
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
			nbEdgesInCol = rand.nextInt(10); 
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