package composite.suggestion.configure.test;

import composite.suggestion.configure.data.proxy.IAMCP;

public class Test {
	
	public static void main(String[] args) {
		
		IAMCP cp=new IAMCP();
		cp.setName("CP");
		cp.save();
	}
	
}
