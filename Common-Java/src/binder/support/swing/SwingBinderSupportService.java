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
 * @Title: SwingBinderSupportService.java 
 * @Package binder.swing 
 * @Description: TODO
 * @author dailey_dai  
 * @date Mar 21, 2012
 * @version V1.0   
 */
package binder.support.swing;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import binder.action.support.ActionSupport;
import binder.action.support.ActionSupportEvent;
import binder.component.support.ComponentSupport;
import binder.support.BinderSupportService;
import binder.support.swing.action.SwingActionEvent;
import binder.support.swing.component.JCheckBoxComponentSupport;
import binder.support.swing.component.JComboBoxComponentSupport;
import binder.support.swing.component.JRadioButtonComponentSupport;
import binder.support.swing.component.JTextAreaComponentSupport;
import binder.support.swing.component.JTextFieldComponentSupport;

/**
 * @author dailey_dai
 * 
 */
public class SwingBinderSupportService implements BinderSupportService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see binder.BinderSupportService#createComponentSupport(java.lang.Object)
	 */
	@Override
	public ComponentSupport createComponentSupport(Object component) {
		ComponentSupport componentSupport = null;
		if (component instanceof JTextField) {
			componentSupport = new JTextFieldComponentSupport((JTextField) component);
		} else if (component instanceof JTextArea) {
			componentSupport = new JTextAreaComponentSupport((JTextArea) component);
		} else {
			throw new IllegalArgumentException("Not support the singled component.");
		}
		return componentSupport;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * binder.BinderSupportService#createListableComponentSupport(java.lang.
	 * Object, java.lang.Object[])
	 */
	@Override
	public ComponentSupport createListableComponentSupport(Object component, Object[] optionals) {
		ComponentSupport componentSupport = null;
		if (component instanceof JComboBox) {
			componentSupport = new JComboBoxComponentSupport((JComboBox) component, optionals);
		} else {
			throw new IllegalArgumentException("Not support the listable component.");
		}
		return componentSupport;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * binder.BinderSupportService#createGroupableComponentSupport(java.lang
	 * .Object[], java.lang.Object[])
	 */
	@Override
	public ComponentSupport createGroupableComponentSupport(Object[] components, Object[] values) {
		ComponentSupport componentSupport = null;
		if (components[0] instanceof JCheckBox) {
			componentSupport = new JCheckBoxComponentSupport((JCheckBox[]) components, values);
		} else if (components[0] instanceof JRadioButton) {
			componentSupport = new JRadioButtonComponentSupport((JRadioButton[]) components, values);
		} else {
			throw new IllegalArgumentException("Not support the groupable component.");
		}
		return componentSupport;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see binder.BinderSupportService#createActionSupport(java.lang.Object,
	 * binder.action.support.ActionSupportEvent)
	 */
	@Override
	public ActionSupport createActionSupport(Object component, ActionSupportEvent event) {
		ActionSupport actionSupport = null;
		if (event instanceof SwingActionEvent) {
			actionSupport = ((SwingActionEvent) event).getActionSupport(component);
		} else {
			actionSupport = SwingActionEvent.DEFAULT_ACTION.getActionSupport(component);
		}
		return actionSupport;
	}

}
