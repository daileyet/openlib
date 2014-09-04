package binder.component.support;

public abstract class GroupableComponentSupport<T> extends ComponentSupport {
	protected T[] components;
	protected Object[] values;

	public GroupableComponentSupport(T[] components, Object[] values) {
		if (components == null || values == null || components.length != values.length) {
			throw new IllegalArgumentException("The group of component's quantity need same as values'.");
		}
		this.components = components;
		this.values = values;
	}

	protected T[] getComponents() {
		if (components == null || components.length == 0)
			throw new NullPointerException("The components need to be asigned.");
		return components;
	}

	protected Object[] getValues() {
		return values;
	}
}
