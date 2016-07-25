package org.opencabe.demo.psd.editparts;

import java.util.HashMap;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.emf.common.notify.Notification;
import org.opencabe.bpmn.editparts.DefaultSizeNodeFigureWithFixedAnchors;
import org.opencabe.demo.psd.figures.ActorBoundaryFigure;
import org.opencabe.diagrams.editparts.ComponentEditPart;
import org.opencabe.diagrams.editparts.ComponentWithFixedAnchorsEditPart;
import org.opencabe.diagrams.figures.BlockFigure;
import org.scopio.demo.psd.ActorBoundary;

public class ActorBoundaryEditPart extends ComponentWithFixedAnchorsEditPart{

	@Override
	public void setModel(Object model) {
		super.setModel((ActorBoundary) model);
	}

	@Override
	public ActorBoundary getModel() {
		return (ActorBoundary) super.getModel();
	}

	@Override
	protected ActorBoundaryFigure createFigure() {
		ActorBoundaryFigure figure = new ActorBoundaryFigure();
		Figure anchors = createNodePlate();
		figure.add(anchors);		
		return figure;
	}
	
	protected DefaultSizeNodeFigureWithFixedAnchors createNodePlate() {
		HashMap<String, PrecisionPoint> anchorLocations = new HashMap<String, PrecisionPoint>();
		for (double i = 0; i < 1d; i += 0.05d) {
			String keyN = "NORTH" + Double.toString(i);
			String keyS = "SOUTH" + Double.toString(i);
			anchorLocations.put(keyN, new PrecisionPoint(i, 0));
			anchorLocations.put(keyS, new PrecisionPoint(i, 1d));
		}

		anchorLocations.put("EAST", new PrecisionPoint(1d, 0.5d));
		anchorLocations.put("WEST", new PrecisionPoint(0, 0.5d));

		DefaultSizeNodeFigureWithFixedAnchors result = new DefaultSizeNodeFigureWithFixedAnchors(
				getModel().getBounds().width, getModel().getBounds().height,
				anchorLocations);
		return result;
	}

	@Override
	public void notifyChanged(Notification msg) {
		super.notifyChanged(msg);

		refreshVisuals();
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();

		ActorBoundaryFigure figure = (ActorBoundaryFigure) getFigure();
		ActorBoundary type = getModel();
		
		figure.setName(type.getName());
	}
}
