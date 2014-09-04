package binder.support;

import binder.action.support.ActionSupport;
import binder.action.support.ActionSupportEvent;
import binder.component.support.ComponentSupport;

/**
 * action binder and component binder support interface
 * @author dailey
 *
 */
public interface BinderSupportService {

	/**
	 * create single component support
	 * @param component
	 * @return ComponentSupport
	 */
	public ComponentSupport createComponentSupport(Object component);

	/**
	 * create component with options support
	 * @param component
	 * @param optionals
	 * @return ComponentSupport
	 */
	public ComponentSupport createListableComponentSupport(Object component, Object[] optionals);

	/**
	 * create a group of component support
	 * @param buttons
	 * @param optionals
	 * @return ComponentSupport
	 */
	public ComponentSupport createGroupableComponentSupport(Object[] buttons, Object[] optionals);

	/**
	 * create action support for component by action event
	 * @param component
	 * @param event ActionSupportEvent
	 * @return ActionSupport
	 */
	public ActionSupport createActionSupport(Object component, ActionSupportEvent event);
}
