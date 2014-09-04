/**   
 *  Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * @Title: JCheckBoxComponentSupport.java 
 * @Package binder.object.support.swing 
 * @Description: TODO
 * @author dailey  
 * @date 2012-3-18
 * @version V1.0   
 */
package binder.support.swing.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;

import binder.support.SupportUtilities;

/**
 * @author dailey
 * @param <T> JCheckBox
 * 
 */
public class JCheckBoxComponentSupport extends AbstractGroupFocusableComponentSupport<JCheckBox> implements
		ActionListener {

	/**
	 * @param components
	 * @param values
	 */
	public JCheckBoxComponentSupport(JCheckBox[] components, Object[] values) {
		super(components, values);
		init();
	}

	/**
	 * @param components
	 * @param values
	 */
	private void init() {
		for (int i = 0; i < components.length; i++) {
			components[i].setText(SupportUtilities.convertToString(values[i]));
			components[i].addActionListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * binder.object.support.ComponentSupport#setComponentValue(java.lang
	 * .Object)
	 */
	@Override
	public void setComponentValue(Object object) {
		if (object != null && object.getClass().isArray()) {
			Object[] newVals = (Object[]) object;
			for (int i = 0; i < components.length; i++) {
				components[i].setSelected(false);
				Object val = values[i];
				for (int j = 0; j < newVals.length; j++) {
					if (val.equals(newVals[j])) {
						components[i].setSelected(true);
						break;
					}
				}
			}

		} else if (object instanceof Boolean) {
			for (int i = 0; i < components.length; i++) {
				components[i].setSelected((Boolean) object);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see binder.object.support.ComponentSupport#getComponentValue()
	 */
	@Override
	public Object getComponentValue() {
		List<Object> selectedValues = new ArrayList<Object>();
		for (int i = 0; i < components.length; i++) {
			if (components[i].isSelected()) {
				selectedValues.add(values[i]);
			}
		}
		return selectedValues.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see binder.object.support.ComponentSupport#requestFocusComponent()
	 */
	@Override
	public void requestFocusComponent() {
		for (int i = 0; i < components.length; i++) {
			if (components[i].isSelected()) {
				components[i].requestFocus();
				break;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		updateComponentModel();
	}

	@Override
	public void focusLost(FocusEvent e) {
		// Nothing to do
	}

}
