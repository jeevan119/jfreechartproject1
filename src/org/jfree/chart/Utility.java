package org.jfree.chart;


import java.io.PrintWriter;
import org.jfree.chart.imagemap.ToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.OverLIBToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.StandardToolTipTagFragmentGenerator;
import org.jfree.chart.imagemap.ImageMapUtilities;
import org.jfree.chart.imagemap.StandardURLTagFragmentGenerator;
import java.io.IOException;

public class Utility {
	public static void writeImageMapExtracted(boolean useOverLibForToolTips, PrintWriter writer, String name,
			ChartRenderingInfo info) throws IOException {
		ToolTipTagFragmentGenerator toolTipTagFragmentGenerator;
		if (useOverLibForToolTips) {
			toolTipTagFragmentGenerator = new OverLIBToolTipTagFragmentGenerator();
		} else {
			toolTipTagFragmentGenerator = new StandardToolTipTagFragmentGenerator();
		}
		ImageMapUtilities.writeImageMap(writer, name, info, toolTipTagFragmentGenerator,
				new StandardURLTagFragmentGenerator());
	}
}