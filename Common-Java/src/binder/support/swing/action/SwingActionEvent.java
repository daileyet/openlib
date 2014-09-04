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
 * @Title: SwingActionType.java 
 * @Package binder.action.support 
 * @Description: TODO
 * @author dailey dai  
 * @date 2012-2-29
 * @version V1.0   
 */
package binder.support.swing.action;

import binder.action.support.ActionSupport;
import binder.action.support.ActionSupportEvent;

/**
 * @author dailey dai
 * 
 */
public enum SwingActionEvent implements ActionSupportEvent {
	MOUSE_CLICK_ACTION {
		/* (non-Javadoc)
		 * @see binder.action.support.swing.SwingActionEvent#getActionSupport(java.lang.Object)
		 */
		@Override
		public ActionSupport getActionSupport(Object component) {
			return new SwingMouseClickActionSupport(component);
		}
	},
	MOUSE_DOUBLE_CLICK_ACTION {
		/* (non-Javadoc)
		 * @see binder.action.support.swing.SwingActionEvent#getActionSupport(java.lang.Object)
		 */
		@Override
		public ActionSupport getActionSupport(Object component) {
			return new SwingMouseDoubleClickActionSupport(component);
		}
	},
	MOUSE_RIGHT_CLICK_ACTION {
		/* (non-Javadoc)
		 * @see binder.action.support.swing.SwingActionEvent#getActionSupport(java.lang.Object)
		 */
		@Override
		public ActionSupport getActionSupport(Object component) {
			return new SwingMouseRightClickActionSupport(component);
		}
	},
	DEFAULT_ACTION;

	@Override
	public ActionSupport getActionSupport(Object component) {
		return new SwingDefaultActionSupport(component);
	}
}
