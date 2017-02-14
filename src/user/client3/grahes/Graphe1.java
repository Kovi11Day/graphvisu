package user.client3.grahes;

import java.util.ArrayList;

import user.client3.structgraphe.MyEdge;
import user.client3.structgraphe.MyGraphe;
import user.client3.structgraphe.MyVertexe;

public class Graphe1 {
	
	private MyGraphe graphe;

	public Graphe1(){
		this.graphe=new MyGraphe();
		MyVertexe v11; MyVertexe v12;MyVertexe v13; MyVertexe v10;
		MyVertexe v21; MyVertexe v22;MyVertexe v23; MyVertexe v20;
		
		MyVertexe v31; MyVertexe v32;MyVertexe v33; MyVertexe v30;
		MyVertexe v01; MyVertexe v02;MyVertexe v03; MyVertexe v00;
		
		this.graphe.addVertex(v11 = new MyVertexe(1,1, "node11"));
		this.graphe.addVertex(v12 = new MyVertexe(1,2, "node12"));
		this.graphe.addVertex(v13 = new MyVertexe(1,3, "node13"));
		this.graphe.addVertex(v10 = new MyVertexe(1,0, "node14"));

		this.graphe.addVertex(v21 = new MyVertexe(2,1, "node21"));
		this.graphe.addVertex(v22 = new MyVertexe(2,2, "node22"));
		this.graphe.addVertex(v23 =new MyVertexe(2,3, "node23"));
		this.graphe.addVertex(v20 =new MyVertexe(2,0, "node24"));
		
		this.graphe.addVertex(v31 = new MyVertexe(3,1, "node31"));
		this.graphe.addVertex(v32 = new MyVertexe(3,2, "node32"));
		this.graphe.addVertex(v33 = new MyVertexe(3,3, "node33"));
		this.graphe.addVertex(v30 = new MyVertexe(3,0, "node34"));

		this.graphe.addVertex(v01 = new MyVertexe(0,1, "node01"));
		this.graphe.addVertex(v02 = new MyVertexe(0,2, "node02"));
		this.graphe.addVertex(v03 =new MyVertexe(0,3, "node03"));
		this.graphe.addVertex(v00 =new MyVertexe(0,0, "node04"));
		
		this.graphe.addEdge(new MyEdge(v03,v13, "03-13"));
		this.graphe.addEdge(new MyEdge(v02,v12, "02-13"));
		this.graphe.addEdge(new MyEdge(v01,v12, "01-12"));
		this.graphe.addEdge(new MyEdge(v01,v11, "01-11"));
		this.graphe.addEdge(new MyEdge(v01,v10, "01-10"));
		this.graphe.addEdge(new MyEdge(v00,v11, "00-11"));
		
		this.graphe.addEdge(new MyEdge(v13,v32, "13-22"));
		this.graphe.addEdge(new MyEdge(v12,v21, "12-21"));
		this.graphe.addEdge(new MyEdge(v11,v22, "11-22"));
		this.graphe.addEdge(new MyEdge(v10,v20, "01-20"));

		this.graphe.addEdge(new MyEdge(v22,v33, "22-33"));
		this.graphe.addEdge(new MyEdge(v22,v31, "22-31"));
		this.graphe.addEdge(new MyEdge(v21,v32, "21-32"));
		this.graphe.addEdge(new MyEdge(v20,v31, "20-31"));
		this.graphe.addEdge(new MyEdge(v20,v30, "20-30"));


		System.out.println("client graph: " + this.graphe.getVertices().size() + "vertices and " + this.graphe.getEdges().size() + " edges");
	}
/*
	public Graphe1(){
		 ArrayList<MyEdge> edges = new ArrayList<>();
		 ArrayList<MyVertexe> vertices = new ArrayList<>();
		
		for (int i = 0; i < 3; ++i){ //nb colonnes
			for (int j = 0; i < 5; ++j){ //nb lignes
				vertices.add(new MyVertexe(i,j, new String("node"+i+"-"+j)));
			}
		}
		
		System.out.println("my graph with " + vertices.size() + "vertices");//TOREM
		
		edges.add(new MyEdge(vertices.get(0),vertices.get(4),"edge11-21"));
		edges.add(new MyEdge(vertices.get(1),vertices.get(5),"edge12-22"));
		edges.add(new MyEdge(vertices.get(2),vertices.get(6),"edge13-23"));
		edges.add(new MyEdge(vertices.get(3),vertices.get(7),"edge14-24"));

		System.out.println("my graph with " + edges.size() + "vertices");//TOREM

		
		
		this.graphe.addVertex(new MyVertexe(1,1, "node11"));
		this.graphe.addVertex(new MyVertexe(1,2, "node12"));
		this.graphe.addVertex(new MyVertexe(1,3, "node13"));
		this.graphe.addVertex(new MyVertexe(1,4, "node14"));

		this.graphe.addVertex(new MyVertexe(2,1, "node21"));
		this.graphe.addVertex(new MyVertexe(2,2, "node22"));
		this.graphe.addVertex(new MyVertexe(2,3, "node23"));
		this.graphe.addVertex(new MyVertexe(2,4, "node24"));
		
		this.graphe.addEdge(new MyEdge());
		
		this.graphe =  new MyGraphe(vertices,edges);
	}*/

	public ArrayList<MyVertexe> getVertices(){
		return this.graphe.getVertices();
	}
	public ArrayList<MyEdge> getEdges(){
		return this.graphe.getEdges();
	}
	
	
}
