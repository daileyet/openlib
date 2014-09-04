package binder.support;

import java.beans.PropertyChangeListener;

public interface PropertyChangeSupportted {

	public void addPropertyChangeListener(PropertyChangeListener listener);

	public void removePropertyChangeListener(PropertyChangeListener listener);

	public PropertyChangeListener[] getPropertyChangeListeners();

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

	public PropertyChangeListener[] getPropertyChangeListeners(String propertyName);

}