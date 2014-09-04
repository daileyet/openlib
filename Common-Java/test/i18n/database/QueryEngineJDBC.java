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
 * @Title: QueryEngineJDBC.java 
 * @Package i18n.database 
 * @Description: TODO
 * @author dailey 
 * @date 2012-11-2
 * @version V1.0 
 */
package i18n.database;

import i18n.IMessage;
import i18n.implement.database.query.IQueryer;
import i18n.implement.database.query.QueryEngine;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;

import sql.dhibernate.Session;
import sql.dhibernate.support.SessionFactory;
import sql.lang.Configurator;
import sql.lang.ConfiguratorFactory;

/**
 * @author dailey
 *
 */
public class QueryEngineJDBC extends QueryEngine {

	static{
		QueryEngine.registerQueryEngine(new QueryEngineJDBC());
	}
	
	/* (non-Javadoc)
	 * @see i18n.implement.database.QueryEngine#getQueryer()
	 */
	@Override
	public IQueryer getQueryer() {
		return new JDBCQueryer();
	}

	class JDBCQueryer implements IQueryer {

		/* (non-Javadoc)
		 * @see i18n.implement.database.IQueryer#query(java.lang.Class, java.io.Serializable, java.util.Locale)
		 */
		@Override
		public <T extends IMessage> T query(Class<T> entityClass, Serializable messageId, Locale locale) {
			Session session = SessionFactory.getSession();
			String sql = "SELECT content FROM message WHERE messageId=? and locale= ?";
			T t = session.get(entityClass, sql, new String[] { messageId.toString(), locale.toString() });
			session.close();
			return t;
		}

		/* (non-Javadoc)
		 * @see i18n.implement.database.IQueryer#query(java.lang.String, java.io.Serializable, java.util.Locale)
		 */
		@Override
		public String query(String tableName, Serializable messageId, Locale locale) {
			String content = null;
			Configurator configurator = ConfiguratorFactory.getDefaultInstance(getClass());
			try {
				Class.forName(configurator.getDriver());
				Connection conn = DriverManager.getConnection(configurator.getUrl(), configurator.getUserName(),
						configurator.getUserPwd());
				String sql = "SELECT content FROM " + tableName + " WHERE messageId=? and locale= ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, (String) messageId);
				ps.setString(2, locale.toString());
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					content = rs.getString("content");
				}
				rs.close();
				ps.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return content;
		}

	}

}
