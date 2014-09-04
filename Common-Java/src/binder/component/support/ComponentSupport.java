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
 * @Title: ComponentSupport.java 
 * @Package com.rockwell.automotivesuite.smartGui.inner 
 * @Description: define the control value binder support
 * @author dailey dai  
 * @date 2012-2-27
 * @version V1.0   
 */
package binder.component.support;

import java.util.Observable;

/**
 * @author dailey dai
 * 
 */
public abstract class ComponentSupport extends Observable {
	public abstract void setComponentValue(Object object);

	public abstract Object getComponentValue();

	public abstract void requestFocusComponent();

	protected void updateComponentModel() {
		try {
			setChanged();
			notifyObservers(getComponentValue());
		} catch (Exception e) {
			requestFocusComponent();
		}
	}
}
