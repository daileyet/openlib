package binder.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;

/**
 * link {@link ActionSupport} to a method in action bean
 * @author dailey
 *
 */
public class ActionBinder implements Observer {
	private Object actionObject = null;
	private Method method = null;

	public ActionBinder(Object actionObject, Method method, Observable trigger) {
		this.actionObject = actionObject;
		this.method = method;
		trigger.addObserver(this);
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		try {
			method.invoke(actionObject);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
