package com.cronus.ide;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.FileChooserUI;

public class WorkspaceChooser extends JDialog implements ActionListener{
	Container c ;
	JButton wsChooser , ok , cancel;
	JTextField wsPath;
	public WorkspaceChooser(){
		JPanel pan1,pan2;
		pan1=new JPanel();
		pan2=new JPanel();
		JLabel lbl1= new JLabel("Choisir votre espace de travail");
		
		wsChooser = new JButton(new ImageIcon("assets/icons/open.png"));
		wsChooser.setContentAreaFilled(false);
		wsChooser.setOpaque(false);
		wsChooser.setBorderPainted(false);
		ok = new JButton("Ok");
		cancel = new JButton("Annuler");
		 wsPath = new JTextField();
		pan1.setLayout(new FlowLayout());
		wsPath.setColumns(30);
		wsPath.setEditable(false);
		pan1.add(wsPath);
		pan1.add(wsChooser);
		pan1.setBorder(BorderFactory.createTitledBorder(lbl1.getText()));
		pan2.setLayout(new FlowLayout());
		pan2.add(new JLabel("                                                                      "));
		pan2.add(ok);
		pan2.add(cancel);
		pan2.setBorder(BorderFactory.createEtchedBorder());
		c=getContentPane();
		c.setLayout(new BorderLayout());
		c.add(pan1,BorderLayout.CENTER);
		c.add(pan2,BorderLayout.PAGE_END);
		this.setTitle("Cronus 1.0 Beta");
		this.setSize(450,150);
		this.setResizable(false);
		this.setLocationRelativeTo(getParent());
		this.setVisible(true);
		ok.setEnabled(false);
		this.addActions();
	}
	private void addActions(){
		this.wsChooser.addActionListener(this);
		this.cancel.addActionListener(this);
		this.ok.addActionListener(this);
	}
	@Override 
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==wsChooser){
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			 int returnValue = fileChooser.showOpenDialog(null);
			 if (returnValue == JFileChooser.APPROVE_OPTION) {
		          File selectedFile = fileChooser.getSelectedFile();
		          wsPath.setText(selectedFile.getAbsolutePath());
		          System.out.println(selectedFile.getAbsolutePath());
		          ok.setEnabled(true);
		        }
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
		if(e.getSource()==ok){
			//appel au splash screen
			//instanciation de main frame avec le workspacePath
			new SplashScreen(this.wsPath.getText().toString());
			this.dispose();
		}
	}
	public static void main(String[] args) {
		new WorkspaceChooser();
	}
}
