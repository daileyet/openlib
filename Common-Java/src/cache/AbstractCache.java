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
* @Title: AbstractCache.java 
* @Package cache 
* @author dailey_dai  
* @date 2012-5-1
* @version V1.0   
*/
package cache;

import java.util.Iterator;
import java.util.Map;

/**
 * @author dailey_dai
 *
 */
public abstract class AbstractCache<K, V> implements Cache<K, V> {

	final class CacheValuesIterator implements Iterator<V> {
		Iterator<? extends CacheObject<?, V>> iterator = cacheMap.values().iterator();
		CacheObject<?, V> nextValue;

		@Override
		public boolean hasNext() {
			return nextValue != null;
		}

		@Override
		public V next() {
			V value = nextValue.value;
			nextValue();
			return value;
		}

		@Override
		public void remove() {
			iterator.remove();
		}

		private void nextValue() {
			while (iterator.hasNext()) {
				nextValue = iterator.next();
				if (nextValue.isExpired() == false) {
					return;
				}
			}
			nextValue = null;
		}
	}

	class CacheObject<K2, V2> {
		final K2 key;
		final V2 value;
		long timeout; //time to live, 0 = no timeout
		long lastAccess; //time of last access
		int accessCount; //count of access

		public CacheObject(K2 k, V2 v, long timeout) {
			this.key = k;
			this.value = v;
			this.timeout = timeout;
			this.lastAccess = System.currentTimeMillis();
		}

		boolean isExpired() {
			if (timeout <= 0) {
				return false;
			}
			return lastAccess + timeout < System.currentTimeMillis();
		}

		V2 getObject() {
			lastAccess = System.currentTimeMillis();
			accessCount++;
			return value;
		}

	}

	protected Map<K, CacheObject<K, V>> cacheMap;

	protected int cacheSize; //max cache size, 0 = no limit

	protected boolean existCustomTimeout; //

	protected long timeout; //default timeout, 0 = no timeout

	protected boolean isPruneExpiredActive() {
		return (timeout != 0 || existCustomTimeout);
	}

	/* (non-Javadoc)
	 * @see cache.Cache#iterator()
	 */
	@Override
	public Iterator<V> iterator() {
		return new CacheValuesIterator();
	}

	/* (non-Javadoc)
	 * @see cache.Cache#getCacheSize()
	 */
	@Override
	public int getCacheSize() {
		return cacheSize;
	}

	/* (non-Javadoc)
	 * @see cache.Cache#size()
	 */
	@Override
	public int size() {
		return cacheMap.size();
	}

	/* (non-Javadoc)
	 * @see cache.Cache#get(java.lang.Object)
	 */
	@Override
	public V get(K k) {
		CacheObject<K, V> cacheObject = cacheMap.get(k);
		if (cacheObject == null) {
			return null;
		}
		if (cacheObject.isExpired()) {
			remove(k);
			return null;
		}
		return cacheObject.getObject();
	}

	/* (non-Javadoc)
	 * @see cache.Cache#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void put(K k, V v) {
		put(k, v, timeout);
	}

	/* (non-Javadoc)
	 * @see cache.Cache#put(java.lang.Object, java.lang.Object, long)
	 */
	@Override
	public void put(K k, V v, long timeout) {
		CacheObject<K, V> cacheObject = new CacheObject<K, V>(k, v, timeout);
		if (timeout != 0) {
			existCustomTimeout = true;
		}
		if (isFull()) {
			prune();
		}
		cacheMap.put(k, cacheObject);
	}

	/* (non-Javadoc)
	 * @see cache.Cache#remove(java.lang.Object)
	 */
	@Override
	public void remove(K k) {
		cacheMap.remove(k);
	}

	/* (non-Javadoc)
	 * @see cache.Cache#prune()
	 */
	@Override
	public abstract int prune();

	/* (non-Javadoc)
	 * @see cache.Cache#isFull()
	 */
	@Override
	public boolean isFull() {
		if (getCacheSize() == 0) {
			return false;
		}
		return size() >= getCacheSize();
	}

	/* (non-Javadoc)
	 * @see cache.Cache#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return cacheMap.isEmpty();
	}

	/* (non-Javadoc)
	 * @see cache.Cache#getCacheTimeout()
	 */
	@Override
	public long getCacheTimeout() {
		return timeout;
	}

	/* (non-Javadoc)
	 * @see cache.Cache#clear()
	 */
	@Override
	public void clear() {
		cacheMap.clear();
	}

}
