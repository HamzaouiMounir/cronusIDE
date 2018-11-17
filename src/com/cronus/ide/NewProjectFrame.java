package com.cronus.ide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewProjectFrame extends JDialog implements ActionListener {
	JTextField projectNameTxt , defaultWorkSpaceDest;
	JComboBox<String> projectTypeList;
	JFileChooser workspaceDestination;
	JButton chooseWS,finish,cancel;
	JPanel pan1,pan2,pan3,pan4,pan5;
	JCheckBox defaultWorkSpaceDirectory;
	Container c;
	private String currentWorkspacePath ;
	public NewProjectFrame(String currentWorkspacePath) {
		this.currentWorkspacePath = currentWorkspacePath;
		this.initializeComponents();
			this.setSize(new Dimension(450,420));
			this.setLocationRelativeTo(getParent());
			//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setTitle("Nouveau Projet");
			
			this.setResizable(false);
			this.setModal(true);
			this.setLocationRelativeTo(getParent());
			setVisible(true);
			
	}
	public void initializeComponents(){
		JLabel createNewProject , projectName , enterProjectName , projectType ,useCurrentWorkSpace ;
		c=getContentPane();
		createNewProject = new JLabel("Créer un nouveau projet");
		enterProjectName = new JLabel("\t \t \t Entrer un nom pour votre projet");
		projectName = new JLabel("Nom projet");
		projectType = new JLabel("Choisir le type de votre projet");
		useCurrentWorkSpace = new JLabel("<html><body>Utiliser l'esapce de travail par default</body></html>");
		useCurrentWorkSpace.setAlignmentX(-1);
		projectNameTxt = new JTextField();
		defaultWorkSpaceDest = new JTextField();
		defaultWorkSpaceDirectory = new JCheckBox("<html><body>Utiliser l'esapce de travail par default</body></html>");
		defaultWorkSpaceDirectory.isSelected();
		projectTypeList = new JComboBox<String>();
		projectTypeList.addItem("Java");
		projectTypeList.addItem("HTML");
		finish = new JButton("Terminer");
		cancel = new JButton("Annuler");
		chooseWS = new JButton(new ImageIcon("assets/icons/open.png"));
		chooseWS.setOpaque(false);
		chooseWS.setContentAreaFilled(false);
		chooseWS.setBorderPainted(false);
		chooseWS.setSize(16, 16);
		c.setLayout(new GridLayout(4,1));
		pan1 = new JPanel();
		pan1.setLayout(new GridLayout(2,1));
		pan1.add(createNewProject);
		pan1.add(enterProjectName);
		pan1.setBackground(Color.white);
		c.add(pan1);
		pan2 = new JPanel();
		pan2.setLayout(new GridLayout(2,2));
		pan2.add(projectName);
		pan2.add(projectNameTxt);
		pan2.add(projectType);
		pan2.add(projectTypeList);
		c.add(pan2);
		//c.add(new JSeparator(SwingConstants.HORIZONTAL));
		pan3 = new JPanel();
		pan3.setLayout(new GridLayout(2,1));
		JPanel pan31 = new JPanel();
		pan31.setLayout(new FlowLayout());
		pan31.add(new JLabel());
		pan31.add(defaultWorkSpaceDirectory);
		pan3.add(pan31);
		JPanel pan32 = new JPanel();
		pan32.setLayout(new FlowLayout());
		pan32.add(new JLabel());
		defaultWorkSpaceDest.setColumns(20);
		pan32.add(defaultWorkSpaceDest);
		pan32.add(chooseWS);
		pan3.add(pan32);
		pan3.setBorder(BorderFactory.createTitledBorder("Espace de travail"));
		c.add(pan3);
		JPanel p4Parent =new JPanel();
		p4Parent.setLayout(new BorderLayout());
		pan4 = new JPanel();
		pan4.setBorder(BorderFactory.createEtchedBorder());
		pan4.setLayout(new FlowLayout());
		pan4.setSize(this.getWidth(), 10);
		pan4.add(new JLabel("                           "));
		pan4.add(new JLabel("                           "));
		pan4.add(cancel , FlowLayout.RIGHT);
		pan4.add(new JLabel());
		pan4.add(finish, FlowLayout.RIGHT);
		p4Parent.add(pan4,BorderLayout.PAGE_END);
		c.add(p4Parent);
		defaultWorkSpaceDest.setEditable(false);
		chooseWS.setEnabled(false);
		defaultWorkSpaceDirectory.setSelected(true);
		defaultWorkSpaceDirectory.addActionListener(this);
		finish.addActionListener(this);
		cancel.addActionListener(this);
		chooseWS.addActionListener(this);
		defaultWorkSpaceDirectory.addActionListener(this);
		defaultWorkSpaceDest.setText(this.currentWorkspacePath);
	}
	public String getCurrentWorkspacePath(){
		return this.currentWorkspacePath;
	}
	public void setCurrentWorkspacePath(String cwp){
		this.currentWorkspacePath=cwp;
	}
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==cancel){
			this.dispose();
		}
		if(e.getSource()==chooseWS){
			 JFileChooser fileChooser = new JFileChooser();
			 fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			 int returnValue = fileChooser.showOpenDialog(null);
			 if (returnValue == JFileChooser.APPROVE_OPTION) {
		          File selectedFile = fileChooser.getSelectedFile();
		          defaultWorkSpaceDest.setText(selectedFile.getAbsolutePath());
		          System.out.println(selectedFile.getAbsolutePath());
		        }
		}
		if(e.getSource()==defaultWorkSpaceDirectory){
			if(defaultWorkSpaceDirectory.isSelected()==false){
				chooseWS.setEnabled(true);
				defaultWorkSpaceDest.setEditable(true);
			}else{
				chooseWS.setEnabled(false);
				defaultWorkSpaceDest.setEditable(false);
			}
		}
	}
	/*public static void main(String ... args){
		new NewProjectFrame("Path");
	}*/
}
