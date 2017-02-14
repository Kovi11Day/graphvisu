package musicalLayout.interfaces;

import clientctx.IClientVertexContext;
//import graphe.vertex.interfaces.Ivertex;
//import visualizeGraphe.IVertexe;
public interface IVerticesNotes {
	
	IClientVertexContext[] getNotes();
	boolean isFilled ();
	void addNote(IClientVertexContext v);
	int getK();
}
