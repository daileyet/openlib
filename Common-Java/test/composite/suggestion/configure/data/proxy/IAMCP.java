package composite.suggestion.configure.data.proxy;

import composite.suggestion.configure.data.inner.DIAMCI;
import composite.suggestion.configure.data.inner.DIAMCP;
import composite.suggestion.configure.manager.ConfigureOperation;
import composite.suggestion.configure.manager.DIAMCPManager;

public class IAMCP extends AbstractIAMConfigure<DIAMCP> {
	DIAMCP dcp;
	DIAMCPManager dcpManager;
	
	public IAMCP() {
		dcp=new DIAMCP();
		dcpManager=new DIAMCPManager();
		setStatus(ObjectStatus.NEW);
	}
	
	@Override
	protected DIAMCP getConfigure() {
		return dcp;
	}
	@Override
	protected ConfigureOperation<DIAMCP> getConfigureManager() {
		return dcpManager;
	}
	
	public void add(IAMCI ci){
		DIAMCI dci=ci.getConfigure();
		dcp.add(dci);
	}


}
