/**
 * @(#)myJMenuItem.java
 *
 *
 * @BELGUITH Mouhieddine 
 * @version 1.00 2015/11/13
 */
 //package mblg;
package com.cronus.ide;
import javax.swing.*;
import java.awt.*;

public class myJMenuItem extends JMenuItem
{
	JLabel textLabel=new JLabel();
	JLabel iconLabel=new JLabel();
	JLabel shortcutLabel=new JLabel();
	
	
	
    public myJMenuItem(String text)
    {
    	    	textLabel.setFont(new Font("Tahoma",Font.PLAIN,13));
    	shortcutLabel.setFont(new Font("Tahoma",Font.PLAIN,13));
    	
    	setBackground(new Color(253,253,253));
    	
    	iconLabel.setText("");iconLabel.setHorizontalTextPosition(SwingConstants.LEFT);
    	textLabel.setText("   "+text);//textLabel.setHorizontalTextPosition(SwingConstants.LEFT);
    	shortcutLabel.setText("");shortcutLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
    	
    	iconLabel.setPreferredSize(new Dimension(25,25));
    	iconLabel.setOpaque(true);
    	iconLabel.setBackground(new Color(240,240,240));
    	
    	shortcutLabel.setForeground(Color.LIGHT_GRAY);
    	
    	//new JMenuItem()
    		setBorder(null);
    		
    	setLayout(new BorderLayout());
    	setPreferredSize(new Dimension(200,25));
    	add("West",iconLabel);
    	add("Center",textLabel);
    	add("East",shortcutLabel);
    }
    
    
    public myJMenuItem(String text, String shortcutText, char shortcut)
    {	
    	textLabel.setFont(new Font("Tahoma",Font.PLAIN,13));
    	shortcutLabel.setFont(new Font("Tahoma",Font.PLAIN,13));
    	
    	setBackground(new Color(253,253,253));
    	
    	iconLabel.setText("");iconLabel.setHorizontalTextPosition(SwingConstants.LEFT);
    	textLabel.setText("   "+text);//textLabel.setHorizontalTextPosition(SwingConstants.LEFT);
    	shortcutLabel.setText(shortcutText+"   ");shortcutLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
    	
    	iconLabel.setPreferredSize(new Dimension(25,25));
    	iconLabel.setOpaque(true);
    	iconLabel.setBackground(new Color(243,243,243));
    	
    	shortcutLabel.setForeground(Color.LIGHT_GRAY);
    	
    	//new JMenuItem()
    		setBorder(null);
    		
    	setMnemonic(shortcut);
    	setLayout(new BorderLayout());
    	setPreferredSize(new Dimension(100,25));
    	add("West",iconLabel);
    	add("Center",textLabel);
    	add("East",shortcutLabel);
    }
    
    public myJMenuItem(String text, ImageIcon icon, String shortcutText, char shortcut)
    {
    	textLabel.setFont(new Font("Tahoma",Font.PLAIN,13));
    	shortcutLabel.setFont(new Font("Tahoma",Font.PLAIN,13));
    	
    	setBackground(new Color(253,253,253));
    	
    	iconLabel.setIcon(icon);iconLabel.setHorizontalTextPosition(SwingConstants.LEFT);
    	textLabel.setText("   "+text);//textLabel.setHorizontalTextPosition(SwingConstants.LEFT);
    	shortcutLabel.setText(shortcutText+"   ");shortcutLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
    	
    	iconLabel.setPreferredSize(new Dimension(25,25));
    	iconLabel.setOpaque(true);
    	iconLabel.setBackground(new Color(243,243,243));
    	
    	shortcutLabel.setForeground(Color.LIGHT_GRAY);
    	
    	//new JMenuItem().
    	setBorder(null);
    	setMnemonic(shortcut);
    	setLayout(new BorderLayout());
    	setPreferredSize(new Dimension(200,25));
    	//setMinimumSize(new Dimension(25,25));
    	add("West",iconLabel);
    	add("Center",textLabel);
    	add("East",shortcutLabel);
    } 
    public myJMenuItem(String text, ImageIcon icon)
    {
    	textLabel.setFont(new Font("Tahoma",Font.PLAIN,13));
    	shortcutLabel.setFont(new Font("Tahoma",Font.PLAIN,13));
    	
    	setBackground(new Color(253,253,253));
    	
    	iconLabel.setIcon(icon);iconLabel.setHorizontalTextPosition(SwingConstants.LEFT);
    	textLabel.setText("   "+text);//textLabel.setHorizontalTextPosition(SwingConstants.LEFT);
    	
    	
    	iconLabel.setPreferredSize(new Dimension(25,25));
    	iconLabel.setOpaque(true);
    	iconLabel.setBackground(new Color(243,243,243));
    	
    	shortcutLabel.setForeground(Color.LIGHT_GRAY);
    	
    	//new JMenuItem().
    	setBorder(null);
    	
    	setLayout(new BorderLayout());
    	setPreferredSize(new Dimension(200,25));
    	//setMinimumSize(new Dimension(25,25));
    	add("West",iconLabel);
    	add("Center",textLabel);
    	add("East",shortcutLabel);
    } 
}