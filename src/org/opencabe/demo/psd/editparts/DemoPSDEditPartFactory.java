package org.opencabe.demo.psd.editparts;

import org.eclipse.emf.common.notify.Adapter;
import org.opencabe.diagrams.editparts.DiagramEditPart;
import org.opencabe.diagrams.editparts.EditPartAdapter;
import org.scopio.demo.psd.DemoPSDPackage;
import org.scopio.demo.psd.util.DemoPSDAdapterFactory;

public class DemoPSDEditPartFactory extends DemoPSDAdapterFactory {

	@Override
	public boolean isFactoryForType(Object object) {
		return EditPartAdapter.class.equals(object);
	}

	@Override
	public Adapter createDemoPSDDiagramAdapter() {
		return new DiagramEditPart(DemoPSDPackage.Literals.DEMO_PSD_ELEMENT)
				.getEditPartAdapter();
	}
	
	 @Override
	  public Adapter createTRAdapter() {
	    return new TREditPart().getEditPartAdapter();
	  }
	 
	 @Override
	  public Adapter createActorBoundaryAdapter() {
	    return new ActorBoundaryEditPart().getEditPartAdapter();
	  }
	 
	 @Override
	  public Adapter createStartAdapter() {
	    return new StartEditPart().getEditPartAdapter();
	  }
	 
	 	 
	 @Override
	  public Adapter createResponceLinkAdapter() {
	  	return new ResponceLinkEditPart().getEditPartAdapter();
	  }
	 
	 @Override
	  public Adapter createWaitingLinkAdapter() {
	  	return new WaitingLinkEditPart().getEditPartAdapter();
	  }



}