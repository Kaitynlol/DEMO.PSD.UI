package org.opencabe.demo.psd.figures;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class WaitingLinkFigure extends PolylineConnection {
	private MessagePolylineEndDecoration endArrow;
	private MessagePolylineStartDecoration startArrow;
	
	private Color greyBackgroundColor = new Color(null, 192, 192, 192);
	private Color whiteBackgroundColor = new Color(null, 255, 255, 255);

	private Label endLabel;
	private Label startLabel;

	public WaitingLinkFigure() {
		this.setLineStyle(Graphics.LINE_DASH);
		this.setConnectionRouter(new ManhattanConnectionRouter());
		this.setLineWidth(2);

		Display display = Display.getCurrent();

		endArrow = new MessagePolylineEndDecoration();
		endArrow.setForegroundColor(new Color(null, 0, 0, 0));
		startArrow = new MessagePolylineStartDecoration();
		startArrow.setForegroundColor(new Color(null, 0, 0, 0));

		setTargetDecoration(endArrow);
		setSourceDecoration(startArrow);

		endLabel = new Label();
		ConnectionEndpointLocator endLocator = new ConnectionEndpointLocator(
				this, true);
		endLocator.setUDistance(-5);
		endLocator.setVDistance(10);
		this.add(endLabel, endLocator);

		startLabel = new Label();
		ConnectionEndpointLocator startLocator = new ConnectionEndpointLocator(
				this, false);
		startLocator.setUDistance(-5);
		startLocator.setVDistance(10);
		this.add(startLabel, startLocator);

	}

	public void setTargetDecoration() {
		setTargetDecoration(endArrow);
	}

	private RotatableDecoration createTargetDecoration() {
		PolylineDecoration df = new PolylineDecoration();
		df.setLineWidth(2);
		df.setLineStyle(Graphics.LINE_DASH);
		return df;
	}

	public Label getStartLabel() {
		return startLabel;
	}

	public void setStartLabel(String startLabel) {
		this.startLabel.setText(startLabel);
	}

	public Label getEndLabel() {
		return endLabel;
	}

	public void setEndLabel(String endLabel) {
		this.endLabel.setText(endLabel);
	}

	public void setGrey() {
		this.endArrow.setBackgroundColor(greyBackgroundColor);
	}

	public void setWhite() {
		this.endArrow.setBackgroundColor(whiteBackgroundColor);
	}

}
