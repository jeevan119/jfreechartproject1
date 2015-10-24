package org.jfree.chart.axis;


import java.text.NumberFormat;
import java.awt.Graphics2D;
import org.jfree.ui.RectangleInsets;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.FontMetrics;
import org.jfree.data.Range;

public abstract class IntermediateValueAxis extends ValueAxis {
	public IntermediateValueAxis(String string, TickUnitSource tickUnitSource) {
		super(string, tickUnitSource);
	}

	public abstract NumberFormat getNumberFormatOverride();

	protected double estimateMaximumTickLabelWidthExtracted(Graphics2D g2, TickUnit unit) {
		RectangleInsets tickLabelInsets = getTickLabelInsets();
		double result = tickLabelInsets.getLeft() + tickLabelInsets.getRight();
		if (isVerticalTickLabels()) {
			FontRenderContext frc = g2.getFontRenderContext();
			LineMetrics lm = getTickLabelFont().getLineMetrics("0", frc);
			result += lm.getHeight();
		} else {
			FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
			Range range = getRange();
			double lower = range.getLowerBound();
			double upper = range.getUpperBound();
			String lowerStr, upperStr;
			NumberFormat formatter = getNumberFormatOverride();
			if (formatter != null) {
				lowerStr = formatter.format(lower);
				upperStr = formatter.format(upper);
			} else {
				lowerStr = unit.valueToString(lower);
				upperStr = unit.valueToString(upper);
			}
			double w1 = fm.stringWidth(lowerStr);
			double w2 = fm.stringWidth(upperStr);
			result += Math.max(w1, w2);
		}
		return result;
	}
}