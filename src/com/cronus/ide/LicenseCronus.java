package com.cronus.ide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class LicenseCronus extends JFrame{
	public LicenseCronus(){
		StyleContext con = new StyleContext();
		DefaultStyledDocument doc = new DefaultStyledDocument(con);
		Style style = con.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_CENTER);
		StyleConstants.setFontSize(style, 18);
		StyleConstants.setSpaceAbove(style, 5);
		StyleConstants.setSpaceBelow(style, 9);
		StyleConstants.setFontFamily(style, "Ubuntu");
		StyleConstants.setForeground(style, Color.WHITE);
		StyleConstants.setBold(style, true);
		Container c = getContentPane();
		
		try {
			doc.insertString(doc.getLength(), "\n Cronus est mis en version d'essai , cette license est totalement gratuite . " +
					"\n \n Pour plus d'informations , vous pouvez visiter notre site web : www.cronus.io",
					style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JTextPane licence = new JTextPane(doc);
		//JLabel lbl = new JLabel(new ImageIcon("assets/icons/about.jpg"));
		licence.setEditable(false);
		licence.setBackground(Color.DARK_GRAY);
		c.setLayout(new BorderLayout());
		c.add(licence,BorderLayout.CENTER);
		setResizable(false);
		setSize(450,250);
		setLocationRelativeTo(getParent());
		setTitle("License Cronus Beta ..");
		setVisible(true);
	}
	
}
