package composite.suggestion.configure.manager;

import composite.suggestion.configure.data.inner.Configure;

public interface ConfigureOperation<E extends Configure>{

	public void create(E e);

	public void load(E e);

	public void update(E e);

	public void delete(E e);
	
}
