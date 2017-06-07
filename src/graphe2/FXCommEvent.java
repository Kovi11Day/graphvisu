package graphe2;

import javafx.event.Event;
import javafx.event.EventType;

public class FXCommEvent extends Event{

		private MusicalVertex target;
	  //private String parameter;
	    public static final EventType<FXCommEvent> COMM = new EventType<>(ANY, "COMM");
	    
	    public FXCommEvent(MusicalVertex target) {
	        super(FXCommEvent.COMM);
	        this.target = target;
	    }

	    public MusicalVertex getParameter() {
	        return this.target;
	    }

}
