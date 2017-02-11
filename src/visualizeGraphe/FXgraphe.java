package visualizeGraphe;

import java.util.ArrayList;
import java.util.Collection;

import javafx.scene.Parent;

public class FXgraphe extends Parent{
	//have 2 interfaces:
	//one to be used for construction //behind the scenes
	//one to be used by final user JavaFX manipulations
	
	private Collection<IVertexe> vertices;
	private Collection<Edge> edges;
	
	public FXgraphe(Collection<IVertexe> vertices, Collection<Edge> edges){
		this.vertices = vertices;
		this.edges = edges;
		
		for (IVertexe v: vertices){
			this.getChildren().add((Vertexe)v);
		}
		for (Edge e: edges){
			this.getChildren().add(e);
		}
	}
	//constructor should add all items to itself
	
	/*
	public void addVertex(Vertexe v){
		vertices.add(v);
		//update by adding item to itself
	}
	
	public void addEdge(Edge e){
		edges.add(e);
		//update by adding item to itself
	}
	*/
}
