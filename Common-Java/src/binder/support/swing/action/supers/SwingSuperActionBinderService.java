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
* @Title: SwingSuperActionBinderService.java 
* @Package binder.support.swing.action.supers 
* @Description: TODO
* @author dailey  
* @date 2012-3-22
* @version V1.0   
*/
package binder.support.swing.action.supers;

import java.awt.Component;
import java.beans.BeanDescriptor;
import java.lang.reflect.Method;

import binder.support.SuperActionBinderService;

/**
 * @author dailey
 *
 */
public class SwingSuperActionBinderService implements SuperActionBinderService {
	private static final String METHOD_PRE = "add";

	/* (non-Javadoc)
	 * @see binder.support.SuperActionBinderService#registerListener(java.lang.Object, java.lang.Class, java.lang.Object)
	 */
	@Override
	public <T> void registerListener(Object component, Class<T> listenerClass, Object listenerImpl) {
		if (component instanceof Component) {
			Method registerMethod = getRegisterListenerMethod(component.getClass(), listenerClass);
			try {
				registerMethod.invoke(component, listenerImpl);
			} catch (Exception e) {
				e.printStackTrace();
				throw new IllegalArgumentException("The component:[" + component.getClass()
						+ "] not support this type:[" + listenerClass + "] listener.");
			}
		}
	}

	private <T> Method getRegisterListenerMethod(Class<? extends Object> class1, Class<T> listenerClass) {
		Method registerMethod = null;
		String registerMethodName = null;
		BeanDescriptor descriptor = new BeanDescriptor(listenerClass);
		registerMethodName = METHOD_PRE + descriptor.getName();

		for (Method method : class1.getMethods()) {
			if (method.getName().equals(registerMethodName)) {
				registerMethod = method;
				break;
			}
		}
		return registerMethod;
	}
}
