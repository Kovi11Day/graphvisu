package musicalLayout;

//import graphe.vertex.interfaces.Ivertex;
import musicalLayout.interfaces.IVerticesNotes;
//import visualizeGraphe.IVertexe;
import clientctx.IClientVertexContext;
public class VerticesNotes implements IVerticesNotes{
	private IClientVertexContext notes [];
	private int k;
	
	public VerticesNotes (){
		notes = new IClientVertexContext[24];
		k = 0;
	}
	//use if user want fix size
	public VerticesNotes(int taille){
		notes = new IClientVertexContext[taille];
		k= 0;
	}
	@Override
	public IClientVertexContext[] getNotes() {
		
		return notes;
	}

	@Override
	public boolean isFilled() {
		
		return (k == notes.length);
	}

	@Override
	public void addNote(IClientVertexContext v) {
		notes[k] = v;
		k++;
		
	}
	public int getK(){
		return k;
	}

}
