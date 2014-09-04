package composite.suggestion.configure.data.proxy;

import composite.suggestion.configure.data.inner.DIAMCI;
import composite.suggestion.configure.manager.ConfigureOperation;
import composite.suggestion.configure.manager.DIAMCIManager;

public class IAMCI extends AbstractIAMConfigure<DIAMCI> {
	DIAMCI dci;
	DIAMCIManager dciManager;

	@Override
	protected DIAMCI getConfigure() {
		return dci;
	}

	@Override
	protected ConfigureOperation<DIAMCI> getConfigureManager() {
		return dciManager;
	}

	
	
}
