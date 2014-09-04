package binder.action.support;

public abstract class AbstractActionSupport extends ActionSupport {
	protected ActionSupportEvent actionEvent;

	public AbstractActionSupport(Object actionComponent, ActionSupportEvent actionEvent) {
		super(actionComponent);
		this.actionEvent = actionEvent;
		registerActionListener(actionComponent);
	}

	protected abstract void registerActionListener(Object component);

	@Override
	public ActionSupportEvent getActionSupportEvent() {
		return actionEvent;
	}

}
