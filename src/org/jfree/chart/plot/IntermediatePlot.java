package org.jfree.chart.plot;


import java.util.Map;
import org.jfree.ui.Layer;
import java.util.Collection;
import java.util.ArrayList;

public abstract class IntermediatePlot extends Plot {
	public IntermediatePlot() {
		super();
	}

	protected void addRangeMarkerExtracted(Layer layer, int index, Marker marker, boolean notify,
			Map thisForegroundRangeMarkers, Map thisBackgroundRangeMarkers) {
		Collection markers;
		if (layer == Layer.FOREGROUND) {
			markers = (Collection) thisForegroundRangeMarkers.get(new Integer(index));
			if (markers == null) {
				markers = new java.util.ArrayList();
				thisForegroundRangeMarkers.put(new Integer(index), markers);
			}
			markers.add(marker);
		} else if (layer == Layer.BACKGROUND) {
			markers = (Collection) thisBackgroundRangeMarkers.get(new Integer(index));
			if (markers == null) {
				markers = new java.util.ArrayList();
				thisBackgroundRangeMarkers.put(new Integer(index), markers);
			}
			markers.add(marker);
		}
		marker.addChangeListener(this);
		if (notify) {
			fireChangeEvent();
		}
	}
}