package composite.suggestion.configure.data.inner;

import java.util.ArrayList;
import java.util.List;

public class DIAMCI extends AbstratcDConfigure{
	private List<DIAMCIR> cirList;
	
	public DIAMCI() {
		cirList=new ArrayList<DIAMCIR>();
	}
	
	public void add(DIAMCIR ci){
		cirList.add(ci);
	}
}
