package visualizeGraphe;

import java.awt.geom.Point2D;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Vertexe extends Parent implements IVertexe{
	protected Point2D coordinates;
	protected Color color;
	//private V originalVertex; //necessary?
	protected double radius = 10;
	protected Circle v = new Circle(radius);
	//shape
	//size
	//text
	
	public Vertexe(Point2D coordinates){
		this.coordinates = coordinates;
		v.setRadius(radius);
		v.setCenterX(coordinates.getX());
		v.setCenterY(coordinates.getY());
		v.setFill(Color.CORAL);
		this.getChildren().add(v);
		//Interaction
		this.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				v.setFill(Color.RED);
			}
		});
		
		this.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				v.setFill(Color.CORAL);
			}
		});
		
		this.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				appuyer();
			}
		});
		
		this.setOnMouseReleased(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				relacher();
			}
		});
	}
	

	
	public Vertexe(double x,double y ){
		Circle sun = new Circle(15);
        sun.setCenterX(x);
        sun.setCenterY(y);
        sun.setFill(Color.CORAL);
        this.getChildren().add(sun);
        
	}

	public Point2D getCoordinates(){
		return this.coordinates;
	}

	public void setCoordinates(Point2D p){
		this.coordinates = p;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	

	public void setRadius(double r){
		this.radius = r;
		//this.getChildren().get(0).resize(radius, radius);
	}

public void appuyer(){
	v.setFill(Color.GREEN);
	
}

public void relacher(){
	v.setFill(Color.CORAL);
}


}
