package musicalLayout;

import java.util.ArrayList;

import musicalLayout.interfaces.INotes;
import musicalLayout.interfaces.IVerticesNotes;

public class Notes implements INotes {
	private ArrayList<IVerticesNotes> partition ;
	
	public Notes(){
		partition = new ArrayList<IVerticesNotes>();
	}
	
	public void addVerticesNotes(IVerticesNotes v){
		partition.add(v);
	}
	public ArrayList<IVerticesNotes> getPartition(){
		return partition;
	}

}
