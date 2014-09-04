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
* @Title: Cache.java 
* @Package cache 
* @author dailey_dai  
* @date 2012-5-1
* @version V1.0   
*/
package cache;

import java.util.Iterator;

/**
 * @author dailey_dai
 *
 */
public interface Cache<K,V> {
	
	public int getCacheSize();
	
	public int size();
	
	public V get(K k);
	
	public void put(K k,V v);
	
	public void put(K k,V v,long timeout);
	
	public void remove(K k);
	
	public int prune();
	
	public boolean isFull();
	
	public boolean isEmpty();
	
	public long getCacheTimeout();
	
	public void clear();
	
	public Iterator<V> iterator();
	
	
}
