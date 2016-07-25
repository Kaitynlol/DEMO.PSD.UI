package org.opencabe.demo.psd.editparts;

import java.util.HashMap;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.emf.common.notify.Notification;
import org.opencabe.bpmn.editparts.DefaultSizeNodeFigureWithFixedAnchors;
import org.opencabe.demo.psd.figures.TRFigure;
import org.opencabe.diagrams.editparts.ComponentEditPart;
import org.opencabe.diagrams.editparts.ComponentWithFixedAnchorsEditPart;
import org.opencabe.diagrams.editparts.ContainerEditPart;
import org.scopio.demo.psd.DemoPSDPackage;

import org.scopio.demo.psd.TR;

public class TREditPart extends ComponentWithFixedAnchorsEditPart {

	@Override
	public void setModel(Object model) {
		super.setModel((TR) model);
	}

	@Override
	public TR getModel() {
		return (TR) super.getModel();
	}

	@Override
	protected TRFigure createFigure() {
		TRFigure figure = new TRFigure();
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

		TRFigure figure = (TRFigure) getFigure();
		TR type = getModel();

		figure.setID(type.getID());
		figure.setName(type.getName());

		if (type.getResult() == null) {
			type.setResult(type.getID().substring(1, type.getID().length()));
		}
	}
}
