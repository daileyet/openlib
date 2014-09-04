package composite.suggestion.configure.data.inner;

import java.util.ArrayList;
import java.util.List;

public class DIAMCP extends AbstratcDConfigure{
	private List<DIAMCI> ciList;
	
	public DIAMCP() {
		ciList=new ArrayList<DIAMCI>();
	}
	
	public void add(DIAMCI ci){
		ciList.add(ci);
	}
	
}
