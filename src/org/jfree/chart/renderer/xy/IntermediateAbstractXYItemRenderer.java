package org.jfree.chart.renderer.xy;


import java.awt.Shape;
import org.jfree.chart.LegendItem;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.labels.XYSeriesLabelGenerator;
import java.awt.Paint;

public abstract class IntermediateAbstractXYItemRenderer extends AbstractXYItemRenderer {
	public IntermediateAbstractXYItemRenderer() {
		super();
	}

	/**
	* The shape used to represent an area in each legend item (this should never be <code>null</code>).
	*/
	protected transient Shape legendArea;

	protected LegendItem getLegendItemExtracted(int datasetIndex, int series) {
		LegendItem result = null;
		XYPlot xyplot = getPlot();
		if (xyplot != null) {
			XYDataset dataset = xyplot.getDataset(datasetIndex);
			if (dataset != null) {
				XYSeriesLabelGenerator lg = getLegendItemLabelGenerator();
				String label = lg.generateLabel(dataset, series);
				String description = label;
				String toolTipText = null;
				if (getLegendItemToolTipGenerator() != null) {
					toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
				}
				String urlText = null;
				if (getLegendItemURLGenerator() != null) {
					urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
				}
				Paint paint = lookupSeriesPaint(series);
				result = new LegendItem(label, description, toolTipText, urlText, this.legendArea, paint);
				result.setLabelFont(lookupLegendTextFont(series));
				Paint labelPaint = lookupLegendTextPaint(series);
				if (labelPaint != null) {
					result.setLabelPaint(labelPaint);
				}
				result.setDataset(dataset);
				result.setDatasetIndex(datasetIndex);
				result.setSeriesKey(dataset.getSeriesKey(series));
				result.setSeriesIndex(series);
			}
		}
		return result;
	}
}