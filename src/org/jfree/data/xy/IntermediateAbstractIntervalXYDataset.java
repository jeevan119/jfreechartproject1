package org.jfree.data.xy;


import java.beans.PropertyChangeEvent;
import org.jfree.data.general.Series;
import java.beans.PropertyVetoException;

public abstract class IntermediateAbstractIntervalXYDataset extends AbstractIntervalXYDataset {
	public IntermediateAbstractIntervalXYDataset() {
		super();
	}

	public abstract int getSeriesIndex(Comparable key);

	public abstract int getSeriesCount();

	protected void vetoableChangeExtracted(PropertyChangeEvent e) throws PropertyVetoException, IllegalStateException {
		if (!"Key".equals(e.getPropertyName())) {
			return;
		}
		Series s = (Series) e.getSource();
		if (getSeriesIndex(s.getKey()) == -1) {
			throw new IllegalStateException(
					"Receiving events from a series " + "that does not belong to this collection.");
		}
		Comparable key = (Comparable) e.getNewValue();
		if (getSeriesIndex(key) >= 0) {
			throw new PropertyVetoException("Duplicate key2", e);
		}
	}
}