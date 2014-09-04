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
* @Title: SwingSuperActionBinderProvider.java 
* @Package binder.support.swing.action.supers 
* @Description: TODO
* @author dailey  
* @date 2012-3-22
* @version V1.0   
*/
package binder.support.swing.action.supers;

import binder.support.SuperActionBinderManager;
import binder.support.SuperActionBinderProvider;
import binder.support.SuperActionBinderService;

/**
 * @author dailey
 *
 */
public class SwingSuperActionBinderProvider implements SuperActionBinderProvider {
	static {
		SuperActionBinderManager.registerDefaultProvider(new SwingSuperActionBinderProvider());
	}

	private SwingSuperActionBinderProvider() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see binder.support.SuperActionBinderProvider#newSuperActionBinderService()
	 */
	@Override
	public SuperActionBinderService newSuperActionBinderService() {
		return new SwingSuperActionBinderService();
	}

}
