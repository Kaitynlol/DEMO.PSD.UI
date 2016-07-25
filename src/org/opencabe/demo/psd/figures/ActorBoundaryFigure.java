package org.opencabe.demo.psd.figures;


import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
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
import org.opencabe.diagrams.figures.ComponentFigure;
import org.scopio.diagrams.primitives.RectangleData;

public class ActorBoundaryFigure extends LayeredPane implements
		ComponentFigure, Disposable {

	public static final String BACKGROUND_LAYER = "background";
	public static final String CONTENT_LAYER = "content";

	private static Font boldFont;

	private Layer contentLayer;	
	private IFigure backgroundFigure;	
	private Label idLabel;	

	private TextFlow actionLabel;
	private FlowPage flowPage;

	
	private Color foregroundColorX = new Color(null, 192, 192, 192);
	private Color backgroundColor = new Color(null, 255, 255, 255);

	public ActorBoundaryFigure() {
		createLayers();
		fillLayers();
	}

	protected void createLayers() {
		Layer backgroundLayer = new Layer();
		backgroundLayer.setLayoutManager(new StackLayout());

		contentLayer = new Layer();
		BorderLayout layout = new BorderLayout();
		
		contentLayer.setLayoutManager(layout);
		contentLayer.setBorder(new MarginBorder(15, 7, 10, 10));

		this.addLayerAfter(backgroundLayer, BACKGROUND_LAYER, null);
		this.addLayerAfter(contentLayer, CONTENT_LAYER, BACKGROUND_LAYER);
	}

	protected void fillLayers() {		
		backgroundFigure =  new RectangleFigure();
		backgroundFigure.setSize(500, 150);
		backgroundFigure.setBackgroundColor(backgroundColor);

		Layer backgroundLayer = getLayer(BACKGROUND_LAYER);
		backgroundLayer.add(backgroundFigure,
				BorderLayout.CENTER);		

		idLabel = new Label();
		idLabel.setLabelAlignment(PositionConstants.MIDDLE);
		contentLayer.add(idLabel, BorderLayout.TOP);

		flowPage = new FlowPage();
		flowPage.setBorder(new MarginBorder(0, 0, 10, 0));
		actionLabel = new TextFlow("<...>");

		actionLabel.setLayoutManager(new ParagraphTextLayout(actionLabel,
				ParagraphTextLayout.WORD_WRAP_SOFT));

		flowPage.setLayoutManager(new PageFlowLayout(flowPage));
		flowPage.add(actionLabel);
		flowPage.setHorizontalAligment(PositionConstants.NORTH_WEST);

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
		backgroundFigure.setBackgroundColor(backgroundColor);
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
		backgroundColor.dispose();
	}

	

		@Override
		protected void paintBorder(Graphics graphics) {
		Rectangle rect = backgroundFigure.getBounds();
		    graphics.setLineWidth(8);
			graphics.setForegroundColor(foregroundColorX);
			
			graphics.drawLine(rect.getTopLeft(), rect.getTopRight());
			graphics.drawLine(rect.getBottomLeft(), rect.getBottomRight());
			
			graphics.setForegroundColor(backgroundColor);
			
			graphics.drawLine(rect.getTopLeft(), rect.getBottomLeft());
			graphics.drawLine(rect.getTopRight(), rect.getBottomRight());
			

		}

	}

