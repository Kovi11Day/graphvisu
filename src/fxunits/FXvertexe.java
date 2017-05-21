package fxunits;

import graphe.FXCommEvent;

import java.awt.Point;
import java.awt.geom.Point2D;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import clientctx.IClientVertexContext;

public class FXvertexe extends Parent implements IClientVertexContext{
	private IClientVertexContext vertexCtx; 
	
	protected Point2D coordinates = new Point(0,0);
	protected Color color = null;
	protected double radius = 10;
	protected Circle node = new Circle(radius);
	protected Text vertexText; 
	//shape
	//size
	//text
	
	public FXvertexe(IClientVertexContext originalVertex){
		this.vertexCtx = originalVertex;
		//node.setRadius(radius);
		node.setFill(Color.CORAL);
		node.setCenterX(coordinates.getX());
		node.setCenterY(coordinates.getY());
		
		//extract vertext text if any
		if (this.vertexCtx.getVertexText() != null){
			this.vertexText = new Text(this.vertexCtx.getVertexText() );
			vertexText.setFill(Color.BLACK);
			vertexText.setFont(new Font(12));
			vertexText.setX(this.node.getCenterX()-5);
			vertexText.setY(this.node.getCenterY()+3);
			
			this.getChildren().add(vertexText);
		}else{
			this.vertexCtx = null;
		}
		
		this.getChildren().add(node);
		
		//Interaction
		this.setOnMouseEntered(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){

			}
		});
		
		this.setOnMouseExited(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){

			}
		});
		
		this.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){
				appuyer(me);
			}
		});
		
		this.setOnMouseReleased(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent me){

			}
		});			
		this.setOnFXCommEvent(new EventHandler<FXCommEvent>(){
			public void handle(FXCommEvent me){
				commHandler(me);
			}
		});
	}
	public void commHandler(FXCommEvent me){
		node.setFill(Color.RED);
		me.consume();
	}
	 public final void setOnFXCommEvent(
		        EventHandler<? super FXCommEvent> value) {
		    this.addEventHandler(FXCommEvent.COMM, value);
		}
	 
	 public IClientVertexContext getVertexCtx(){
		 return this.vertexCtx;
	 }
	public Circle getNodeGraphic(){
		return this.node;
	}
	public Point2D getCoordinates(){
		return this.coordinates;
	}

	public void setCoordinates(Point2D p){
		this.coordinates = p;
		node.setCenterX(coordinates.getX());
		node.setCenterY(coordinates.getY());
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	

	public void setRadius(double r){
		this.radius = r;
	}

public void appuyer(MouseEvent me){
	node.setFill(Color.GREEN);
	FXgraphe parentGraph = (FXgraphe)this.getParent();	
	Event.fireEvent(parentGraph, new FXCommEvent(this));
	me.consume(); 
}

@Override
public int getI() {
	return this.vertexCtx.getI();
}

@Override
public int getJ() {
	// TODO Auto-generated method stub
	return this.vertexCtx.getJ();
}

@Override
public String getVertexText() {
	// TODO Auto-generated method stub
	return this.vertexCtx.getVertexText();
}

@Override
public java.awt.Color getVertexColor() {
	// TODO Auto-generated method stub
	return null;
}

public void refreshVertexTextLables(){ //TODO: refresh edges of whole graph //ATTACH edge ends to vertices
	if (this.vertexText != null){
		vertexText.setX(this.node.getCenterX()-5);
		vertexText.setY(this.node.getCenterY()+3);
	}
}
 

}
