package demos;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.DefaultVisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationModel;

import java.awt.Dimension;
import java.awt.geom.Point2D;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class Demo0 extends Application {

	private static final int CIRCLE_SIZE = 5;

	/*
	 * M�thode cr�ant un graphe � partir d'un tableau de sommets (String[] v),
	 * et d'une matrice d'adjacence (int[][] edges)
	 */
	public static Graph<String, Integer> createGraph(String[] v, int[][] edges) {
		if (v == null) {
			return null;
		}
		Graph<String, Integer> ig = new DirectedSparseGraph<String, Integer>();
		/* Ajout des sommets */
		for (int i = 0; i < v.length; i++) {
			ig.addVertex(v[i]);
			// System.out.println("Sommet " + v[i]);
		}

		if (edges != null) {
			/* ajouts des aretes */
			for (int i = 0; i < edges.length; i++) {
				ig.addEdge(i, v[edges[i][0]], v[edges[i][1]]);

			}
		}
		return ig;
	}

	/*
	 * M�thode ajoutant un graphe a la Scene
	 */

	private void addGraphToScene(Graph<String, Integer> graph1,
			Layout<String, Integer> layout, Group viz) {

		/*
		 * Ajouts des sommets du graphe a la racine de l'ensemble des acteurs de
		 * la scene
		 */
		for (String v : graph1.getVertices()) {
			Point2D p = layout.transform(v);
			Circle circle = CircleBuilder.create().centerX(p.getX())
					.centerY(p.getY()).radius(CIRCLE_SIZE).fill(Color.BLUE)
					.build();
			viz.getChildren().add(circle);
		}

		for (Integer n : graph1.getEdges()) {
			/*
			 * getEndpoints() renvoie les extremites d'un segment en tant que
			 * pair
			 */
			Pair<String> endpoints = graph1.getEndpoints(n);
			/* recuperation des 2 extremit�s du segment en tant que 2 points */
			Point2D pStart = layout.transform(endpoints.getFirst());
			Point2D pEnd = layout.transform(endpoints.getSecond());

			Line line = LineBuilder.create().startX(pStart.getX())
					.startY(pStart.getY()).endX(pEnd.getX()).endY(pEnd.getY())
					.fill(Color.RED).build();

			viz.getChildren().add(line);
		}
	}

	/*
	 * methode start 
	 * 
	 */
	@Override
	public void start(Stage stage) throws Exception {
		// Pane p = new Pane();
		Group viz1 = new Group();

		Scene scene = new Scene(viz1, 1200, 1000, Color.WHITE);

		String[] v = { "A", "B", "C", "D", "E", "F" };
		int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 2, 0 }, { 3, 2 },
				{ 3, 5 }, { 5, 4 } };
		Graph<String, Integer> graph1 = createGraph(v, edges);
		Layout<String, Integer> layout = new CircleLayout<>(graph1);
		VisualizationModel<String, Integer> vm1 = new DefaultVisualizationModel<>(
				layout, new Dimension(400, 400));

		addGraphToScene(graph1, layout, viz1);
		// root.getChildren().add(viz1);

		stage.setTitle("DrawGraph");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(Demo0.class,args);
	}
}
