package binder.support.swing.component;

import javax.swing.JTextField;

import binder.support.SupportUtilities;

public class JTextFieldComponentSupport extends AbstractSingleFocusableComponentSupport<JTextField> {
	public JTextFieldComponentSupport(JTextField component) {
		super(component);
	}

	@Override
	public void setComponentValue(Object object) {
		getComponent().setText(SupportUtilities.convertToString(object));
	}

	@Override
	public Object getComponentValue() {
		return getComponent().getText();
	}
}
