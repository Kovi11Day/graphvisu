package musicalLayout;


import java.awt.Point;
import java.awt.geom.Point2D;

import musicalLayout.interfaces.INotes;
import musicalLayout.interfaces.IVerticesNotes;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import graphvisunits.VisuVertex;



//TODO do not build client graph here
public class GridLayout2<V,E> extends StaticLayout<V, E> {
	
	//version 1 creer un nv graphe et insere  les vertex dans le nv graphe 
	private INotes n ;
	
	public GridLayout2(INotes n) {
		super(new SparseGraph<V,E>());
		this.n = n;
		makeGrapheandLayout();
	}
	//version 2 le graphe existe deja, on modifie juste les coordoné de chaque vertexe
	public GridLayout2(Graph<V,E> g){
		super(g);
		for(V v : g.getVertices()){
			//int i =((IClientVertexContext) v).getI()*100; //TODO: calculer dynamiquement facteur multiplicative
			//int j =((IClientVertexContext) v).getJ()*100;
			double i = ((VisuVertex)v).getI();
			double j = ((VisuVertex)v).getJ(); 
			Point2D p = new Point((int)i, (int)j);
			this.setLocation(v, p);
		}
	}

	@SuppressWarnings("unchecked")
	public void makeGrapheandLayout(){
		int distX = 100; //TODO: calculer dynamiquement
		int distY = 100;
		int nbColumn = n.getPartition().size();
		for(int i = 0; i< nbColumn; i++){
			int nbLigne = n.getPartition().get(i).getK();
			IVerticesNotes notes = n.getPartition().get(i);
			for(int j = 0; j < nbLigne; j++){
				this.getGraph().addVertex((V) notes.getNotes()[j]);
				this.setLocation( (V) notes.getNotes()[j], i*distX, j*distY);
			}
		}
	}
	

}
