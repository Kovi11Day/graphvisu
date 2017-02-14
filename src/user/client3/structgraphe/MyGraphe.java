package user.client3.structgraphe;

import java.util.ArrayList;

public class MyGraphe {

	private ArrayList<MyEdge> edges;
	private ArrayList<MyVertexe> vertices;

	public MyGraphe (){
		this.edges = new ArrayList<MyEdge>();
		this.vertices = new ArrayList<MyVertexe>();
	}
	
	public MyGraphe(ArrayList<MyVertexe> vertices, ArrayList<MyEdge> edges){
		this.vertices=vertices;
		this.edges=edges;
	}
	
	public void addVertex(MyVertexe v){
		this.vertices.add(v);
	}
	
	public void addEdge(MyEdge e){
		this.edges.add(e);
	}
	
	public ArrayList<MyVertexe> getVertices(){
		return this.vertices;
	}
	
	public ArrayList<MyEdge> getEdges(){
		return this.edges;
	}
}
