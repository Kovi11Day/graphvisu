package converters;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import visualizeGraphe.*;


public class JungGraphToFXGraphConverter<V,E> implements Converter<Graph<V,E>,FXgraphe>{

	//TODO: make layout type flexible
	//TODO: make layout dimensions flexible: given by what? 
	//TODO: consider making accessible to user so they can configure it 
	private Dimension dimension;
	private Layout<V,E> layout;
	private Graph<V,E> g;
	
	public JungGraphToFXGraphConverter(Graph<V,E> g, String layoutType){
		this.g = g;
		String l = new String(layoutType);
		if (l.equals(new String("CircleLayout")))
			layout = new CircleLayout<V,E>(g); 
		else if (l.equals(new String("FRLayout")))
			layout = new FRLayout<V,E>(g); 
		else if (l.equals(new String("SpringLayout")))
			layout = new SpringLayout<V,E>(g); 
	}
	
	
	public FXgraphe convert(int width, int height){
		this.dimension = new Dimension(width, height);
		
		layout.setSize(this.dimension);
    	
    	Collection<V> vertices = layout.getGraph().getVertices(); 
    	Collection<E> edges = layout.getGraph().getEdges(); 
    	
    	
    	Collection<IVertexe> fxVertices = new ArrayList<IVertexe>();
    	Collection<Edge> fxEdges = new ArrayList<Edge>();
    	
    	//preserve instances to allow FXvertex and vertices in FXedge to be of the same instance
    	Map<V,IVertexe> assos = new HashMap<V,IVertexe>();
    	
    	for(V v: vertices){
    		IVertexe fxVertex = new Vertexe(layout.transform(v));
    		fxVertex.setRadius(calcRadius(this.dimension.getWidth(),this.g.getVertexCount()));
    		fxVertices.add(fxVertex);
    		assos.put(v, fxVertex); //useful for edges
    	}	
    	
    	for(E e: edges){
    		Pair<V> endPoints = g.getEndpoints(e);
    		fxEdges.add(new Edge(
    				assos.get(endPoints.getFirst()),
    				assos.get(endPoints.getSecond())));
    	}
    	
		return new FXgraphe(fxVertices, fxEdges);
	}

	@Override
	public FXgraphe convertExtra(int width, int height) {
		this.dimension = new Dimension(width, height);
		
		layout.setSize(dimension);
    	
    	Collection<V> vertices = layout.getGraph().getVertices(); 
    	Collection<E> edges = layout.getGraph().getEdges(); 
    	
    	
    	Collection<IVertexe> fxVertices = new ArrayList<IVertexe>();
    	Collection<Edge> fxEdges = new ArrayList<Edge>();
    	
    	//preserve instances to allow FXvertex and vertices in FXedge to be of the same instance
    	Map<V,IVertexe> assos = new HashMap<V,IVertexe>();
    	
    	for(V v: vertices){
    		IVertexExtra ve = (IVertexExtra) v;
    		IVertexe fxVertex = new VertexeExtra(layout.transform(v), ve);
    		fxVertices.add(fxVertex);
    		assos.put(v, fxVertex); //useful for edges
    	}	
    	
    	for(E e: edges){
    		Pair<V> endPoints = g.getEndpoints(e);
    		fxEdges.add(new Edge(
    				assos.get(endPoints.getFirst()),
    				assos.get(endPoints.getSecond())));
    	}
    	
    	
    	
		return new FXgraphe(fxVertices, fxEdges);	}

		
	public static double calcRadius(double width, int numNodes){
		return width / (10 * numNodes);
	}
	
	

}
