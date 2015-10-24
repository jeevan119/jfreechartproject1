package org.jfree.chart.axis;


import java.util.List;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import org.jfree.ui.RectangleEdge;
import java.util.ArrayList;
import org.jfree.ui.RectangleInsets;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import org.jfree.chart.plot.PlotRenderingInfo;

public abstract class IntermediateIntermediateValueAxis extends IntermediateValueAxis {
	public IntermediateIntermediateValueAxis(String string, TickUnitSource tickUnitSource) {
		super(string, tickUnitSource);
	}

	/**
	* Calculates the positions of the tick labels for the axis, storing the results in the tick label list (ready for drawing).
	* @param g2   the graphics device.
	* @param state   the axis state.
	* @param dataArea   the area in which the plot should be drawn.
	* @param edge   the location of the axis.
	* @return  A list of ticks.
	*/
	@Override
	public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {
		List result = new java.util.ArrayList();
		if (RectangleEdge.isTopOrBottom(edge)) {
			result = refreshTicksHorizontal(g2, dataArea, edge);
		} else if (RectangleEdge.isLeftOrRight(edge)) {
			result = refreshTicksVertical(g2, dataArea, edge);
		}
		return result;
	}

	protected abstract List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge);

	/**
	* Selects an appropriate tick value for the axis.  The strategy is to display as many ticks as possible (selected from an array of 'standard' tick units) without the labels overlapping.
	* @param g2   the graphics device.
	* @param dataArea   the area defined by the axes.
	* @param edge   the axis location.
	* @since  1.0.7
	*/
	protected void selectAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge) {
		if (RectangleEdge.isTopOrBottom(edge)) {
			selectHorizontalAutoTickUnit(g2, dataArea, edge);
		} else if (RectangleEdge.isLeftOrRight(edge)) {
			selectVerticalAutoTickUnit(g2, dataArea, edge);
		}
	}

	protected abstract void selectHorizontalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge);

	public abstract NumberTickUnit getTickUnit();

	/**
	* Estimates the maximum width of the tick labels, assuming the specified tick unit is used. <P> Rather than computing the string bounds of every tick on the axis, we just look at two values: the lower bound and the upper bound for the axis.  These two values will usually be representative.
	* @param g2   the graphics device.
	* @param unit   the tick unit to use for calculation.
	* @return  The estimated maximum width of the tick labels.
	* @since  1.0.7
	*/
	protected double estimateMaximumTickLabelWidth(Graphics2D g2, TickUnit unit) {
		return estimateMaximumTickLabelWidthExtracted(g2, unit);
	}

	public abstract void setTickUnit(NumberTickUnit unit, boolean notify, boolean turnOffAutoSelect);

	protected abstract void selectVerticalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge);

	/**
	* Estimates the maximum tick label height.
	* @param g2   the graphics device.
	* @return  The maximum height.
	* @since  1.0.7
	*/
	protected double estimateMaximumTickLabelHeight(Graphics2D g2) {
		RectangleInsets tickLabelInsets = getTickLabelInsets();
		double result = tickLabelInsets.getTop() + tickLabelInsets.getBottom();
		Font tickLabelFont = getTickLabelFont();
		FontRenderContext frc = g2.getFontRenderContext();
		result += tickLabelFont.getLineMetrics("123", frc).getHeight();
		return result;
	}

	protected abstract List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge);

	protected AxisState drawExtracted(double cursor, Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge,
			Rectangle2D plotArea, PlotRenderingInfo plotState) {
		AxisState state;
		if (!isVisible()) {
			state = new AxisState(cursor);
			List ticks = refreshTicks(g2, state, dataArea, edge);
			state.setTicks(ticks);
			return state;
		}
		state = drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
		if (getAttributedLabel() != null) {
			state = drawAttributedLabel(getAttributedLabel(), g2, plotArea, dataArea, edge, state);
		} else {
			state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
		}
		createAndAddEntity(cursor, state, dataArea, edge, plotState);
		return state;
	}
}