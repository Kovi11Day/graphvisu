package graphml;
/*
 * Copyright (c) 2003, the JUNG Project and the Regents of the University of
 * California All rights reserved.
 *
 * This software is open-source under the BSD license; see either "license.txt"
 * or http://jung.sourceforge.net/license.txt for a description.
 *
 */
/*
** GraphMLDemo
** Copyright (C) 2011, Matt Johnson
**
** GraphML.java (Author(s): Matt Johnson)
** 
** Permission is hereby granted, free of charge, to any person obtaining a
** copy of this software and associated documentation files (the "Software"),
** to deal in the Software without restriction, including without limitation
** the rights to use, copy, modify, merge, publish, distribute, sublicense,
** and/or sell copies of the Software, and to permit persons to whom the
** Software is furnished to do so, subject to the following conditions:
**
** The above copyright notice and this permission notice shall be included
** in all copies or substantial portions of the Software. Changes in the
** copyright notice and/or disclaimer are illegal.
**
** THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
** OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
** FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL
** ANY MEMBER OF UNCC'S GAME LAB BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
** WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
** CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import graphml.GraphMLDemo.edge;
import graphml.GraphMLDemo.node;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.io.GraphMLMetadata;
import edu.uci.ics.jung.io.GraphMLReader;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.BidiMap;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

class GraphML
{
    public GraphML(String filename) throws ParserConfigurationException, SAXException, IOException
    {
        GraphMLReader<UndirectedGraph<node, edge>, node, edge> gmlr =
            new GraphMLReader<UndirectedGraph<node, edge>, node, edge>(new VertexFactory(), new EdgeFactory());
            final UndirectedGraph<node, edge> graph = new UndirectedSparseMultigraph<node, edge>();
            
       // final DirectedGraph<node, edge> graph = new DirectedSparseMultigraph<node, edge>();
        gmlr.load(filename, graph); //Here we read in our graph. filename is our .graphml file, and graph is where we
        // will store our graph.
        BidiMap<node, String> vertex_ids = gmlr.getVertexIDs();  //The vertexIDs are stored in a BidiMap.
        Map<String, GraphMLMetadata<node>> vertex_color = gmlr.getVertexMetadata(); //Our vertex Metadata is stored in a map.
        Map<String, GraphMLMetadata<edge>> edge_meta = gmlr.getEdgeMetadata(); // Our edge Metadata is stored in a map.
        System.out.println(graph.getEdgeCount());
        // Here we iterate through our vertices, n, and we set the value and the color of our nodes from the data we have
        // in the vertex_ids map and vertex_color map.
        for (node n : graph.getVertices())
        {
            n.setValue(vertex_ids.get(n)); //Set the value of the node to the vertex_id which was read in from the GraphML Reader.
            n.setColor(vertex_color.get("d0").transformer.transform(n)); // Set the color, which we get from the Map, vertex_color.
            //Let's print out the data so we can get a good understanding of what we've got going on.
            System.out.println("ID: "+n.getID()+", Value: "+n.getValue()+", Color: "+n.getColor());
        }

        // Just as we added the vertices to the graph, we add the edges as well.
        for (edge e : graph.getEdges())
        {
            e.setValue(edge_meta.get("d1").transformer.transform(e)); //Set the edge's value.
            System.out.println("Edge ID: "+e.getID());
        }

        TreeBuilder treeBuilder = new TreeBuilder(graph);




        // create a simple graph for the demo:
        //First we make a VisualizationViewer, of type node, edge. We give it our Layout, and the Layout takes a graph in it's constructor.
        //VisualizationViewer<node, edge> vv = new VisualizationViewer<node, edge>(new FRLayout<node, edge>(graph));

        VisualizationViewer<node, edge> vv = new VisualizationViewer<node, edge>(new TreeLayout<node, edge>(treeBuilder.getTree()));

        //Next we set some rendering properties. First we want to color the vertices, so we provide our own vertexPainter.
        vv.getRenderContext().setVertexFillPaintTransformer(new vertexPainter());
        //Then we want to provide labels to our nodes, Jung provides a nice function which makes the graph use a vertex's ToString function
        //as it's way of labelling. We do the same for the edge. Look at the edge and node classes for their ToString function.
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<node>());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<edge>());

        // Next we do some Java stuff, we create a frame to hold the graph
        final JFrame frame = new JFrame();
        frame.setTitle("GraphMLReader for Trees - Reading in Attributes"); //Set the title of our window.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Give a close operation.

        //Here we get the contentPane of our frame and add our a VisualizationViewer, vv.
        frame.getContentPane().add(vv);

        //Finally, we pack it to make sure it is pretty, and set the frame visible. Voila.
        frame.pack();
        frame.setVisible(true);
    }
}