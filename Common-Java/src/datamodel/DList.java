package datamodel;

/**
 * �б�ӿ�
 * 
 * @author dmj
 * @since 2010/11/10
 */
public interface DList<E> extends Iterable<E> {
	/**
	 * �б�����
	 * 
	 * @return �б��������
	 */
	public int size();

	/**
	 * �б�ʵ��Ԫ�ظ���
	 * 
	 * @return ʵ�������ɵ�Ԫ�ظ���
	 */
	public E get(int index);

	/**
	 * ���б�ĩβ���һ��Ԫ��
	 * 
	 * @param e
	 *            ��Ҫ��ӵ�Ԫ��
	 */
	public void add(E e);

	/**
	 * ���б�ָ��λ�ò���һ��Ԫ��
	 * 
	 * @param index
	 *            ָ���±�
	 * @param e
	 *            ��Ҫ��ӵ�Ԫ��
	 */
	public void add(int index, E e);

	/**
	 * ɾ���б�ָ��λ�õ�Ԫ��
	 * 
	 * @param index
	 *            Ҫɾ���б���ָ���±�Ԫ��
	 * @return ɾ����Ԫ��
	 */
	public E remove(int index);

	/**
	 * �Ƴ�����Ԫ��
	 */
	public void removeAll();

}
