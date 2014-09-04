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
* @Title: DescriptorTest.java 
* @Package binder 
* @Description: TODO
* @author dailey  
* @date 2012-3-22
* @version V1.0   
*/
package binder;

import java.awt.event.ActionListener;
import java.beans.BeanDescriptor;
import java.lang.reflect.Method;

import javax.swing.JButton;

/**
 * @author dailey
 *
 */
public class DescriptorTest {
	public static void main(String[] args) {

		BeanDescriptor descriptor = new BeanDescriptor(ActionListener.class);
		descriptor = new BeanDescriptor(JButton.class);
		System.out.println(descriptor.getName());
		System.out.println(descriptor.getDisplayName());
		System.out.println(descriptor.getShortDescription());
		System.out.println(descriptor.getBeanClass());

		for (Method method : JButton.class.getMethods()) {
			System.out.println(method.getName());
			//			MethodDescriptor md = new MethodDescriptor(method);
			//			System.out.println(md.getName());
		}

	}
}
