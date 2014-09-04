package composite;

import java.util.List;
import java.util.Vector;

public class IAMConcreteConfig extends IAMConfig {
	private List<IAMConfig> childs = new Vector<IAMConfig>();

	@Override
	public void create(IAMConfig c) {
		childs.add(c);
	}

	@Override
	public void load(IAMConfig c) {
		childs.add(c);
	}

	@Override
	public void update(int seq, IAMConfig c) {
		childs.set(seq, c);
	}

	@Override
	public void delete(int seq) {
		childs.remove(seq);
	}

	public List<IAMConfig> getChilds() {
		return childs;
	}

	public void setChilds(List<IAMConfig> childs) {
		this.childs = childs;
	}

}
