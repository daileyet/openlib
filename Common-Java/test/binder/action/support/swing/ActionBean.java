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
* @Title: ActionBean.java 
* @Package binder.action.support.swing 
* @Description: TODO
* @author dailey  
* @date 2012-3-20
* @version V1.0   
*/
package binder.action.support.swing;

import java.awt.event.MouseEvent;

/**
 * @author dailey
 *
 */
public class ActionBean {
	public static final String MOUSE_CLICK = "mouse_click";
	public static final String MOUSE_DOUBLE_CLICK = "mouse_double_click";
	public static final String MOUSE_RIGHT_CLICK = "mouse_right_click";
	public static final String DEFAULT = "default_";
	public static final String DEFAULT_MOUSE_CLICK = "default_mouse_click";
	public static final String SUPER_DEFAULT = "super_default";
	public static final String SUPER_MOUSE_CLICK = "super_mouse_click";
	public static final String SUPER_MOUSE_RIGHT_CLICK = "super_mouse_right_click";
	public static final String SUPER_MOUSE_DOUBLE_CLICK = "super_mouse_double_click";

	private ActionBean() {
	}

	private static ActionBean instance = new ActionBean();

	public static ActionBean getInstance() {
		return instance;
	}

	public void mouse_click() {
		System.out.println("Mouse click");
	}

	public void mouse_double_click() {
		System.out.println("Mouse double click");
	}

	public void mouse_right_click() {
		System.out.println("Mouse right click");
	}

	public void default_() {
		System.out.println("default action");
	}

	protected void default_mouse_click() {
		System.out.println("default action and mouse click");
	}

	public void super_default() {
		System.out.println("super_default");
	}

	public void super_mouse_click() {
		System.out.println("super_mouse_click");
	}

	public void super_mouse_right_click(int button) {
		if (button == MouseEvent.BUTTON3) {
			System.out.println("super_mouse_right_click");
		}
	}

	public void super_mouse_right_click() {
		System.out.println("super_mouse_right_click without parameter");
	}

	public void super_mouse_double_click(MouseEvent event) {
		if (event.getClickCount() == 2) {
			System.out.println("super_mouse_double_click");
		}
	}

}
