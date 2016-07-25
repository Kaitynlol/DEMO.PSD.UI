package org.opencabe.demo.psd.figures;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Disposable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.opencabe.diagrams.figures.ComponentFigure;
import org.scopio.diagrams.primitives.RectangleData;

public class StartFigure extends LayeredPane implements ComponentFigure,
		Disposable {
	public static final String BACKGROUND_LAYER = "background";
	public static final String CONTENT_LAYER = "content";
	// private static final String ICON_LAYER = "icon";

	private static Font boldFont;

	private Ellipse backgroundFigure;
	private ImageFigure typeFigure;

	private Color foregroundColor = new Color(null, 128, 128, 128);
	private Color backgroundColor = new Color(null, 255, 255, 255);

	public StartFigure() {
		createLayers();
		fillLayers();
	}

	protected void createLayers() {
		Layer backgroundLayer = new Layer();
		backgroundLayer.setLayoutManager(new BorderLayout());

		Layer contentLayer = new Layer();
		contentLayer.setLayoutManager(new BorderLayout());

		this.addLayerAfter(backgroundLayer, BACKGROUND_LAYER, null);
		this.addLayerAfter(contentLayer, CONTENT_LAYER, BACKGROUND_LAYER);
	}

	protected void fillLayers() {
		backgroundFigure = new Ellipse();
		backgroundFigure.setLineWidth(1);
		backgroundFigure.setBackgroundColor(backgroundColor);
		backgroundFigure.setForegroundColor(foregroundColor);		

		Layer backgroundLayer = getLayer(BACKGROUND_LAYER);
		backgroundLayer.add(backgroundFigure, BorderLayout.CENTER);

		typeFigure = new ImageFigure();
		Layer contentLayer = getLayer(CONTENT_LAYER);
		contentLayer.add(typeFigure, BorderLayout.CENTER);
	}

	public void setIcon(Image icon) {
		typeFigure.setImage(icon);
	}

	public Image getIcon() {
		return typeFigure.getImage();
	}

	@Override
	public void setBounds(RectangleData rect) {
		Rectangle bounds = new Rectangle(rect.x, rect.y, 25,
				25);		
		getParent().setConstraint(this, bounds);
	}

	public void setBackgroundColor(RGB rgb) {
		backgroundColor.dispose();
		backgroundColor = new Color(null, rgb.red, rgb.green, rgb.blue);
	}

	public void setForegroundColor(RGB rgb) {
		foregroundColor.dispose();
		foregroundColor = new Color(null, rgb.red, rgb.green, rgb.blue);
	}

	@Override
	public void setParent(IFigure p) {
		super.setParent(p);

		if (boldFont == null) {
			Font font = p.getFont();
			FontData[] data = font.getFontData();
			data[0].setStyle(SWT.BOLD);
			boldFont = new Font(Display.getCurrent(), data[0]);
		}
	}

	@Override
	public void dispose() {
		foregroundColor.dispose();
		backgroundColor.dispose();
	}

	public class BorderMine extends Figure {

		public BorderMine() {
			this.setOpaque(false);
		}

		@Override
		protected void paintBorder(Graphics graphics) {
			Rectangle rect = this.getBounds();
			graphics.drawRectangle(rect);			
		}

	}

	public void deleteIcon() {
		typeFigure = new ImageFigure();
	}
}
