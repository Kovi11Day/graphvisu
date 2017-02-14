package user.client3.structgraphe;

import java.awt.Color;

import clientctx.IClientVertexContext;

public class MyVertexe implements IClientVertexContext{
	private int i;
	private int j;
	private String name;
	//musical note
	//colour
	

	public MyVertexe(int i, int j, String name) {
		this.i = i;
		this.j = j;
		this.name = name;
	}

	
	@Override
	public int getI() {
		// TODO Auto-generated method stub
		return i;
	}
	@Override
	public int getJ() {
		// TODO Auto-generated method stub
		return j;
	}

	@Override
	public String getVertexText() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Color getVertexColor() {
		// TODO Auto-generated method stub
		return null;
	}

}
