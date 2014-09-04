/**
 * Licensed to the Apache Software Foundation (ASF) under one 
 * or more contributor license agreements. See the NOTICE file 
 * distributed with this work for additional information 
 * regarding copyright ownership. The ASF licenses this file 
 * to you under the Apache License, Version 2.0 (the 
 * "License"); you may not use this file except in compliance 
 * with the License. You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY 
 * KIND, either express or implied. See the License for the 
 * specific language governing permissions and limitations 
 * under the License. 
 * 
 * @Title: MessageEntity.java 
 * @Package i18n.database 
 * @Description: TODO
 * @author dailey 
 * @date 2012-11-2
 * @version V1.0 
 */
package i18n.database;

import i18n.IMessage;

import java.util.Locale;

import sql.entity.Entity;
import utilities.LocaleUtils;

/**
 * @author dailey
 *
 */
public class MessageEntity extends Entity implements IMessage {

	private String messageId;
	private String locale;
	private String content;

	/* (non-Javadoc)
	 * @see i18n.IMessage#getContent()
	 */
	@Override
	public String getContent() {
		return content;
	}

	/* (non-Javadoc)
	 * @see i18n.IMessage#getLocale()
	 */
	@Override
	public Locale getLocale() {
		return LocaleUtils.langToLocale(locale);
	}

	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}
	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MessageEntity [messageId=" + messageId + ", locale=" + locale + ", content=" + content + "]";
	}
}
