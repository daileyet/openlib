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
* @Title: SuperActionsBinder.java 
* @Package binder 
* @Description: TODO
* @author dailey  
* @date 2012-3-22
* @version V1.0   
*/
package binder;

import java.beans.Beans;
import java.beans.EventHandler;
import java.io.IOException;

import binder.support.SuperActionBinderFactory;

/**
 * @author dailey
 *
 */
public class SuperActionsBinder {
	private Object bindObject;

	/**
	 * @param bindObject the object contains all action methods 
	 */
	public SuperActionsBinder(Object bindObject) {
		super();
		this.bindObject = bindObject;
	}

	public SuperActionsBinder(Class<?> bindClass) throws IOException, ClassNotFoundException {
		this(Beans.instantiate(bindClass.getClassLoader(), bindClass.getName()));
	}

	/**
	 * bind action named in bind object to control when control's listener call back
	 * @param component control need to bind action
	 * @param listenerInterface listener interface class
	 * @param listenerMethodName method name in listener interface class
	 * @param action action name in bind object
	 * @param eventPropertyName control trigger event property name 
	 */
	public <T> void bindAction(Object component, Class<T> listenerInterface, String listenerMethodName, String action,
			String eventPropertyName) {
		T listenerImpl = EventHandler.create(listenerInterface, bindObject, action, eventPropertyName,
				listenerMethodName);
		SuperActionBinderFactory.registerListener(component, listenerInterface, listenerImpl);
	}

	public <T> void bindAction(Object component, Class<T> listenerInterface, String listenerMethodName, String action) {
		T listenerImpl = EventHandler.create(listenerInterface, bindObject, action, null, listenerMethodName);
		SuperActionBinderFactory.registerListener(component, listenerInterface, listenerImpl);
	}

	public <T> void bindAction(Object component, Class<T> listenerInterface, String action) {
		T listenerImpl = EventHandler.create(listenerInterface, bindObject, action);
		SuperActionBinderFactory.registerListener(component, listenerInterface, listenerImpl);
	}
}
