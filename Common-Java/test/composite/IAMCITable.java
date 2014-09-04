package composite;

public class IAMCITable extends IAMConfig {

	@Override
	public void create(IAMConfig c) {
		System.out.println("memory create IAMCITable");

	}

	@Override
	public void load(IAMConfig c) {
		System.out.println("db create IAMCITable");

	}

	@Override
	public void update(int seq, IAMConfig c) {
		System.out.println("db update IAMCITable");

	}

	@Override
	public void delete(int seq) {
		System.out.println("db delete IAMCITable");

	}

}
