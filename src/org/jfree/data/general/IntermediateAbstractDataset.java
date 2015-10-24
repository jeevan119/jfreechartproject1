package org.jfree.data.general;


public abstract class IntermediateAbstractDataset extends AbstractDataset {
	public IntermediateAbstractDataset() {
		super();
	}

	public abstract int getItemCount();

	public abstract Comparable getKey(int index);

	public abstract Number getValue(int item);

	protected boolean equalsExtracted(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof PieDataset)) {
			return false;
		}
		PieDataset that = (PieDataset) obj;
		int count = getItemCount();
		if (that.getItemCount() != count) {
			return false;
		}
		for (int i = 0; i < count; i++) {
			Comparable k1 = getKey(i);
			Comparable k2 = that.getKey(i);
			if (!k1.equals(k2)) {
				return false;
			}
			Number v1 = getValue(i);
			Number v2 = that.getValue(i);
			if (v1 == null) {
				if (v2 != null) {
					return false;
				}
			} else {
				if (!v1.equals(v2)) {
					return false;
				}
			}
		}
		return true;
	}
}