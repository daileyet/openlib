package composite.suggestion.configure.manager;

import composite.suggestion.configure.data.inner.DIAMCP;

public class DIAMCPManager implements ConfigureOperation<DIAMCP>{

	@Override
	public void create(DIAMCP e) {
		System.out.println("DCP create."+e);
		
	}

	@Override
	public void load(DIAMCP e) {
		System.out.println("DCP load.");
	}

	@Override
	public void update(DIAMCP e) {
		System.out.println("DCP update.");
	}

	@Override
	public void delete(DIAMCP e) {
		System.out.println("DCP delete.");
	}
	
}
