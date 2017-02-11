package visualizeGraphe;

import java.awt.Insets;
import java.awt.geom.Point2D;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VertexeExtra extends Vertexe implements IVertexExtra{

	private IVertexExtra originalVertexe; //type V implements IVertexExtra
	private String vertexText;
	
	public VertexeExtra(Point2D coordinates, IVertexExtra originalVertexe){
		//super();
		//this.getChildren().add((Node) super.getChildren());
		super(coordinates);
		
		this.originalVertexe = originalVertexe;
		this.vertexText = originalVertexe.getVertexText();
		
		if (this.vertexText != null){
			Text name = new Text(this.vertexText);
			name.setFill(Color.BLACK);
			name.setFont(new Font(12));
			name.setX(v.getCenterX()-5);
			name.setY(v.getCenterY()+3);
			
			this.getChildren().add(name);
		}
		

	}
	
	public VertexeExtra(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String getVertexText() {
		return this.originalVertexe.getVertexText();
	}
	@Override
	public java.awt.Color getVertexColor() {
		return this.originalVertexe.getVertexColor();
	}
	
	

}
