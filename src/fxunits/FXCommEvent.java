package fxunits;

import javafx.event.Event;
import javafx.event.EventType;

public class FXCommEvent extends Event{

		private FXvertexe target;
	  //private String parameter;
	    public static final EventType<FXCommEvent> COMM = new EventType<>(ANY, "COMM");
	    
	    public FXCommEvent(FXvertexe target) {
	        super(FXCommEvent.COMM);
	        this.target = target;
	    }

	    public FXvertexe getParameter() {
	        return this.target;
	    }

}
