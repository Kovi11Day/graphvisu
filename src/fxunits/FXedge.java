package fxunits;

import java.util.Map;

import clientctx.IClientEdgeContext;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class FXedge extends Parent implements IClientEdgeContext{
	IClientEdgeContext edgeCtx; //TODO: clean up edge struct
	private FXvertexe vertex1; //necessary?
	private FXvertexe vertex2; 
	private Line line = new Line();
	
	//TODO: do not store all vertices in each edge
	public FXedge(IClientEdgeContext edgeContext, Map<String,FXvertexe> assos ){
		this.edgeCtx = edgeContext;
		this.vertex1 = assos.get(edgeContext.getVertex1().getVertexText());
		this.vertex2 = assos.get(edgeContext.getVertex2().getVertexText());
		line.setStartX(this.vertex1.getCoordinates().getX());
		line.setStartY(this.vertex1.getCoordinates().getY());
		line.setEndX(vertex2.getCoordinates().getX());
		line.setEndY(vertex2.getCoordinates().getY());
		line.setFill(Color.BLACK);
		this.getChildren().add(line);
		
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
		this.line.setStroke(Color.RED);
		me.consume(); 
	}
	 public final void setOnFXCommEvent(
		        EventHandler<? super FXCommEvent> value) {
		    this.addEventHandler(FXCommEvent.COMM, value);
		}
	 
	 public IClientEdgeContext getEdgeCtx(){
		 return this.edgeCtx;
	 }
	//color
	public FXedge(IClientEdgeContext edgeContext ){
		this.edgeCtx = edgeContext;
		line.setStartX(vertex1.getCoordinates().getX());
		line.setStartY(vertex1.getCoordinates().getY());
		line.setEndX(vertex2.getCoordinates().getX());
		line.setEndY(vertex2.getCoordinates().getY());
		line.setFill(Color.BLACK);
		line.setStrokeWidth(10.0);
		this.getChildren().add(line);
	}
	public FXvertexe getVertex1(){return vertex1;}
	public FXvertexe getVertex2(){return vertex1;}

	public void refreshEdges(){ //TODO: refresh edges of whole graph //ATTACH edge ends to vertices
		line.setStartX(this.vertex1.getCoordinates().getX());
		line.setStartY(this.vertex1.getCoordinates().getY());
		line.setEndX(vertex2.getCoordinates().getX());
		line.setEndY(vertex2.getCoordinates().getY());
	}
	public void appuyer(){
		line.setFill(Color.GREEN);
	}

	@Override
	public String getEdgeText() {
		// TODO Auto-generated method stub
		return this.getEdgeCtx().getEdgeText();
	}
}
