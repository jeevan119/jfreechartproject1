package org.jfree.chart.plot;


import org.jfree.util.ObjectList;
import org.jfree.ui.RectangleEdge;
import java.awt.geom.Point2D;
import org.jfree.chart.axis.ValueAxis;

public abstract class IntermediateIntermediatePlot extends IntermediatePlot {
	public IntermediateIntermediatePlot() {
		super();
	}

	public abstract RectangleEdge getRangeAxisEdge();

	protected void zoomRangeAxesExtracted(boolean useAnchor, Point2D source, PlotRenderingInfo info, double factor,
			ObjectList thisRangeAxes, PlotOrientation thisOrientation) {
		for (int i = 0; i < thisRangeAxes.size(); i++) {
			ValueAxis rangeAxis = (ValueAxis) thisRangeAxes.get(i);
			if (rangeAxis != null) {
				if (useAnchor) {
					double sourceY = source.getY();
					if (thisOrientation == PlotOrientation.HORIZONTAL) {
						sourceY = source.getX();
					}
					double anchorY = rangeAxis.java2DToValue(sourceY, info.getDataArea(), getRangeAxisEdge());
					rangeAxis.resizeRange2(factor, anchorY);
				} else {
					rangeAxis.resizeRange(factor);
				}
			}
		}
	}
}