package binder.action.support;

/**
 * Action event for one control, it usually likes {@link java.awt.MouseEvent}, {@link java.awt.ActionEvent}
 * User can implements by its owner event.
 * @author dailey
 *
 */
public interface ActionSupportEvent {

	public ActionSupport getActionSupport(Object component);

}
