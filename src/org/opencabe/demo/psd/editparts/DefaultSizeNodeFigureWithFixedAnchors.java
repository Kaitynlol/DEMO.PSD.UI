package org.opencabe.demo.psd.editparts;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;

public class DefaultSizeNodeFigureWithFixedAnchors extends Figure {

	private HashMap<String, FixedConnectionAnchor> anchors = new HashMap<String, FixedConnectionAnchor>();

	public DefaultSizeNodeFigureWithFixedAnchors(Dimension defSize,
			HashMap<String, PrecisionPoint> anchorLocations) {
		this(defSize.width, defSize.height, anchorLocations);
	}

	public DefaultSizeNodeFigureWithFixedAnchors(int width, int height,
			HashMap<String, PrecisionPoint> anchorLocations) {
		setSize(width, height);
		if (anchorLocations.size() == 0)
			throw new IllegalArgumentException(
					"At least one fixed anchor must be specified");
		Iterator<String> terminals = anchorLocations.keySet().iterator();
		while (terminals.hasNext()) {
			String terminal = terminals.next();
			PrecisionPoint anchorLocation = anchorLocations.get(terminal);
			anchors.put(terminal, new FixedConnectionAnchor(this,
					anchorLocation));
		}
	}

	public ConnectionAnchor getSourceConnectionAnchorAt(Point p) {
		return findNearestAnchorFrom(p);
	}

	public ConnectionAnchor getTargetConnectionAnchorAt(Point p) {
		return findNearestAnchorFrom(p);
	}

	public ConnectionAnchor getConnectionAnchor(String terminal) {
		return anchors.get(terminal);
	}

	public String getConnectionAnchorTerminal(ConnectionAnchor c) {
		String selectedTerminal = null;
		Iterator<String> terminals = anchors.keySet().iterator();
		while (terminals.hasNext() && selectedTerminal == null) {
			String terminal = terminals.next();
			FixedConnectionAnchor anchor = anchors.get(terminal);
			if (anchor == c) {
				selectedTerminal = terminal;
			}
		}
		return selectedTerminal;
	}

	private ConnectionAnchor findNearestAnchorFrom(Point point) {
		ConnectionAnchor result = null;
		if (point == null || anchors.size() == 1) {
			result = anchors.values().iterator().next();
		} else {
			double minDistance = Double.MAX_VALUE;
			String nearestTerminal = null;
			Iterator<String> terminals = anchors.keySet().iterator();
			while (terminals.hasNext()) {
				String terminal = terminals.next();
				FixedConnectionAnchor anchor = anchors.get(terminal);
				Point anchorPosition = anchor.getLocation();
				double distance = point.getDistance(anchorPosition);
				if (distance < minDistance) {
					minDistance = distance;
					nearestTerminal = terminal;
				}
			}
			result = anchors.get(nearestTerminal);
		}
		return result;
	}

	public Collection<FixedConnectionAnchor> getFixedAnchors() {
		return anchors.values();
	}
}
