package binder.support;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

public class AbstractPropertySupportted implements PropertyChangeSupportted, VetoableChangeSupportted {

	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

	public AbstractPropertySupportted() {
		super();
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	@Override
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	@Override
	public void addVetoableChangeListener(String propertyName, VetoableChangeListener listener) {
		vetoableChangeSupport.addVetoableChangeListener(propertyName, listener);
	}

	@Override
	public void addVetoableChangeListener(VetoableChangeListener listener) {
		vetoableChangeSupport.addVetoableChangeListener(listener);
	}

	protected void firePropertyChange(String property, boolean oldVal, boolean newVal) {
		propertyChangeSupport.firePropertyChange(property, oldVal, newVal);
	}

	protected void firePropertyChange(String property, int oldVal, int newVal) {
		propertyChangeSupport.firePropertyChange(property, oldVal, newVal);
	}

	protected void firePropertyChange(String property, Object oldVal, Object newVal) {
		propertyChangeSupport.firePropertyChange(property, oldVal, newVal);
	}

	protected void fireVetoableChange(String property, boolean oldVal, boolean newVal) throws PropertyVetoException {
		vetoableChangeSupport.fireVetoableChange(property, oldVal, newVal);
	}

	protected void fireVetoableChange(String property, int oldVal, int newVal) throws PropertyVetoException {
		vetoableChangeSupport.fireVetoableChange(property, oldVal, newVal);
	}

	protected void fireVetoableChange(String property, Object oldVal, Object newVal) throws PropertyVetoException {
		vetoableChangeSupport.fireVetoableChange(property, oldVal, newVal);
	}

	@Override
	public PropertyChangeListener[] getPropertyChangeListeners() {
		return propertyChangeSupport.getPropertyChangeListeners();
	}

	@Override
	public PropertyChangeListener[] getPropertyChangeListeners(String propertyName) {
		return propertyChangeSupport.getPropertyChangeListeners(propertyName);
	}

	@Override
	public VetoableChangeListener[] getVetoableChangeListeners() {
		return vetoableChangeSupport.getVetoableChangeListeners();
	}

	@Override
	public VetoableChangeListener[] getVetoableChangeListeners(String propertyName) {
		return vetoableChangeSupport.getVetoableChangeListeners(propertyName);
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	@Override
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
	}

	@Override
	public void removeVetoableChangeListener(String propertyName, VetoableChangeListener listener) {
		vetoableChangeSupport.removeVetoableChangeListener(propertyName, listener);
	}

	@Override
	public void removeVetoableChangeListener(VetoableChangeListener listener) {
		vetoableChangeSupport.removeVetoableChangeListener(listener);
	}

}