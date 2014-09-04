package datamodel;

import java.util.Iterator;

/**
 * �Զ���ɱ䳤����List
 * 
 * @author dmj
 * @since 2010/11/10
 */
public class DArrayList<E> implements DList<E> {
	private Object[] element;
	/**
	 * ʵ��Ԫ�ظ���
	 */
	private int size;

	public DArrayList() {
		this(10);
	}

	protected DArrayList(int capactiy) {
		element = new Object[capactiy];
	}

	/**
	 * �б�����
	 * 
	 * @return �б��������
	 */
	public int capactiy() {// �б�����
		return element == null ? 0 : element.length;
	}

	/**
	 * �б�ʵ��Ԫ�ظ���
	 * 
	 * @return ʵ�������ɵ�Ԫ�ظ���
	 */
	public int size() {// �б�ʵ��Ԫ�ظ���
		return size <= 0 ? 0 : size;
	}

	/**
	 * ���б�ĩβ���һ��Ԫ��
	 * 
	 * @param e
	 *            ��Ҫ��ӵ�Ԫ��
	 */
	public void add(E e) {// ���б�ĩβ���һ��Ԫ��
		add(size(), e);
	}

	/**
	 * ���б�ָ��λ�ò���һ��Ԫ��
	 * 
	 * @param index
	 *            ָ���±�
	 * @param e
	 *            ��Ҫ��ӵ�Ԫ��
	 */
	public void add(int index, E e) {// ��ָ��λ�ò���һ��Ԫ��
		if (index < 0 || index > size())
			throw new ArrayIndexOutOfBoundsException("���Ԫ���±�Խ��");
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
	 * ɾ���б�ָ��λ�õ�Ԫ��
	 * 
	 * @param index
	 *            Ҫɾ���б���ָ���±�Ԫ��
	 * @return ɾ����Ԫ��
	 */
	@SuppressWarnings("unchecked")
	public E remove(int index) {// ɾ��ָ��λ�õ�Ԫ��
		if (index < 0 || index > size() - 1)
			throw new ArrayIndexOutOfBoundsException("ɾ��Ԫ���±�Խ��");
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
	 * ����ָ���±��Ԫ��
	 * 
	 * @param index
	 *            ָ���±�
	 * @return ָ���±��Ԫ��
	 */
	@SuppressWarnings("unchecked")
	public E get(int index) {// ����ָ���±�Ԫ�ص�ֵ
		if (index < 0 || index > size() - 1)
			throw new ArrayIndexOutOfBoundsException("����Ԫ���±�Խ��");
		return (E) element[index];
	}

	/**
	 * ���ص�����
	 */
	@Override
	public Iterator<E> iterator() {// ���ص�����
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
