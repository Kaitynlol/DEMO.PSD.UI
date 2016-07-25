package org.opencabe.demo.psd.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.opencabe.demo.psd.figures.ResponceLinkFigure;
import org.opencabe.diagrams.editparts.EdgeEditPart;
import org.scopio.demo.psd.DemoPSDPackage;
import org.scopio.demo.psd.ResponceLink;

public class ResponceLinkEditPart extends EdgeEditPart {

	@Override
	public ResponceLink getModel() {
		return (ResponceLink) super.getModel();
	}

	@Override
	public void setModel(Object model) {
		super.setModel((ResponceLink) model);
	}

	@Override
	protected IFigure createFigure() {
		return new ResponceLinkFigure();
	}

	@Override
	public ResponceLinkFigure getFigure() {
		return (ResponceLinkFigure) super.getFigure();
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		getFigure().setTargetDecoration();
	
	}

	@Override
	public void notifyChanged(Notification msg) {
		super.notifyChanged(msg);

		int featureID = msg.getFeatureID(ResponceLinkFigure.class);

		switch (featureID) {
		case DemoPSDPackage.RESPONCE_LINK__TARGET:
			refreshVisuals();
		}

		ResponceLinkFigure figure = getFigure();
		ResponceLink type = getModel();
		

		switch (type.getExName()) {
		case NONE:
			figure.setEndLabel("");
			figure.setWhite();
			break;
		case AC:
			figure.setEndLabel("ac");
			figure.setWhite();
			break;
		case DC:
			figure.setEndLabel("dc");
			figure.setWhite();
			break;
		case EX:
			figure.setEndLabel("ex");
			figure.setGrey();
			break;
		case PM:
			figure.setEndLabel("pm");
			figure.setWhite();
			break;
		case QT:
			figure.setEndLabel("qt");
			figure.setWhite();
			break;
		case RJ:
			figure.setEndLabel("rj");
			figure.setWhite();
			break;
		case RQ:
			figure.setEndLabel("rq");
			figure.setWhite();
			break;
		case SP:
			figure.setEndLabel("sp");
			figure.setWhite();
			break;
		case ST:
			figure.setEndLabel("st");
			break;
		default:
			figure.setEndLabel("None");
			break;
		}

		switch (type.getInName()) {
		case NONE:
			figure.setStartLabel("");
			break;
		case AC:
			figure.setStartLabel("ac");
			break;
		case DC:
			figure.setStartLabel("dc");
			break;
		case EX:
			figure.setStartLabel("ex");
			break;
		case PM:
			figure.setStartLabel("pm");
			break;
		case QT:
			figure.setStartLabel("qt");
			break;
		case RJ:
			figure.setStartLabel("rj");
			break;
		case RQ:
			figure.setStartLabel("rq");
			break;
		case SP:
			figure.setStartLabel("sp");
			break;
		case ST:
			figure.setStartLabel("st");
			break;
		default:
			figure.setStartLabel("None");
			break;
		}
	}

}
