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
 * @Title: ActionSupport.java 
 * @Package binder.action.support 
 * @Description: define the action for different control at different events
 * @author dailey dai  
 * @date 2012-2-29
 * @version V1.0   
 */
package binder.action.support;

import java.util.Observable;

/**
 * @author dailey dai
 * 
 */
public abstract class ActionSupport extends Observable {
	protected Object actionComponent;

	public ActionSupport(Object component) {
		this.actionComponent = component;
	}

	public Object getActionCompoenent() {
		return actionComponent;
	}

	/**
	 * 
	 * @return ActionSupportEvent action event which cause the action
	 */
	public abstract ActionSupportEvent getActionSupportEvent();

}
