package org.jfree.chart.renderer.xy;


import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import org.jfree.ui.RectangleEdge;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Color;

public abstract class IntermediateXYBarPainter {
	/**
	* Creates a shadow for the bar.
	* @param bar   the bar shape.
	* @param xOffset   the x-offset for the shadow.
	* @param yOffset   the y-offset for the shadow.
	* @param base   the edge that is the base of the bar.
	* @param pegShadow   peg the shadow to the base?
	* @return  A rectangle for the shadow.
	*/
	protected Rectangle2D createShadow(RectangularShape bar, double xOffset, double yOffset, RectangleEdge base,
			boolean pegShadow) {
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

	protected void paintBarShadowExtracted(XYBarRenderer renderer, int row, int column, RectangularShape bar,
			RectangleEdge base, boolean pegShadow, Graphics2D g2) {
		Paint itemPaint = renderer.getItemPaint(row, column);
		if (itemPaint instanceof Color) {
			Color c = (Color) itemPaint;
			if (c.getAlpha() == 0) {
				return;
			}
		}
		RectangularShape shadow = createShadow(bar, renderer.getShadowXOffset(), renderer.getShadowYOffset(), base,
				pegShadow);
		g2.setPaint(Color.gray);
		g2.fill(shadow);
	}
}