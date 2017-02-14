package converters;

import java.util.Collection;

public class GenericGraph<IVertex,IEdge> {
	
	private Collection<IVertex> vertices;
	private Collection<IEdge> edges;
	
	public GenericGraph(Collection<IVertex> vertices, Collection<IEdge> edges){
		this.vertices = vertices;
		this.edges = edges;
	}
	
	public Collection<IVertex> getVertices(){ return vertices;}
	
	public Collection<IEdge> getEdges(){ return edges;}
	
	
}
