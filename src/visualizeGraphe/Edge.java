package visualizeGraphe;




import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Edge extends Parent{
	private IVertexe start;
	private IVertexe end;
	private Line line = new Line();
	
	//color
	
	public Edge(IVertexe start, IVertexe end){
		line.setStartX(start.getCoordinates().getX());
		line.setStartY(start.getCoordinates().getY());
		line.setEndX(end.getCoordinates().getX());
		line.setEndY(end.getCoordinates().getY());
		line.setFill(Color.BLACK);
		this.getChildren().add(line);
	}
	
	
	

}
