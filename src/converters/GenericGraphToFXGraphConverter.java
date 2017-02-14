package converters;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import musicalLayout.GridLayout;
import clientctx.IClientEdgeContext;
import clientctx.IClientVertexContext;
import fxunits.FXgraphe;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import fxunits.FXedge;
import fxunits.FXvertexe;

public class GenericGraphToFXGraphConverter<V,E> implements Converter<Graph<V,E>,FXgraphe> {
	private Dimension dimension;
	private Layout<FXvertexe,FXedge> layout; //is storing this necessary?
	private Collection<V> vertices;
	private Collection<E> edges;
	private Graph<FXvertexe,FXedge> jungGraph;//is storing this necessary?
	//TODO cf builder DP
	//TODO parametrize graph type
	public GenericGraphToFXGraphConverter(Collection<V> vertices, Collection<E> edges ){ 
		this.vertices = vertices;
		this.edges = edges;
		this.dimension = null;
		this.layout = null;
		this.jungGraph = null;
	}
	
	@Override
	public FXgraphe convert(int width, int height, String layoutType) { //pass graph type, directed/un edges
		this.dimension = new Dimension(width, height);	
		this.jungGraph = new SparseMultigraph<>();
		
		//TODO: build generic util to solve this problem
    	Map<String,FXvertexe> assos = new HashMap<String,FXvertexe>();//preserver instances

		//fill in jung graph each vertex has no original coorniate
		for(V v: vertices){
			IClientVertexContext vertexCtx = (IClientVertexContext)v; //TODO: throw exception if not the case
			FXvertexe fxv = new FXvertexe(vertexCtx);
			jungGraph.addVertex(fxv);
			assos.put(fxv.getVertexText(), fxv);
		}
		for(E e: edges){
			IClientEdgeContext edgeCtx = (IClientEdgeContext)e; //TODO: throw exception if not the case
			FXedge edge = new FXedge(edgeCtx, assos); //TODO:optimise, do not store all nodes in each edge
			jungGraph.addEdge(edge,  edge.getVertex1(),  edge.getVertex2());
		}
		System.out.println("jung graph created with " + this.jungGraph.getVertexCount() + " nodes and " + this.jungGraph.getEdgeCount() + " edges");

		
		//add coordinate to each vertex using layout
		String l = new String(layoutType);
		if (l.equals(new String("CircleLayout")))
			this.layout = new CircleLayout<FXvertexe,FXedge>(jungGraph); 
		else if (l.equals(new String("FRLayout")))
			this.layout = new FRLayout<FXvertexe,FXedge>(jungGraph); 
		else if (l.equals(new String("SpringLayout")))
			this.layout = new SpringLayout<FXvertexe,FXedge>(jungGraph); 
		else if (l.equals(new String("GridLayout"))){
	    	layout = new GridLayout<FXvertexe,FXedge>(this.jungGraph);
	    	if(this.layout == null){
	    		System.out.println("WARNING: was unable to create GridLayout, created CircleLayout instead");
	    		this.layout = new CircleLayout<FXvertexe,FXedge>(jungGraph);
			}
		}
		else
			this.layout = new CircleLayout<FXvertexe,FXedge>(jungGraph);
		
		layout.setSize(this.dimension);
		System.out.println("layout created");

		for (FXvertexe v: this.jungGraph.getVertices()){
			Point2D p = this.layout.transform(v);
			v.setCoordinates(p);
			v.refreshVertexTextLables();
		}

		for (FXedge e: this.jungGraph.getEdges()){
			e.refreshEdges();
		}
		return new FXgraphe(this.jungGraph);
	}

	@Override
	public FXgraphe convert(int width, int height) {
		return null;
	}

	@Override
	public FXgraphe convertExtra(int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}


}
