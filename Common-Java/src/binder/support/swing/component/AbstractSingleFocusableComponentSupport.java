package binder.support.swing.component;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import binder.component.support.SingleableComponentSupport;

public abstract class AbstractSingleFocusableComponentSupport<T extends Component> extends
		SingleableComponentSupport<T> implements FocusListener {

	public AbstractSingleFocusableComponentSupport(T component) {
		super(component);
		register(component);
	}

	@Override
	public void focusGained(FocusEvent e) {
	}

	@Override
	public void focusLost(FocusEvent e) {
		updateComponentModel();
	}

	public void register(T component) {
		component.addFocusListener(this);
	}

	@Override
	public void requestFocusComponent() {
		Component component = getComponent();
		if (component != null) {
			component.requestFocus();
		}
	}
}