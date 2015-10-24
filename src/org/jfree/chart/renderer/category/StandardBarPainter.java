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
 * -----------------------
 * StandardBarPainter.java
 * -----------------------
 * (C) Copyright 2008, by Object Refinery Limited.
 *
 * Original Author:  David Gilbert (for Object Refinery Limited);
 * Contributor(s):   -;
 *
 * Changes:
 * --------
 * 19-Jun-2008 : Version 1 (DG);
 * 15-Aug-2008 : Use renderer's shadow paint (DG);
 *
 */

package org.jfree.chart.renderer.category;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

import org.jfree.ui.GradientPaintTransformer;
import org.jfree.ui.RectangleEdge;

/**
 * An implementation of the {@link BarPainter} interface that preserves the
 * behaviour of bar painting that existed prior to the introduction of the
 * {@link BarPainter} interface.
 *
 * @see GradientBarPainter
 *
 * @since 1.0.11
 */
public class StandardBarPainter extends IntermediateBarPainter implements BarPainter, Serializable {

    /**
     * Creates a new instance.
     */
    public StandardBarPainter() {
    }

    /**
     * Paints a single bar instance.
     *
     * @param g2  the graphics target.
     * @param renderer  the renderer.
     * @param row  the row index.
     * @param column  the column index.
     * @param bar  the bar
     * @param base  indicates which side of the rectangle is the base of the
     *              bar.
     */
    @Override
    public void paintBar(Graphics2D g2, BarRenderer renderer, int row,
            int column, RectangularShape bar, RectangleEdge base) {

        Paint itemPaint = renderer.getItemPaint(row, column);
        GradientPaintTransformer t = renderer.getGradientPaintTransformer();
        if (t != null && itemPaint instanceof GradientPaint) {
            itemPaint = t.transform((GradientPaint) itemPaint, bar);
        }
        g2.setPaint(itemPaint);
        g2.fill(bar);

        // draw the outline...
        if (renderer.isDrawBarOutline()) {
               // && state.getBarWidth() > BAR_OUTLINE_WIDTH_THRESHOLD) {
            Stroke stroke = renderer.getItemOutlineStroke(row, column);
            Paint paint = renderer.getItemOutlinePaint(row, column);
            if (stroke != null && paint != null) {
                g2.setStroke(stroke);
                g2.setPaint(paint);
                g2.draw(bar);
            }
        }

    }

    /**
     * Paints a single bar instance.
     *
     * @param g2  the graphics target.
     * @param renderer  the renderer.
     * @param row  the row index.
     * @param column  the column index.
     * @param bar  the bar
     * @param base  indicates which side of the rectangle is the base of the
     *              bar.
     * @param pegShadow  peg the shadow to the base of the bar?
     */
    @Override
    public void paintBarShadow(Graphics2D g2, BarRenderer renderer, int row,
            int column, RectangularShape bar, RectangleEdge base,
            boolean pegShadow) {

        paintBarShadowExtracted(renderer, row, column, bar, base, pegShadow, g2);

    }

    /**
     * Tests this instance for equality with an arbitrary object.
     *
     * @param obj  the obj (<code>null</code> permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardBarPainter)) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hash code for this instance.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        int hash = 37;
        // no fields to compute...
        return hash;
    }

}
