package composite.suggestion.configure.data.proxy;

import composite.suggestion.configure.data.inner.DIAMCIR;
import composite.suggestion.configure.manager.ConfigureOperation;
import composite.suggestion.configure.manager.DIAMCIRManager;

public class IAMCIR extends AbstractIAMConfigure<DIAMCIR>{
	DIAMCIR dcir;
	DIAMCIRManager dcirManager;
	
	@Override
	protected DIAMCIR getConfigure() {
		return dcir;
	}
	@Override
	protected ConfigureOperation<DIAMCIR> getConfigureManager() {
		// TODO Auto-generated method stub
		return dcirManager;
	}


	
}
