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
* @Title: ObjectBean.java 
* @Package binder.object.support.swing 
* @Description: TODO
* @author dailey  
* @date 2012-3-18
* @version V1.0   
*/
package binder.component.support.swing;

import binder.support.AbstractPropertySupportted;

/**
 * @author dailey
 *
 */
public class ObjectBean extends AbstractPropertySupportted {
	private static final ObjectBean instance = new ObjectBean();

	private ObjectBean() {
	}

	/**
	 * @return
	 */
	public static ObjectBean getInstance() {
		return instance;
	}

	public static final String PROPERTY_TEXT = "textVal";
	public static final String PROPERTY_TEXTAREA = "textAreaVal";
	public static final String PROPERTY_CHECKBOX = "checkBoxVal";
	public static final String PROPERTY_COMBOX = "comboxVal";
	public static final String PROPERTY_RADIO = "radioVal";

	private String textVal;
	private String textAreaVal;
	private Object[] checkBoxVal;
	private String radioVal;
	private String comboxVal;

	public String getTextVal() {
		return textVal;
	}

	public void setTextVal(String textVal) {
		String oldVal = this.textVal;
		this.textVal = textVal;
		firePropertyChange(PROPERTY_TEXT, oldVal, textVal);
	}

	public String getTextAreaVal() {
		return textAreaVal;
	}

	public void setTextAreaVal(String textAreaVal) {
		String oldVal = this.textAreaVal;
		this.textAreaVal = textAreaVal;
		firePropertyChange(PROPERTY_TEXTAREA, oldVal, textAreaVal);
	}

	
	/**
	 * @return the radioVal
	 */
	public String getRadioVal() {
		return radioVal;
	}
	
	
	/**
	 * @param radioVal the radioVal to set
	 */
	public void setRadioVal(String radioVal) {
		String oldVal=this.radioVal;
		this.radioVal = radioVal;
		firePropertyChange(PROPERTY_RADIO, oldVal, radioVal);
	}
	
	/**
	 * @return the checkBoxVal
	 */
	public Object[] getCheckBoxVal() {
		return checkBoxVal;
	}

	/**
	 * @param checkBoxVal the checkBoxVal to set
	 */
	public void setCheckBoxVal(Object[] checkBoxVal) {
		Object[] oldVal = this.checkBoxVal;
		this.checkBoxVal = checkBoxVal;
		firePropertyChange(PROPERTY_CHECKBOX, oldVal, checkBoxVal);
	}

	public String getComboxVal() {
		return comboxVal;
	}

	public void setComboxVal(String comboxVal) {
		String oldVal = this.comboxVal;
		this.comboxVal = comboxVal;
		firePropertyChange(PROPERTY_COMBOX, oldVal, comboxVal);
	}

}
