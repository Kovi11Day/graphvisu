package user.struct2;

//import graphe.vertex.interfaces.Ivertex;

public class VertexGraphe{
	private double x;
	private double y;
	private String name;
	
	//constructeur1 vide: si on connait pas les coordonnees et ne veut pas mettre de string;
	public VertexGraphe (){}
	
	//constructeur2: si on connait pas les coordonnees
	public VertexGraphe(String name){
		this.name = name;
	}
	//constructeur3
	public VertexGraphe(double x, double y,String name ){
		this.x= x;
		this.y= y;
		this.name= name;
	}

	public double getX(){
		return x;
		
	}
	public double getY(){
		return y;
	}

	public String getName() {
		return name;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
