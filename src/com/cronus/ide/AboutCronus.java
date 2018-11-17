package com.cronus.ide;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutCronus extends JFrame{
	public AboutCronus(){
		Container c = getContentPane();
		JLabel lbl = new JLabel(new ImageIcon("assets/icons/about.jpg"));
		
		c.setLayout(new BorderLayout());
		c.add(lbl,BorderLayout.CENTER);
		
		setResizable(false);
		setSize(700,450);
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
	
}
