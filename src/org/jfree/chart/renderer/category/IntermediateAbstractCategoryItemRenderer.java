package org.jfree.chart.renderer.category;


import org.jfree.chart.LegendItem;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.Stroke;

public abstract class IntermediateAbstractCategoryItemRenderer extends AbstractCategoryItemRenderer {
	public IntermediateAbstractCategoryItemRenderer() {
		super();
	}

	protected LegendItem getLegendItemExtracted(int series, int datasetIndex) {
		CategoryPlot cp = getPlot();
		if (cp == null) {
			return null;
		}
		if (!isSeriesVisible(series) || !isSeriesVisibleInLegend(series)) {
			return null;
		}
		CategoryDataset dataset = cp.getDataset(datasetIndex);
		String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
		String description = label;
		String toolTipText = null;
		if (getLegendItemToolTipGenerator() != null) {
			toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
		}
		String urlText = null;
		if (getLegendItemURLGenerator() != null) {
			urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
		}
		Shape shape = lookupLegendShape(series);
		Paint paint = lookupSeriesPaint(series);
		Paint outlinePaint = lookupSeriesOutlinePaint(series);
		Stroke outlineStroke = lookupSeriesOutlineStroke(series);
		LegendItem result = new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke,
				outlinePaint);
		result.setLabelFont(lookupLegendTextFont(series));
		Paint labelPaint = lookupLegendTextPaint(series);
		if (labelPaint != null) {
			result.setLabelPaint(labelPaint);
		}
		result.setDataset(dataset);
		result.setDatasetIndex(datasetIndex);
		result.setSeriesKey(dataset.getRowKey(series));
		result.setSeriesIndex(series);
		return result;
	}
}