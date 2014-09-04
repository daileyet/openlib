package binder.action.support.swing;

import javax.swing.JButton;

import binder.support.swing.action.SwingActionEvent;

public class SwingActionSupportEventTest {
	
	public static void main(String[] args) {
		System.out.println(SwingActionEvent.DEFAULT_ACTION.getActionSupport(new JButton()));
	}
	
}
