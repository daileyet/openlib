package composite.suggestion.configure.data.proxy;

import composite.suggestion.configure.data.inner.Configure;
import composite.suggestion.configure.manager.ConfigureOperation;

public abstract class AbstractIAMConfigure<T extends Configure> implements IAMConfigure {
	private ObjectStatus status;

	@Override
	public void save() {
		ConfigureOperation<T> manager = getConfigureManager();
		T configure = getConfigure();
		switch (status) {
		case NEW:
			manager.create(configure);
			break;
		case MODIFY:
			manager.update(configure);
			break;
		case DELETE:
			manager.delete(configure);
			break;
		default:
			break;
		}
	}

	public void setStatus(ObjectStatus status) {
		this.status = status;
	}
	
	public ObjectStatus getStatus() {
		return status;
	}

	
	@Override
	public void setName(String name) {
		T configure=getConfigure();
		configure.setName(name);
	}
	
	@Override
	public String getName() {
		T configure=getConfigure();
		return configure.getName();
	}
	
	protected abstract T getConfigure();

	protected abstract ConfigureOperation<T> getConfigureManager();

}
