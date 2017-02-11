package graphml;
import graphml.GraphMLDemo.edge;
import graphml.GraphMLDemo.node;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.UndirectedGraph;

/**
 * Created by IntelliJ IDEA.
 * User: Matt
 * Date: 5/16/11
 * Time: 2:13 PM
 */
public class TreeBuilder
{
    DelegateForest<node,edge> mTree;
    TreeBuilder(UndirectedGraph<node,edge> graph)
    {
        mTree = new DelegateForest<node, edge>();
        for (node n : graph.getVertices())
        {
            mTree.addVertex(n);
        }
        for (edge e : graph.getEdges())
        {
            mTree.addEdge(e, graph.getSource(e),graph.getDest(e));
        }
    }

    public DelegateForest<node, edge> getTree()
    {
        return mTree;
    }
}
