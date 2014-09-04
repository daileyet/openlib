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
* @Title: SuperActionBinderManager.java 
* @Package binder.support 
* @Description: TODO
* @author dailey  
* @date 2012-3-22
* @version V1.0   
*/
package binder.support;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dailey
 *
 */
public class SuperActionBinderManager {
	private static final String DEFAULT_PROVIDER_NAME = "binder.action.super.provider.default";
	private static final Map<String, SuperActionBinderProvider> providers = new ConcurrentHashMap<String, SuperActionBinderProvider>();

	public static void registerProvider(String providerName, SuperActionBinderProvider provider) {
		providers.put(providerName, provider);
	}

	public static void registerDefaultProvider(SuperActionBinderProvider provider) {
		registerProvider(DEFAULT_PROVIDER_NAME, provider);
	}

	public static SuperActionBinderService newInstance(String providerName) {
		SuperActionBinderProvider provider = providers.get(providerName);
		if (provider == null) {
			throw new IllegalArgumentException("No provider registered with name: " + providerName);
		}
		return provider.newSuperActionBinderService();
	}

	public static SuperActionBinderService newInstance() {
		return newInstance(DEFAULT_PROVIDER_NAME);
	}

	// initial
	static {
		final String binder_file = "/binder/support/binder.service.properties";
		Properties contxt = new Properties();
		try {
			contxt.load(SuperActionBinderManager.class.getResourceAsStream(binder_file));
			String providerClassName = contxt.getProperty(DEFAULT_PROVIDER_NAME);
			Class.forName(providerClassName);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private SuperActionBinderManager() {
	}

}
