/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2013, by Object Refinery Limited and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * -----------------
 * PiePlotState.java
 * -----------------
 * (C) Copyright 2004-2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 06-Mar-2004 : Version 1 (DG);
 *
 */

package org.jfree.chart.plot;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Arc2D;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.renderer.RendererState;
import org.jfree.data.general.PieDataset;
import org.jfree.text.TextBox;
import org.jfree.util.Rotation;

/**
 * A renderer state.
 */
public class PiePlotState extends RendererState {

    /** The number of passes required by the renderer. */
    private int passesRequired;

    /** The total of the values in the dataset. */
    private double total;

    /** The latest angle. */
    private double latestAngle;

    /** The exploded pie area. */
    private Rectangle2D explodedPieArea;

    /** The pie area. */
    private Rectangle2D pieArea;

    /** The center of the pie in Java 2D coordinates. */
    private double pieCenterX;

    /** The center of the pie in Java 2D coordinates. */
    private double pieCenterY;

    /** The vertical pie radius. */
    private double pieHRadius;

    /** The horizontal pie radius. */
    private double pieWRadius;

    /** The link area. */
    private Rectangle2D linkArea;

    /**
     * Creates a new object for recording temporary state information for a
     * renderer.
     *
     * @param info  the plot rendering info.
     */
    public PiePlotState(PlotRenderingInfo info) {
        super(info);
        this.passesRequired = 1;
        this.total = 0.0;
    }

    /**
     * Returns the number of passes required by the renderer.
     *
     * @return The number of passes.
     */
    public int getPassesRequired() {
        return this.passesRequired;
    }

    /**
     * Sets the number of passes required by the renderer.
     *
     * @param passes  the passes.
     */
    public void setPassesRequired(int passes) {
        this.passesRequired = passes;
    }

    /**
     * Returns the total of the values in the dataset.
     *
     * @return The total.
     */
    public double getTotal() {
        return this.total;
    }

    /**
     * Sets the total.
     *
     * @param total  the total.
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * Returns the latest angle.
     *
     * @return The latest angle.
     */
    public double getLatestAngle() {
        return this.latestAngle;
    }

    /**
     * Sets the latest angle.
     *
     * @param angle  the angle.
     */
    public void setLatestAngle(double angle) {
        this.latestAngle = angle;
    }

    /**
     * Returns the pie area.
     *
     * @return The pie area.
     */
    public Rectangle2D getPieArea() {
        return this.pieArea;
    }

    /**
     * Sets the pie area.
     *
     * @param area  the area.
     */
    public void setPieArea(Rectangle2D area) {
       this.pieArea = area;
    }

    /**
     * Returns the exploded pie area.
     *
     * @return The exploded pie area.
     */
    public Rectangle2D getExplodedPieArea() {
        return this.explodedPieArea;
    }

    /**
     * Sets the exploded pie area.
     *
     * @param area  the area.
     */
    public void setExplodedPieArea(Rectangle2D area) {
        this.explodedPieArea = area;
    }

    /**
     * Returns the x-coordinate of the center of the pie chart.
     *
     * @return The x-coordinate (in Java2D space).
     */
    public double getPieCenterX() {
        return this.pieCenterX;
    }

    /**
     * Sets the x-coordinate of the center of the pie chart.
     *
     * @param x  the x-coordinate (in Java2D space).
     */
    public void setPieCenterX(double x) {
        this.pieCenterX = x;
    }

    /**
     * Returns the y-coordinate (in Java2D space) of the center of the pie
     * chart.  For the {@link PiePlot3D} class, we derive this from the top of
     * the pie.
     *
     * @return The y-coordinate (in Java2D space).
     */
    public double getPieCenterY() {
        return this.pieCenterY;
    }

    /**
     * Sets the y-coordinate of the center of the pie chart.  This method is
     * used by the plot and typically is not called directly by applications.
     *
     * @param y  the y-coordinate (in Java2D space).
     */
    public void setPieCenterY(double y) {
        this.pieCenterY = y;
    }

    /**
     * Returns the link area.  This defines the "dog-leg" point for the label
     * linking lines.
     *
     * @return The link area.
     */
    public Rectangle2D getLinkArea() {
        return this.linkArea;
    }

    /**
     * Sets the label link area.  This defines the "dog-leg" point for the
     * label linking lines.
     *
     * @param area  the area.
     */
    public void setLinkArea(Rectangle2D area) {
        this.linkArea = area;
    }

    /**
     * Returns the vertical pie radius.
     *
     * @return The radius.
     */
    public double getPieHRadius() {
        return this.pieHRadius;
    }

    /**
     * Sets the vertical pie radius.
     *
     * @param radius  the radius.
     */
    public void setPieHRadius(double radius) {
        this.pieHRadius = radius;
    }

    /**
     * Returns the horizontal pie radius.
     *
     * @return The radius.
     */
    public double getPieWRadius() {
        return this.pieWRadius;
    }

    /**
     * Sets the horizontal pie radius.
     *
     * @param radius  the radius.
     */
    public void setPieWRadius(double radius) {
        this.pieWRadius = radius;
    }

	/**
	 * Returns the center for the specified section. Checks to see if the section is exploded and recalculates the new center if so.
	 * @param key   section key.
	 * @param dataset
	 * @param direction
	 * @param piePlot
	 * @return  The center for the specified section.
	 * @since  1.0.14
	 */
	public Point2D getArcCenter(Comparable key, PieDataset dataset, Rotation direction, PiePlot piePlot) {
		Point2D center = new Point2D.Double(getPieCenterX(), getPieCenterY());
		double ep = piePlot.getExplodePercent(key);
		double mep = piePlot.getMaximumExplodePercent();
		if (mep > 0.0) {
			ep = ep / mep;
		}
		if (ep != 0) {
			Rectangle2D pieArea = getPieArea();
			Rectangle2D expPieArea = getExplodedPieArea();
			double angle1, angle2;
			Number n = dataset.getValue(key);
			double value = n.doubleValue();
			if (direction == Rotation.CLOCKWISE) {
				angle1 = getLatestAngle();
				angle2 = angle1 - value / getTotal() * 360.0;
			} else if (direction == Rotation.ANTICLOCKWISE) {
				angle1 = getLatestAngle();
				angle2 = angle1 + value / getTotal() * 360.0;
			} else {
				throw new IllegalStateException("Rotation type not recognised.");
			}
			double angle = (angle2 - angle1);
			Arc2D arc1 = new Arc2D.Double(pieArea, angle1, angle / 2, Arc2D.OPEN);
			Point2D point1 = arc1.getEndPoint();
			Arc2D.Double arc2 = new Arc2D.Double(expPieArea, angle1, angle / 2, Arc2D.OPEN);
			Point2D point2 = arc2.getEndPoint();
			double deltaX = (point1.getX() - point2.getX()) * ep;
			double deltaY = (point1.getY() - point2.getY()) * ep;
			center = new Point2D.Double(getPieCenterX() - deltaX, getPieCenterY() - deltaY);
		}
		return center;
	}

	public TextBox drawLeftRightLabel(Graphics2D g2, PieLabelRecord record, double anchorX, double targetX,
			double targetY, boolean labelLinksVisible, Paint labelLinkPaint, Stroke labelLinkStroke,
			PieLabelLinkStyle labelLinkStyle) {
		if (labelLinksVisible) {
			double theta = record.getAngle();
			double linkX = getPieCenterX() + Math.cos(theta) * getPieWRadius() * record.getLinkPercent();
			double linkY = getPieCenterY() - Math.sin(theta) * getPieHRadius() * record.getLinkPercent();
			double elbowX = getPieCenterX() + Math.cos(theta) * getLinkArea().getWidth() / 2.0;
			double elbowY = getPieCenterY() - Math.sin(theta) * getLinkArea().getHeight() / 2.0;
			double anchorY = elbowY;
			g2.setPaint(labelLinkPaint);
			g2.setStroke(labelLinkStroke);
			PieLabelLinkStyle style = labelLinkStyle;
			if (style.equals(PieLabelLinkStyle.STANDARD)) {
				g2.draw(new Line2D.Double(linkX, linkY, elbowX, elbowY));
				g2.draw(new Line2D.Double(anchorX, anchorY, elbowX, elbowY));
				g2.draw(new Line2D.Double(anchorX, anchorY, targetX, targetY));
			} else if (style.equals(PieLabelLinkStyle.QUAD_CURVE)) {
				QuadCurve2D q = new QuadCurve2D.Float();
				q.setCurve(targetX, targetY, anchorX, anchorY, elbowX, elbowY);
				g2.draw(q);
				g2.draw(new Line2D.Double(elbowX, elbowY, linkX, linkY));
			} else if (style.equals(PieLabelLinkStyle.CUBIC_CURVE)) {
				CubicCurve2D c = new CubicCurve2D.Float();
				c.setCurve(targetX, targetY, anchorX, anchorY, elbowX, elbowY, linkX, linkY);
				g2.draw(c);
			}
		}
		TextBox tb = record.getLabel();
		return tb;
	}

}
