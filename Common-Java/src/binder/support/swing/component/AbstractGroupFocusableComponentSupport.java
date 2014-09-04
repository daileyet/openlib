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
* @Title: AbstractGroupFocusableComponentSupport.java 
* @Package binder.object.support.swing 
* @Description: TODO
* @author dailey  
* @date 2012-3-18
* @version V1.0   
*/
package binder.support.swing.component;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import binder.component.support.GroupableComponentSupport;

/**
 * @author dailey
 *
 */
public abstract class AbstractGroupFocusableComponentSupport<T extends Component> extends GroupableComponentSupport<T>
		implements FocusListener {

	public AbstractGroupFocusableComponentSupport(T[] components, Object[] values) {
		super(components, values);
		register(components);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent e) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent e) {
		updateComponentModel();
	}

	public void register(T[] components) {
		for (T component : components) {
			component.addFocusListener(this);
		}
	}

}
