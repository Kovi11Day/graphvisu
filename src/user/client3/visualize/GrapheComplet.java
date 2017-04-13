package user.client3.visualize;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import graphe.BuildGraph;
import graphe.MusicalEdge;
import graphe.MusicalGraph;
import graphe.MusicalVertex;
import graphvisunits.VisuEdge;
import graphvisunits.VisuVertex;

import java.awt.Dimension;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import musicalLayout.GridLayout2;

public class GrapheComplet extends Application {
	private MusicalGraph graphe;

	public Graph<VisuVertex, VisuEdge> buildMyExample() {
		Graph<VisuVertex, VisuEdge> jungGraph = new DirectedSparseGraph<>();
		double uniqueID = 0;
		MusicalVertex v11;
		MusicalVertex v12;
		MusicalVertex v13;
		MusicalVertex v10;
		MusicalVertex v14;

		MusicalVertex v21;
		MusicalVertex v22;
		MusicalVertex v23;
		MusicalVertex v20;
		MusicalVertex v24;

		MusicalVertex v31;
		MusicalVertex v32;
		MusicalVertex v33;
		MusicalVertex v30;
		MusicalVertex v34;

		MusicalVertex v01;
		MusicalVertex v02;
		MusicalVertex v03;
		MusicalVertex v00;
		MusicalVertex v04;

		jungGraph.addVertex(v11 = new MusicalVertex(uniqueID++, 1, 1));
		jungGraph.addVertex(v12 = new MusicalVertex(uniqueID++, 1, 2));
		jungGraph.addVertex(v13 = new MusicalVertex(uniqueID++, 1, 3));
		jungGraph.addVertex(v10 = new MusicalVertex(uniqueID++, 1, 0));

		jungGraph.addVertex(v21 = new MusicalVertex(uniqueID++, 2, 1));
		jungGraph.addVertex(v22 = new MusicalVertex(uniqueID++, 2, 2));
		jungGraph.addVertex(v23 = new MusicalVertex(uniqueID++, 2, 3));
		jungGraph.addVertex(v20 = new MusicalVertex(uniqueID++, 2, 0));

		jungGraph.addVertex(v31 = new MusicalVertex(uniqueID++, 3, 1));
		jungGraph.addVertex(v32 = new MusicalVertex(uniqueID++, 3, 2));
		jungGraph.addVertex(v33 = new MusicalVertex(uniqueID++, 3, 3));
		jungGraph.addVertex(v30 = new MusicalVertex(uniqueID++, 3, 0));

		jungGraph.addVertex(v01 = new MusicalVertex(uniqueID++, 0, 1));
		jungGraph.addVertex(v02 = new MusicalVertex(uniqueID++, 0, 2));
		jungGraph.addVertex(v03 = new MusicalVertex(uniqueID++, 0, 3));
		jungGraph.addVertex(v00 = new MusicalVertex(uniqueID++, 0, 0));

		jungGraph.addVertex(v14 = new MusicalVertex(uniqueID++, 1, 4));
		jungGraph.addVertex(v24 = new MusicalVertex(uniqueID++, 2, 4));
		jungGraph.addVertex(v34 = new MusicalVertex(uniqueID++, 3, 4));
		jungGraph.addVertex(v04 = new MusicalVertex(uniqueID++, 0, 4));
		/***/

		jungGraph.addEdge(new MusicalEdge(v03, v13), v03, v13);
		jungGraph.addEdge(new MusicalEdge(v02, v12), v02, v12);
		jungGraph.addEdge(new MusicalEdge(v01, v12), v01, v12);
		jungGraph.addEdge(new MusicalEdge(v01, v11), v01, v11);
		jungGraph.addEdge(new MusicalEdge(v01, v10), v01, v10);
		jungGraph.addEdge(new MusicalEdge(v00, v11), v00, v11);

		jungGraph.addEdge(new MusicalEdge(v13, v22), v13, v22);
		jungGraph.addEdge(new MusicalEdge(v12, v21), v12, v21);
		jungGraph.addEdge(new MusicalEdge(v11, v22), v11, v22);
		jungGraph.addEdge(new MusicalEdge(v10, v20), v10, v20);

		jungGraph.addEdge(new MusicalEdge(v22, v33), v22, v33);
		jungGraph.addEdge(new MusicalEdge(v22, v31), v22, v31);
		jungGraph.addEdge(new MusicalEdge(v21, v32), v21, v32);
		jungGraph.addEdge(new MusicalEdge(v20, v31), v20, v31);
		jungGraph.addEdge(new MusicalEdge(v20, v30), v20, v30);

		jungGraph.addEdge(new MusicalEdge(v04, v13), v04, v13);
		jungGraph.addEdge(new MusicalEdge(v14, v23), v14, v23);

		return jungGraph;

	}

	@Override
	public void start(Stage stage) throws Exception {
		Graph<VisuVertex, VisuEdge> jungGraph = buildMyExample();
		this.graphe = new MusicalGraph(jungGraph);
		Layout<VisuVertex, VisuEdge> layout = new GridLayout2<VisuVertex, VisuEdge>(
				graphe.getJungGraphe());
		layout.setSize(new Dimension(700, 700));
		graphe.applyLayout(layout);

		/* NEED NUMBER OF VERTICES PER COLUMN */
		// 5 -> number_of_vertices_per_column
		jungGraph = BuildGraph.grapheComplet(jungGraph, 5);
		this.graphe = new MusicalGraph(jungGraph);
		Pane pane = new StackPane();
		pane.setPrefSize(700, 700); // set a default size for your stackpane
		pane.getChildren().add(graphe);
		StackPane.setAlignment(graphe, Pos.CENTER);

		Scene s = new Scene(pane);

		// root.getChildren().add(pane);
		stage.setScene(s);
		stage.show();
	}

	public static void main(String args[]) {
		Application.launch(GrapheComplet.class, args);
	}
}
