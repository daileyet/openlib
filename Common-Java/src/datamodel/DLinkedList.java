package datamodel;

import java.util.Iterator;

/**
 * 自定义单向链表
 * 
 * @author dmj
 * @since 2010/11/15
 */
public class DLinkedList<E> implements DList<E> {
	private int size;
	private Node firstElement;// 元素

	public DLinkedList() {
		firstElement = new Node();
	}

	/**
	 * 链表内部节点类型
	 */
	class Node {
		private E value;// 节点值
		private Node next;// 后继节点

		Node() {
			this(null);
		}

		Node(E value) {
			this(value, null);
		}

		Node(E value, Node next) {
			this.value = value;
			this.next = next;
		}

		@Override
		public String toString() {
			return value.toString();
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}

	@Override
	public void add(E e) {
		add(size(), e);
	}

	@Override
	public void add(int index, E e) {
		if (index < 0 || index > size())
			throw new ArrayIndexOutOfBoundsException("添加元素下标越界");
		Node node = new Node(e);
		getNode(index - 1).setNext(node);
		size++;
	}

	@Override
	public E get(int index) {
		Node node = getNode(index);
		return node == null ? null : node.getValue();
	}

	private Node getNode(int index) {
		if (index < -1 || index >= size())
			throw new ArrayIndexOutOfBoundsException("查找元素下标越界");
		if (firstElement == null)
			return null;
		if (size() == 0)
			return firstElement;
		Node temp = new Node(null, firstElement.getNext());
		int count = -1;
		while (temp.getNext() != null && count != index) {
			count++;
			temp = temp.getNext();
		}
		return temp;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size())
			throw new ArrayIndexOutOfBoundsException("删除元素下标越界");
		Node beforNode = null;
		if (index == 0)
			beforNode = firstElement;
		else
			beforNode = getNode(index - 1);
		Node currentNode = beforNode.getNext();
		Node endNode = beforNode.getNext().getNext();
		beforNode.setNext(endNode);
		currentNode.setNext(null);
		size--;
		return currentNode.getValue();
	}

	@Override
	public void removeAll() {
		firstElement.setNext(null);
		size = 0;
	}

	@Override
	public int size() {
		return size <= 0 ? 0 : size;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			int index = -1;

			@Override
			public boolean hasNext() {
				return size() - 1 > index;
			}

			@Override
			public E next() {
				return get(++index);
			}

			@Override
			public void remove() {

			}

		};
	}

}
