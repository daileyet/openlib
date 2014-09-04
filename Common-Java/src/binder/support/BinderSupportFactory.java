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
 * @Title: BinderSupportFactory.java 
 * @Package binder 
 * @Description: factory for binder support
 * @author dailey_dai  
 * @date Mar 21, 2012
 * @version V1.0   
 */
package binder.support;

import java.security.InvalidParameterException;

import binder.action.support.ActionSupport;
import binder.action.support.ActionSupportEvent;
import binder.component.support.ComponentSupport;

/**
 * @author dailey_dai
 * 
 */
public class BinderSupportFactory {
	private static BinderSupportService binderSupportService = null;

	public static ComponentSupport createComponentSupport(Object component) {
		return getBinderSupportService().createComponentSupport(component);
	}

	public static ComponentSupport createComponentSupport(Object component, Object[] optionals) {
		return getBinderSupportService().createListableComponentSupport(component, optionals);
	}

	public static ComponentSupport createComponentSupport(Object[] buttons, Object[] optionals) {
		if (buttons == null || optionals == null || buttons.length != optionals.length) {
			throw new InvalidParameterException();
		}
		return getBinderSupportService().createGroupableComponentSupport(buttons, optionals);
	}

	public static ActionSupport createActionSupport(Object component, ActionSupportEvent actionEvent) {
		ActionSupport actionSupport = getBinderSupportService().createActionSupport(component, actionEvent);
		return actionSupport;
	}

	public static void setBinderSupportService(String binderSupportProviderName) {
		binderSupportService = BinderSupportManager.newInstance(binderSupportProviderName);
	}

	private static BinderSupportService getBinderSupportService() {
		if (binderSupportService == null) {
			binderSupportService = BinderSupportManager.newInstance();
		}
		return binderSupportService;
	}

	private BinderSupportFactory() {
	}
}
