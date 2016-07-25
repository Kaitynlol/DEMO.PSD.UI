package org.opencabe.demo.psd.figures;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.PageFlowLayout;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.gef.Disposable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.opencabe.diagrams.figures.ContainerFigure;
import org.scopio.diagrams.primitives.RectangleData;

public class TRFigure extends LayeredPane implements
		ContainerFigure, Disposable {

	public static final String BACKGROUND_LAYER = "background";
	public static final String CONTENT_LAYER = "content";
	public static final String CONTENT_FIG_LAYER = "content_fig";

	private static Font boldFont;

	private Layer contentLayer;
	private RoundedRectangle backgroundRoundedRectangleFigure;
	private IFigure backgroundDiamondFigure;
	private Label idLabel;

	private TextFlow actionLabel;
	private FlowPage flowPage;

	private Color foregroundColor = new Color(null, 128, 128, 128);
	private Color foregroundColorX = new Color(null, 255, 4, 4);
	private Color backgroundColor = new Color(null, 255, 255, 255);

	public TRFigure() {
		createLayers();
		fillLayers();
	}

	protected void createLayers() {
		Layer backgroundLayer = new Layer();
		backgroundLayer.setLayoutManager(new StackLayout());

		contentLayer = new Layer();
		BorderLayout layout = new BorderLayout();
		// layout.setStretchMinorAxis(true);
		// layout.setSpacing(3);
		contentLayer.setLayoutManager(layout);
		contentLayer.setBorder(new MarginBorder(15, 7, 10, 10));

		Layer contentFigLayer = new Layer();
		contentFigLayer.setLayoutManager(new XYLayout());

		this.addLayerAfter(backgroundLayer, BACKGROUND_LAYER, null);
		this.addLayerAfter(contentLayer, CONTENT_LAYER, BACKGROUND_LAYER);
		this.addLayerAfter(contentFigLayer, CONTENT_FIG_LAYER, CONTENT_LAYER);
	}

	protected void fillLayers() {
		backgroundRoundedRectangleFigure = new RoundedRectangle();
		backgroundRoundedRectangleFigure.setLineWidth(1);
		backgroundRoundedRectangleFigure.setSize(150, 35);
		backgroundRoundedRectangleFigure.setCornerDimensions(new Dimension(35,
				35));

		backgroundRoundedRectangleFigure.setBackgroundColor(backgroundColor);
		backgroundDiamondFigure = new DiamondMine();

		Layer backgroundLayer = getLayer(BACKGROUND_LAYER);
		backgroundLayer.add(backgroundRoundedRectangleFigure,
				BorderLayout.CENTER);
		backgroundLayer.add(backgroundDiamondFigure, BorderLayout.CENTER);

		idLabel = new Label();
		idLabel.setLabelAlignment(PositionConstants.MIDDLE);
		contentLayer.add(idLabel, BorderLayout.TOP);

		flowPage = new FlowPage();
		flowPage.setBorder(new MarginBorder(20, 0, 10, 0));
		actionLabel = new TextFlow("<...>");

		actionLabel.setLayoutManager(new ParagraphTextLayout(actionLabel,
				ParagraphTextLayout.WORD_WRAP_SOFT));

		flowPage.setLayoutManager(new PageFlowLayout(flowPage));
		flowPage.add(actionLabel);
		flowPage.setHorizontalAligment(PositionConstants.CENTER);

		// actionLabel = new Label("<...>");
		// actionLabel.setLabelAlignment(PositionConstants.MIDDLE);

		contentLayer.add(flowPage, BorderLayout.CENTER);
	}

	public void setID(String id) {
		idLabel.setText(id);
	}

	public String getID() {
		return idLabel.getText();
	}

	public void setName(String name) {
		actionLabel.setText(name);
	}

	public String getName() {
		return actionLabel.getText();
	}

	@Override
	public void setBounds(RectangleData rect) {
		Rectangle bounds = new Rectangle(rect.x, rect.y, rect.width,
				rect.height);
		getParent().setConstraint(this, bounds);
		// backgroundDiamondFigure.setDiamondShape(bounds);
	}

	public void setBackgroundColor(RGB rgb) {
		backgroundColor.dispose();
		backgroundColor = new Color(null, rgb.red, rgb.green, rgb.blue);
		backgroundRoundedRectangleFigure.setBackgroundColor(backgroundColor);
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

		idLabel.setFont(boldFont);
	}

	@Override
	public void dispose() {
		foregroundColor.dispose();
		backgroundColor.dispose();
	}

	public class DiamondMine extends Figure {

		public DiamondMine() {
			this.setOpaque(false);
		}

		@Override
		protected void paintBorder(Graphics graphics) {
			Rectangle rect = backgroundRoundedRectangleFigure.getBounds();
			graphics.setLineWidth(2);
			graphics.setForegroundColor(foregroundColorX);

			graphics.drawLine(rect.getLeft().setX(rect.getLeft().x + 25), rect
					.getTop().setY(rect.getTop().y - 6));

			graphics.drawLine(rect.getTop().setY(rect.getTop().y - 6), rect
					.getRight().setX(rect.getRight().x - 25));

			graphics.drawLine(rect.getRight().setX(rect.getRight().x - 25),
					rect.getBottom().setY(rect.getBottom().y + 6));
			graphics.drawLine(rect.getBottom().setY(rect.getBottom().y + 6),
					rect.getLeft().setX(rect.getLeft().x + 25));

		}
	}

	@Override
	public IFigure getContentPane() {

		return getLayer(CONTENT_FIG_LAYER);
	}

}
