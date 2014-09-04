package regex;

import junit.framework.Assert;

import org.junit.Test;

public class RegexUtilTest {

	@Test
	public void testDoubleIsInteger(){
		String target=new Double(100.0).toString();
		boolean actual=RegexUtil.isMatch("^\\d*\\.[0]*$", target);
		Assert.assertEquals(true, actual);
		
	}
	
}
