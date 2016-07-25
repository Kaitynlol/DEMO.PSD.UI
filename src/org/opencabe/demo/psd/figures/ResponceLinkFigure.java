package org.opencabe.demo.psd.figures;

import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.handles.ConnectionStartHandle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class ResponceLinkFigure extends PolylineConnection {

	private MessagePolylineEndDecoration endArrow;
	private MessagePolylineStartDecoration startArrow;

	private Color greyBackgroundColor = new Color(null, 192, 192, 192);
	private Color whiteBackgroundColor = new Color(null, 255, 255, 255);

	private Label endLabel;
	private Label startLabel;

	public ResponceLinkFigure() {
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

class MessagePolylineEndDecoration extends RectangleFigure implements
		RotatableDecoration {
	private Point myCenter = new Point();

	public void setLineWidth(int width) {
		super.setLineWidth(2);
	}

	public Rectangle getBounds() {
		if (bounds == null) {

			// int diameter = radius * 2;
			bounds = new Rectangle(myCenter.x - 9, myCenter.y - 9, 18, 18);
			bounds.expand(lineWidth / 2, lineWidth / 2);
		}
		return bounds;
	}

	public void setLocation(Point p) {
		if (myCenter.equals(p)) {
			return;
		}
		myCenter.setLocation(p);
		bounds = null;
	}

	public void setReferencePoint(Point p) {
		// ignore, does not make sense to rotate circle
	}

	/**
	 * Overrides to set the alpha. only for the shape outline. Not for the fill.
	 * 
	 * @param graphics
	 *            the graphics object
	 */
	@Override
	protected void outlineShape(Graphics graphics) {
		int alpha = graphics.getAlpha();
		graphics.setAlpha(100);
		super.outlineShape(graphics);
		graphics.setAlpha(alpha);
	}

}

class MessagePolylineStartDecoration extends Ellipse implements
		RotatableDecoration {
	private int myRadius = 9;
	private Point myCenter = new Point();

	public void setRadius(int radius) {
		erase();
		myRadius = Math.abs(radius);
		bounds = null;
		repaint();
	}

	public void setLineWidth(int width) {
		super.setLineWidth(2);
	}

	public Rectangle getBounds() {
		if (bounds == null) {
			int radius = myRadius;
			int diameter = radius * 2;
			bounds = new Rectangle(myCenter.x - radius, myCenter.y - radius,
					diameter, diameter);
			bounds.expand(lineWidth / 2, lineWidth / 2);
		}
		return bounds;
	}

	public void setLocation(Point p) {
		if (myCenter.equals(p)) {
			return;
		}
		myCenter.setLocation(p);
		bounds = null;
	}

	public void setReferencePoint(Point p) {
		// ignore, does not make sense to rotate circle
	}

	/**
	 * Overrides to set the alpha. only for the shape outline. Not for the fill.
	 * 
	 * @param graphics
	 *            the graphics object
	 */
	@Override
	protected void outlineShape(Graphics graphics) {
		int alpha = graphics.getAlpha();
		graphics.setAlpha(100);
		super.outlineShape(graphics);
		graphics.setAlpha(alpha);
	}

}