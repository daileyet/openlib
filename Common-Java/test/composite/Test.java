package composite;


public class Test {

  /**
   * @param args
   */
  public static void main(String[] args) {
	  IAMConcreteConfig test = new IAMConcreteConfig();
	  test.create(new IAMCITable());
	  test.create(new IAMCPTable());
	  
	  IAMConcreteConfig test_sub1 = new IAMConcreteConfig();
	  test_sub1.create(new IAMCITable());
	  test_sub1.create(new IAMCITable());
	  test.create(test_sub1);
	  
	  IAMConcreteConfig test_sub2 = new IAMConcreteConfig();
	  test_sub2.create(new IAMCPTable());
	  test_sub2.create(new IAMCPTable());
	  test.create(test_sub2);
	 
  }

}
