package org.jfree.chart.renderer.category;


import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import org.jfree.ui.RectangleEdge;

public class Utility {
	public static Rectangle2D.Double createShadowExtracted(RectangularShape bar, RectangleEdge base, double xOffset,
			boolean pegShadow, double yOffset) {
		double x0 = bar.getMinX();
		double x1 = bar.getMaxX();
		double y0 = bar.getMinY();
		double y1 = bar.getMaxY();
		if (base == RectangleEdge.TOP) {
			x0 += xOffset;
			x1 += xOffset;
			if (!pegShadow) {
				y0 += yOffset;
			}
			y1 += yOffset;
		} else if (base == RectangleEdge.BOTTOM) {
			x0 += xOffset;
			x1 += xOffset;
			y0 += yOffset;
			if (!pegShadow) {
				y1 += yOffset;
			}
		} else if (base == RectangleEdge.LEFT) {
			if (!pegShadow) {
				x0 += xOffset;
			}
			x1 += xOffset;
			y0 += yOffset;
			y1 += yOffset;
		} else if (base == RectangleEdge.RIGHT) {
			x0 += xOffset;
			if (!pegShadow) {
				x1 += xOffset;
			}
			y0 += yOffset;
			y1 += yOffset;
		}
		return new Rectangle2D.Double(x0, y0, (x1 - x0), (y1 - y0));
	}
}