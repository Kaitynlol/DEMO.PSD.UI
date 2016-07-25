package org.opencabe.demo.psd.editparts;

import org.eclipse.draw2d.Figure;
import org.eclipse.emf.common.notify.Notification;
import org.opencabe.demo.psd.figures.StartFigure;
import org.opencabe.diagrams.editparts.ComponentEditPart;
import org.opencabe.diagrams.editparts.ComponentWithFixedAnchorsEditPart;
import org.scopio.demo.psd.Start;

public class StartEditPart extends ComponentWithFixedAnchorsEditPart {

	@Override
	public void setModel(Object model) {
		super.setModel((Start) model);
	}

	@Override
	public Start getModel() {
		return (Start) super.getModel();
	}

	@Override
	protected StartFigure createFigure() {
		StartFigure figure = new StartFigure();
		Figure anchors = createNodePlate();
		figure.add(anchors);
		return figure;
	}

	@Override
	public void notifyChanged(Notification msg) {
		super.notifyChanged(msg);

		refreshVisuals();
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();

		StartFigure figure = (StartFigure) getFigure();
		Start type = getModel();

	}
}
