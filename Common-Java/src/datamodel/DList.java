package datamodel;

/**
 * 列表接口
 * 
 * @author dmj
 * @since 2010/11/10
 */
public interface DList<E> extends Iterable<E> {
	/**
	 * 列表容量
	 * 
	 * @return 列表最大容量
	 */
	public int size();

	/**
	 * 列表实际元素个数
	 * 
	 * @return 实际所容纳的元素个数
	 */
	public E get(int index);

	/**
	 * 向列表末尾添加一个元素
	 * 
	 * @param e
	 *            需要添加的元素
	 */
	public void add(E e);

	/**
	 * 向列表指定位置插入一个元素
	 * 
	 * @param index
	 *            指定下标
	 * @param e
	 *            需要添加的元素
	 */
	public void add(int index, E e);

	/**
	 * 删除列表指定位置的元素
	 * 
	 * @param index
	 *            要删除列表中指定下标元素
	 * @return 删除的元素
	 */
	public E remove(int index);

	/**
	 * 移除所有元素
	 */
	public void removeAll();

}
