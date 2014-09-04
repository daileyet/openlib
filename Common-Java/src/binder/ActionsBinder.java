package binder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import binder.action.ActionBinder;
import binder.action.support.ActionSupportEvent;
import binder.support.BinderSupportFactory;

public class ActionsBinder {
	private Object bindObject = null;
	private List<ActionBinder> list = null;
	private Class<?> clazz = null;

	public ActionsBinder(Object bindObject) {
		setBindObject(bindObject);
		list = Collections.synchronizedList(new ArrayList<ActionBinder>());
	}

	private void setBindObject(Object bindObject) {
		this.bindObject = bindObject;
		clazz = bindObject.getClass();
	}

	/**
	 * bind action for control's {@link ActonSupportEvent} happen.
	 * @param actionMethodName method name in bind action object
	 * @param component need to bind action's control
	 * @param actionEvent {@link ActonSupportEvent} is define the control support event
	 */
	public void bindAction(String actionMethodName, Object component, ActionSupportEvent actionEvent) {
		try {
			Class<?>[] parameters = null;
			Method method = clazz.getDeclaredMethod(actionMethodName, parameters);
			method.setAccessible(true);
			list.add(new ActionBinder(bindObject, method, BinderSupportFactory.createActionSupport(component,
					actionEvent)));
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	/**
	 * bind action for control's default support event happen.
	 * such as control {@link javax.swing.JButton}'s default support event is {@link java.awt.event.ActionEvent}
	 * @param actionMethodName method name in bind action object
	 * @param component need to bind action's control
	 */
	public void bindAction(String actionMethodName, Object component) {
		bindAction(actionMethodName, component, null);
	}

}
