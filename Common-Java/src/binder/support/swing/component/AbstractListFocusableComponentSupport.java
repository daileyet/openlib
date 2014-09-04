package binder.support.swing.component;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import binder.component.support.ListableComponentSupport;

public abstract class AbstractListFocusableComponentSupport<T extends Component> extends ListableComponentSupport<T>
		implements FocusListener {

	public AbstractListFocusableComponentSupport(T component, Object[] options) {
		super(component, options);
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