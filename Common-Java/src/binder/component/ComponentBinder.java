package binder.component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import binder.component.support.ComponentSupport;
import binder.support.SupportUtilities;

public class ComponentBinder implements Observer {
	private Object bean = null;
	private PropertyDescriptor propertyDescriptor = null;
	private ComponentSupport componentSupport = null;

	public ComponentBinder(Object bean, PropertyDescriptor propertyDescriptor, ComponentSupport componentSupport) {
		this.bean = bean;
		this.propertyDescriptor = propertyDescriptor;
		this.componentSupport = componentSupport;
		componentSupport.addObserver(this);
		loadProperty();
	}

	/**
	 * initial the control value from bind bean's property 
	 */
	public void loadProperty() {
		Method method = propertyDescriptor.getReadMethod();
		try {
			Object value = method.invoke(bean);
			loadProperty(value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void loadProperty(final Object value) {
		if (!SupportUtilities.equals(componentSupport.getComponentValue(), value)) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					componentSupport.setComponentValue(value);
				}
			});
		}

	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public PropertyDescriptor getPropertyDescriptor() {
		return propertyDescriptor;
	}

	public void setPropertyDescriptor(PropertyDescriptor propertyDescriptor) {
		this.propertyDescriptor = propertyDescriptor;
	}

	public ComponentSupport getComponentSupport() {
		return componentSupport;
	}

	public void setComponentSupport(ComponentSupport componentSupport) {
		this.componentSupport = componentSupport;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		Method method = propertyDescriptor.getReadMethod();
		Object oldValue = null;
		try {
			oldValue = method.invoke(bean);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		if (oldValue == arg || (oldValue != null && oldValue.equals(arg)))
			return;
		updateModel(arg);
	}

	protected void updateModel(final Object value) {
		Method method = propertyDescriptor.getWriteMethod();
		try {
			method.invoke(bean, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
