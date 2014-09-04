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
 * @Title: DefaultActionSupport.java 
 * @Package binder.action.support.swing 
 * @Description: TODO
 * @author dailey dai  
 * @date 2012-2-29
 * @version V1.0   
 */
package binder.support.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

import binder.action.support.AbstractActionSupport;

/**
 * @author dailey dai
 * 
 */
public class SwingDefaultActionSupport extends AbstractActionSupport implements ActionListener {

	public SwingDefaultActionSupport(Object actionComponent) {
		super(actionComponent, SwingActionEvent.DEFAULT_ACTION);
	}

	@Override
	protected void registerActionListener(Object component) {
		if (component instanceof AbstractButton) {
			AbstractButton button = ((AbstractButton) component);
			button.addActionListener(this);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		setChanged();
		notifyObservers(e);
	}
}
