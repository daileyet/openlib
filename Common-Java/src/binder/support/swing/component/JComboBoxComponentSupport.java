package binder.support.swing.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;

import javax.swing.JComboBox;

@SuppressWarnings("rawtypes")
public class JComboBoxComponentSupport extends AbstractListFocusableComponentSupport<JComboBox> implements
		ActionListener {

	public JComboBoxComponentSupport(JComboBox component, Object[] options) {
		super(component, options);
		init(getOptions());
	}

	@SuppressWarnings("unchecked")
	private void init(Object[] optionals) {
		if (optionals != null) {
			for (Object object : optionals) {
				getComponent().addItem(object);
			}
		}
		getComponent().addActionListener(this);
	}

	@Override
	public void setComponentValue(Object object) {
		getComponent().setSelectedItem(object);
	}

	@Override
	public Object getComponentValue() {
		return getComponent().getSelectedItem();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.updateComponentModel();
	}

	@Override
	public void focusLost(FocusEvent e) {
		// Nothing to do
	}
}
