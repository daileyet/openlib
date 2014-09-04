/**   
 *  Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * @Title: BasicUI.java 
 * @Package binder.object.support.swing 
 * @Description: TODO
 * @author dailey  
 * @date 2012-3-18
 * @version V1.0   
 */
package binder;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.beans.IntrospectionException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import binder.ComponentsBinder;
import binder.action.support.swing.ActionBean;
import binder.component.support.swing.ObjectBean;
import binder.support.swing.action.SwingActionEvent;

/**
 * @author dailey
 * 
 */
public class BasicUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1318193534181155615L;
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox comboBox;
	private JTextArea textArea;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private ComponentsBinder binder;
	private ActionsBinder actionsbinder;
	SuperActionsBinder superActionsBinder;
	private JButton btnChangeModleValue;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BasicUI frame = new BasicUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BasicUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 10, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		comboBox = new JComboBox();
		comboBox.setBounds(10, 41, 66, 21);
		contentPane.add(comboBox);

		textArea = new JTextArea();
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBounds(10, 72, 66, 66);
		contentPane.add(textArea);

		btnChangeModleValue = new JButton("Change Modle Value");
		btnChangeModleValue.setBounds(10, 200, 245, 30);
		contentPane.add(btnChangeModleValue);
		btnChangeModleValue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ObjectBean.getInstance().setRadioVal("radio1");
				ObjectBean.getInstance().setCheckBoxVal(
						new String[] { "check1", "check2", "check3" });
				ObjectBean.getInstance().setTextVal("Hello");
				ObjectBean.getInstance().setTextAreaVal("Hello too!");
			}
		});

		chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(99, 9, 103, 23);
		contentPane.add(chckbxNewCheckBox);

		chckbxNewCheckBox_1 = new JCheckBox("New check box");
		chckbxNewCheckBox_1.setBounds(99, 40, 103, 23);
		contentPane.add(chckbxNewCheckBox_1);

		chckbxNewCheckBox_2 = new JCheckBox("New check box");
		chckbxNewCheckBox_2.setBounds(99, 72, 103, 23);
		contentPane.add(chckbxNewCheckBox_2);

		rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(96, 115, 121, 23);
		contentPane.add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setBounds(96, 149, 121, 23);
		contentPane.add(rdbtnNewRadioButton_1);

		try {
			binder = new ComponentsBinder(ObjectBean.getInstance());
			binder.bindProperty(ObjectBean.PROPERTY_TEXT, textField);
			binder.bindProperty(ObjectBean.PROPERTY_TEXTAREA, textArea);
			binder.bindProperty(ObjectBean.PROPERTY_CHECKBOX,
					new JCheckBox[] { chckbxNewCheckBox, chckbxNewCheckBox_1,
							chckbxNewCheckBox_2 }, new String[] { "check1",
							"check2", "check3" });
			binder.bindProperty(ObjectBean.PROPERTY_COMBOX, comboBox,
					new String[] { "item1", "item2", "item3" });

			btnNewButton = new JButton("Default");
			btnNewButton.setBounds(265, 24, 159, 23);
			contentPane.add(btnNewButton);

			btnNewButton_1 = new JButton("Mouse Click");
			btnNewButton_1.setBounds(265, 71, 159, 23);
			contentPane.add(btnNewButton_1);

			btnNewButton_2 = new JButton("Mouse Double Click");
			btnNewButton_2.setBounds(265, 118, 159, 23);
			contentPane.add(btnNewButton_2);

			btnNewButton_3 = new JButton("Mouse Right Click");
			btnNewButton_3.setBounds(265, 165, 159, 23);
			contentPane.add(btnNewButton_3);

			btnNewButton_4 = new JButton("Default & Mouse Click");
			btnNewButton_4.setBounds(265, 212, 159, 23);
			contentPane.add(btnNewButton_4);

			binder.bindProperty(ObjectBean.PROPERTY_RADIO, new JRadioButton[] {
					rdbtnNewRadioButton, rdbtnNewRadioButton_1 }, new String[] {
					"radio1", "radio2" });

			ObjectBean.getInstance().addPropertyChangeListener(
					new PropertyChangeListener() {

						@Override
						public void propertyChange(PropertyChangeEvent evt) {
							String propertyName = evt.getPropertyName();
							if (ObjectBean.PROPERTY_CHECKBOX
									.equals(propertyName)) {
								System.out.println(propertyName + " OldValue:["
										+ toSString(evt.getOldValue())
										+ "],NewValue:["
										+ toSString(evt.getNewValue()) + "]");
							} else {
								System.out.println(propertyName + " OldValue:"
										+ evt.getOldValue() + ",NewValue:"
										+ evt.getNewValue());
							}
						}
					});

			actionsbinder = new ActionsBinder(ActionBean.getInstance());
			actionsbinder.bindAction(ActionBean.DEFAULT, btnNewButton);
			actionsbinder.bindAction(ActionBean.MOUSE_CLICK, btnNewButton_1,
					SwingActionEvent.MOUSE_CLICK_ACTION);
			actionsbinder.bindAction(ActionBean.MOUSE_DOUBLE_CLICK,
					btnNewButton_2, SwingActionEvent.MOUSE_DOUBLE_CLICK_ACTION);
			actionsbinder.bindAction(ActionBean.MOUSE_RIGHT_CLICK,
					btnNewButton_3, SwingActionEvent.MOUSE_RIGHT_CLICK_ACTION);
			actionsbinder.bindAction(ActionBean.DEFAULT, btnNewButton_4,
					SwingActionEvent.DEFAULT_ACTION);
			actionsbinder.bindAction(ActionBean.MOUSE_CLICK, btnNewButton_4,
					SwingActionEvent.MOUSE_CLICK_ACTION);
			
			
			superActionsBinder=new SuperActionsBinder(ActionBean.getInstance());
			
			btnNewButton_5 = new JButton("Super Default");
			btnNewButton_5.setBounds(265, 252, 159, 23);
			contentPane.add(btnNewButton_5);

			
			
			JButton btnNewButton_6 = new JButton("Super Mouse Click");
			btnNewButton_6.setBounds(265, 286, 159, 23);
			contentPane.add(btnNewButton_6);
			
			JButton btnNewButton_7 = new JButton("Super Mouse Right Click");
			btnNewButton_7.setBounds(265, 319, 159, 23);
			contentPane.add(btnNewButton_7);
			
			JButton btnNewButton_8 = new JButton("Super Mouse Double Click");
			btnNewButton_8.setBounds(265, 352, 159, 23);
			contentPane.add(btnNewButton_8);
			
			
			superActionsBinder.bindAction(btnNewButton_5, ActionListener.class, ActionBean.SUPER_DEFAULT);
			superActionsBinder.bindAction(btnNewButton_6, MouseListener.class, "mouseClicked", ActionBean.SUPER_MOUSE_CLICK, null);
			superActionsBinder.bindAction(btnNewButton_7, MouseListener.class, "mouseClicked", ActionBean.SUPER_MOUSE_RIGHT_CLICK, "button");
			superActionsBinder.bindAction(btnNewButton_8, MouseListener.class, "mouseClicked", ActionBean.SUPER_MOUSE_DOUBLE_CLICK, "");
			
			
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

	}

	private String toSString(Object obj) {
		if (obj != null && obj.getClass().isArray()) {
			Object[] objs = (Object[]) obj;
			StringBuffer buff = new StringBuffer();
			for (Object objt : objs) {
				buff.append(objt);
			}
			return buff.toString();
		}
		return null;
	}
}
