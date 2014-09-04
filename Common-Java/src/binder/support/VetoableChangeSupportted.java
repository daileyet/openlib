package binder.support;

import java.beans.VetoableChangeListener;

public interface VetoableChangeSupportted {
	public void addVetoableChangeListener(VetoableChangeListener listener);

	public void removeVetoableChangeListener(VetoableChangeListener listener);

	public void addVetoableChangeListener(String propertyName, VetoableChangeListener listener);

	public VetoableChangeListener[] getVetoableChangeListeners();

	public void removeVetoableChangeListener(String propertyName, VetoableChangeListener listener);

	public VetoableChangeListener[] getVetoableChangeListeners(String propertyName);
}
