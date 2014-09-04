package composite;

public abstract class IAMConfig {
	public abstract void create(IAMConfig c);

	public abstract void load(IAMConfig c);

	public abstract void update(int seq, IAMConfig c);

	public abstract void delete(int seq);
}
