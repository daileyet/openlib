package annotation;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EventListenerInstall {

	/**
	 * 
	 * @param target
	 *            the object contains field which want to add listener
	 * @param source
	 *            the object contains action method which the link for listener
	 *            action method
	 */
	public static void process(Object target, Object source) {
		Method[] methods = source.getClass().getDeclaredMethods();
		for (Method m : methods) {
			EventListenFor a = m.getAnnotation(EventListenFor.class);
			if (a != null) {
				Field field;
				try {
					field = target.getClass().getDeclaredField(a.source());
					field.setAccessible(true);
					Object fieldObj = field.get(target);
					addCComponentEventListener(source, m, fieldObj, a.event());
				} catch (SecurityException e) {
					throw e;
				} catch (NoSuchFieldException e) {
					throw new RuntimeException(e);
				} catch (IllegalArgumentException e) {
					throw e;
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (NoSuchMethodException e) {
					throw new RuntimeException(e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static void process(Object target, Class<?> sourceClz) {
		Object sourceInstance = null;
		if (sourceClz != null) {
			try {
				sourceInstance = sourceClz.newInstance();
				process(target, sourceInstance);
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static void addCComponentEventListener(final Object source,
			final Method m, Object fieldObj, final String event)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				return m.invoke(source);
			}
		};
		//
		ActionListener proxyListener = (ActionListener) Proxy.newProxyInstance(
				ActionListener.class.getClassLoader(),
				new Class[] { ActionListener.class }, handler);
		Method fieldMethod = fieldObj.getClass().getMethod("addActionListener",
				new Class[] { ActionListener.class });
		fieldMethod.invoke(fieldObj, proxyListener);
	}

}
