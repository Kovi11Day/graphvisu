package user.client3.structgraphe;

import clientctx.IClientEdgeContext;
import clientctx.IClientVertexContext;
import fxunits.FXvertexe;

public class MyEdge implements IClientEdgeContext{
	private MyVertexe v1;
	private MyVertexe v2;
	private String name;
	
	
	public MyEdge(MyVertexe v1, MyVertexe v2, String name) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.name = name;
	}

	@Override
	public IClientVertexContext getVertex1() {
		// TODO Auto-generated method stub
		return v1;
	}

	@Override
	public IClientVertexContext getVertex2() {
		// TODO Auto-generated method stub
		return v2;
	}

	@Override
	public String getEdgeText() {
		// TODO Auto-generated method stub
		return name;
	}

}
