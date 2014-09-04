package datamodel;

import java.util.Iterator;

/**
 * 自定义可变长数组List
 * 
 * @author dmj
 * @since 2010/11/10
 */
public class DArrayList<E> implements DList<E> {
	private Object[] element;
	/**
	 * 实际元素个数
	 */
	private int size;

	public DArrayList() {
		this(10);
	}

	protected DArrayList(int capactiy) {
		element = new Object[capactiy];
	}

	/**
	 * 列表容量
	 * 
	 * @return 列表最大容量
	 */
	public int capactiy() {// 列表容量
		return element == null ? 0 : element.length;
	}

	/**
	 * 列表实际元素个数
	 * 
	 * @return 实际所容纳的元素个数
	 */
	public int size() {// 列表实际元素个数
		return size <= 0 ? 0 : size;
	}

	/**
	 * 向列表末尾添加一个元素
	 * 
	 * @param e
	 *            需要添加的元素
	 */
	public void add(E e) {// 向列表末尾添加一个元素
		add(size(), e);
	}

	/**
	 * 向列表指定位置插入一个元素
	 * 
	 * @param index
	 *            指定下标
	 * @param e
	 *            需要添加的元素
	 */
	public void add(int index, E e) {// 向指定位置插入一个元素
		if (index < 0 || index > size())
			throw new ArrayIndexOutOfBoundsException("添加元素下标越界");
		if (size() * 2 - 1 > capactiy()) {
			Object[] temp = new Object[size() * 2 - 1];
			for (int i = 0; i < size(); i++) {
				temp[i] = this.element[i];
			}
			this.element = temp;
		}
		for (int i = size(); i > index; i--) {
			this.element[i] = this.element[i - 1];
		}
		this.element[index] = e;
		size++;

	}

	/**
	 * 删除列表指定位置的元素
	 * 
	 * @param index
	 *            要删除列表中指定下标元素
	 * @return 删除的元素
	 */
	@SuppressWarnings("unchecked")
	public E remove(int index) {// 删除指定位置的元素
		if (index < 0 || index > size() - 1)
			throw new ArrayIndexOutOfBoundsException("删除元素下标越界");
		E T = (E) element[index];
		for (int i = index; i < size(); i++) {
			this.element[i] = this.element[i + 1];
		}
		size--;
		if (size() < (capactiy() + 1) / 2) {
			Object[] Temp = new Object[(capactiy() + 1) / 2];
			for (int i = 0; i < size(); i++) {
				Temp[i] = this.element[i];
			}
			this.element = Temp;
		}
		return T;
	}

	/**
	 * 返回指定下标的元素
	 * 
	 * @param index
	 *            指定下标
	 * @return 指定下标的元素
	 */
	@SuppressWarnings("unchecked")
	public E get(int index) {// 返回指定下标元素的值
		if (index < 0 || index > size() - 1)
			throw new ArrayIndexOutOfBoundsException("查找元素下标越界");
		return (E) element[index];
	}

	/**
	 * 返回迭代器
	 */
	@Override
	public Iterator<E> iterator() {// 返回迭代器
		return new Iterator<E>() {
			int index = -1;

			@Override
			public boolean hasNext() {
				return size() - 1 > index;
			}

			@SuppressWarnings("unchecked")
			@Override
			public E next() {
				return (E) element[++index];
			}

			@Override
			public void remove() {

			}

		};
	}

	@Override
	public void removeAll() {
		this.element = null;
		this.element = new Object[10];
		size = 0;
	}

}
