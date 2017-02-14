package clientctx;

import fxunits.FXvertexe;

public interface IClientEdgeContext {
	IClientVertexContext getVertex1();
	IClientVertexContext getVertex2();
	String getEdgeText(); 
}
