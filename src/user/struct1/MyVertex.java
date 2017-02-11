package user.struct1;

import java.awt.Color;

import visualizeGraphe.IVertexExtra;

public class MyVertex implements IVertexExtra{
	private String name;

	public MyVertex(String name){
		this.name = name;
	}
	@Override
	public String getVertexText() {
		return name;
	}

	@Override
	public Color getVertexColor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
