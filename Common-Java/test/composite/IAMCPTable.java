package composite;

public class IAMCPTable extends IAMConfig {
	@Override
	public void create(IAMConfig c) {
		System.out.println("memory create IAMCPTable");

	}

	@Override
	public void load(IAMConfig c) {
		System.out.println("db create IAMCPTable");

	}

	@Override
	public void update(int seq, IAMConfig c) {
		System.out.println("db update IAMCPTable");

	}

	@Override
	public void delete(int seq) {
		System.out.println("db delete IAMCPTable");

	}

}
