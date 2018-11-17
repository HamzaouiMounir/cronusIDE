package com.cronus.ide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MainFrame extends JFrame implements TreeSelectionListener,
		ActionListener {
	JMenuBar mainMenu;
	JMenu fichier, editer, executer, aide;
	// Fichier JmenuItem
	JMenuItem nvProjet, opnProjet, opnFile, nvFile, save, saveas, exit;
	// Editer JmenuItem
	JMenuItem undo, redo, copy, paste, cut, selectAll;
	// Executer JmenuItem
	JMenuItem run, compile;
	// Aide JmenuItem
	JMenuItem usesTerms, licence, about;
	// JPanel
	JPanel fileExplorer, codeEditor, mainPanel, bottomPanel;
	// JLabels
	JLabel fe, ce, btm;
	// JScrollPane
	JScrollPane editorScrollPane;
	JTextPane mainEditor, outputScreen;
	JButton closeTab;
	// JList
	JList folders;
	DefaultListModel model;
	JTree workspaceExplorer;
	File[] files = File.listRoots();
	String workspacePath, selectedFilePath;
	StyleContext context, con;
	StyledDocument document, doc;
	CodeEditorTab codeEditorTab;
	private int tabCounter = 0;
	JFileChooser fileChooser;
	JPopupMenu popumenu,filepopup;
	CommandShellSimulator compiler;

	public MainFrame(String workspacePath) {
		this.workspacePath = workspacePath;
		// JSplitPane
		final JSplitPane divider = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		final JSplitPane verticalDivider = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		this.setTitle("Cronus Beta 1.0");
		this.initializeComponents();
		Container c = getContentPane();
		divider.setLeftComponent(fileExplorer);
		divider.setRightComponent(codeEditorTab);
		verticalDivider.setBottomComponent(bottomPanel);
		verticalDivider.setResizeWeight(0.2);
		divider.setResizeWeight(0.2);
		mainPanel.add(divider, BorderLayout.CENTER);
		mainPanel.setSize(500, 500);
		c.add(mainPanel, BorderLayout.CENTER);
		c.add(bottomPanel, BorderLayout.PAGE_END);
		setJMenuBar(mainMenu);
		mainMenu.setPreferredSize(new Dimension(500, 20));
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(getParent());
		setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		
	}

	protected void initializeComponents() {
		FileReader currentSelectedFile = new FileReader();
		mainMenu = new JMenuBar();
		fichier = new JMenu("Fichier");
		editer = new JMenu("Editer");
		executer = new JMenu("Execute");
		aide = new JMenu("Aide");
		folders = new JList();
		outputScreen = new JTextPane();
		setOutputScreenDocument(null,false,false);
		outputScreen.setPreferredSize(new Dimension(this.getWidth(), 100));
		outputScreen.setEditable(false);
		nvProjet = new myJMenuItem("Nouveau projet", new ImageIcon(
				"assets/icons/new.png"));
		opnProjet = new myJMenuItem("Ouvrir un dossier", new ImageIcon(
				"assets/icons/open.png"));
		opnFile = new myJMenuItem("Ouvrir un fichier", new ImageIcon(
				"assets/icons/file_edit.png"));
		nvFile = new myJMenuItem("Nouveau fichier", new ImageIcon(
				"assets/icons/newFile.png"));
		save = new myJMenuItem("Enregistrer", new ImageIcon(
				"assets/icons/disk.png"));
		saveas = new myJMenuItem("Enregistrer sous ..", new ImageIcon(
				"assets/icons/document_save.png"));
		exit = new myJMenuItem("Quitter", new ImageIcon(
				"assets/icons/Cancel.png"));
		fichier.add(nvProjet);
		fichier.add(opnProjet);
		fichier.add(opnFile);
		fichier.add(nvFile);
		fichier.addSeparator();
		fichier.add(save);
		fichier.add(saveas);
		fichier.addSeparator();
		fichier.add(exit);
		// --------------------------- EDIT
		undo = new myJMenuItem("Undo", new ImageIcon(
				"assets/icons/undo-icon.png"), "Ctrl+Z", 'Z');
		redo = new myJMenuItem("Redo", new ImageIcon(
				"assets/icons/redo-icon.png"), "Ctrl+W", 'W');
		copy = new myJMenuItem("Copier", new ImageIcon(
				"assets/icons/copy-icon.png"), "Ctrl+Z", 'Z');
		paste = new myJMenuItem("Coller", new ImageIcon(
				"assets/icons/paste-icon.png"), "Ctrl+Z", 'Z');
		cut = new myJMenuItem("Couper", new ImageIcon(
				"assets/icons/undo-icon.png"), "Ctrl+Z", 'Z');
		selectAll = new myJMenuItem("Selectionner Tout", new ImageIcon(
				"assets/icons/select.png"), "Ctrl+Z", 'Z');
		editer.add(undo);
		editer.add(redo);
		editer.addSeparator();
		editer.add(copy);
		editer.add(cut);
		editer.add(paste);
		editer.add(selectAll);
		run = new myJMenuItem("Exécuter", new ImageIcon("assets/icons/run.gif"));
		compile = new myJMenuItem("Compiler", new ImageIcon(
				"assets/icons/compile.png"));
		executer.add(compile);
		executer.add(run);
		usesTerms = new myJMenuItem("Terme d'utilisation", new ImageIcon(
				"assets/icons/help.png"));
		licence = new myJMenuItem("License Cronus 1.0", new ImageIcon(
				"assets/icons/check.png"));
		about = new myJMenuItem("A propos Cronus", new ImageIcon(
				"assets/icons/about.png"));
		//aide.add(usesTerms);
		aide.add(licence);
		aide.addSeparator();
		aide.add(about);
		mainMenu.add(fichier);
		mainMenu.add(editer);
		mainMenu.add(executer);
		mainMenu.add(aide);
		workspaceExplorer = new JTree();
		this.currentWorkspaceDirectory();
		fileExplorer = new JPanel();
		codeEditor = new JPanel();
		mainPanel = new JPanel();
		bottomPanel = new JPanel();
		fileExplorer.setLayout(new BorderLayout());
		codeEditor.setLayout(new FlowLayout());
		ce = new JLabel("Code Editor");
		fe = new JLabel("File Explorer");
		btm = new JLabel("Output");
		fileExplorer.setLayout(new BorderLayout());
		fileExplorer.add(workspaceExplorer, BorderLayout.CENTER);
		
		mainPanel.setLayout(new BorderLayout());
		bottomPanel.setBorder(BorderFactory.createEtchedBorder());
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.add(outputScreen, BorderLayout.CENTER);
		codeEditorTab = new CodeEditorTab();
		codeEditorTab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		workspaceExplorer.addTreeSelectionListener(this);
		closeTab = new JButton();
		closeTab.setIcon(new ImageIcon("assets/icons/Cancel.png"));
		closeTab.setOpaque(false);
		closeTab.setBorderPainted(false);
		closeTab.setBackground(getBackground());
		closeTab.setPreferredSize(new Dimension(16, 16));
		popumenu = new JPopupMenu();
		JMenuItem item;
		popumenu.add(item = new JMenuItem("Nouveau fichier", new ImageIcon(
				"assets/icons/new.png")));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		popumenu.add(item = new JMenuItem("Renommer", new ImageIcon(
				"assets/icons/new.png")));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		popumenu.add(item = new JMenuItem("Supprimer", new ImageIcon(
				"assets/icons/delete.gif")));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		// item.addActionListener(menuListener);
		popumenu.addSeparator();
		popumenu.add(item = new JMenuItem("Propriétés", new ImageIcon(
				"assets/icons/prop.png")));
		run.setEnabled(true);
		//run.setBackground(Color.LIGHT_GRAY);
		compile.setEnabled(true);
		//compile.setBackground(Color.LIGHT_GRAY);
		this.addActions();

	}

	protected void addActions() {
		this.nvProjet.addActionListener(this);
		this.opnFile.addActionListener(this);
		this.opnProjet.addActionListener(this);
		this.save.addActionListener(this);
		this.saveas.addActionListener(this);
		this.exit.addActionListener(this);
		this.undo.addActionListener(this);
		this.redo.addActionListener(this);
		this.copy.addActionListener(this);
		this.paste.addActionListener(this);
		this.selectAll.addActionListener(this);
		this.run.addActionListener(this);
		this.compile.addActionListener(this);
		this.usesTerms.addActionListener(this);
		this.licence.addActionListener(this);
		this.about.addActionListener(this);
		this.nvFile.addActionListener(this);

		this.workspaceExplorer.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(java.awt.event.MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (SwingUtilities.isRightMouseButton(arg0)) {
					int row = workspaceExplorer.getClosestRowForLocation(
							arg0.getX(), arg0.getY());
					workspaceExplorer.setSelectionRow(row);
					
					popumenu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nvProjet) {

			new NewProjectFrame(workspacePath);
		}
		if(e.getSource()==about){
			new AboutCronus();
		}
		if(e.getSource()==licence){
			new LicenseCronus();
		}
		if (e.getSource() == opnProjet) {
			fileChooser = new JFileChooser();
			fileChooser.setBackground(Color.DARK_GRAY);
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
			}

		}
		if (e.getSource() == opnFile) {
			fileChooser = new JFileChooser();
			fileChooser.setBackground(Color.DARK_GRAY);
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
				FileReader fr = new FileReader();
				fr.setFile(selectedFile);
				fr.readFile();
				this.setDocumentContent(fr.getFileContent());
				JTextPane ce = new JTextPane(document);
				JScrollPane sc = new JScrollPane(ce);
				codeEditorTab.addTab(selectedFile.getName(), sc);
				// JPanel customizedTabPanel = getCustomizedTabPanel(node
				// .getUserObject().toString());
				codeEditorTab.setSelectedIndex(codeEditorTab.getTabCount() - 1);
			}

		}
		if (e.getSource() == save) {
			fileChooser = new JFileChooser();
			fileChooser.setBackground(Color.DARK_GRAY);
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int returnValue = fileChooser.showSaveDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
			}

		}
		if (e.getSource() == exit) {
			this.dispose();
		}
		if (e.getSource() == nvFile) {
			this.setDocumentContent(null);
			DefaultMutableTreeNode node = new DefaultMutableTreeNode("File");
			JTextPane ce = new JTextPane(document);
			JScrollPane sc = new JScrollPane(ce);
			codeEditorTab.addTab("File", sc);
			codeEditorTab.setSelectedIndex(codeEditorTab.getTabCount() - 1);
			DefaultTreeModel model = (DefaultTreeModel) workspaceExplorer
					.getModel();
			DefaultMutableTreeNode root = (DefaultMutableTreeNode) model
					.getRoot();
			model.insertNodeInto(node, root, root.getChildCount());
		}
		if (e.getSource() == compile) {
			String s = null;
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) workspaceExplorer
					.getLastSelectedPathComponent();
			String fileToCompile = workspacePath.substring(0,
					workspacePath.lastIndexOf("/"))
					+ "/" + getSelectedFilePath(node.getUserObjectPath());
			compiler = new CommandShellSimulator();
			compiler.compile("javac " + fileToCompile);
			if(compiler.getCompilationState()==false){
				System.out.println(compiler.getCompilationState());
				setOutputScreenDocument(null,false,true);
					
			}
			else
				setOutputScreenDocument(compiler.getErrors(),true,false);
			
		}
		if(e.getSource()==run){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) workspaceExplorer
					.getLastSelectedPathComponent();
			String fileToCompile = workspacePath.substring(0,
					workspacePath.lastIndexOf("/"))
					+ "/" + getSelectedFilePath(node.getUserObjectPath());
			String classToCompile = node.getUserObject().toString().substring(0, node.getUserObject().toString().indexOf(".java"));
			String cmd = "java -classpath "+fileToCompile.substring(0, fileToCompile.lastIndexOf("/")+1)+" "+classToCompile;
			System.out.println(cmd);
			Executor exe = new Executor(cmd);
			exe.run();
			StringBuilder result = new StringBuilder();
			CommandShellSimulator shell =exe.getShell();
			result.append(shell.getOutput());
			setOutputScreenDocument(result, false, true);
			
		}
	}

	protected JPanel getCustomizedTabPanel(String tabTitle) {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));

		p.add(new JLabel(tabTitle));

		p.add(closeTab);
		return p;
	}

	protected void currentWorkspaceDirectory() {
		// workspacePath = "/home/hamzaoui/workspace";
		DirectoryReader dreader = new DirectoryReader();
		dreader.loadDirectory(workspacePath);
		dreader.loadFileTree(workspaceExplorer, dreader.getCurrentDirTree(
				dreader.getDirectory(), workspacePath));
	}

	public void setWorkspacePath(String wsp) {
		this.workspacePath = wsp;
	}

	public String getWorkSpacePath() {
		return this.workspacePath;
	}

	/*
	 * public static void main(String[] args) { new MainFrame(); }
	 */
	private void setOutputScreenDocument(StringBuilder content,boolean errors , boolean success) {
		con = new StyleContext();
		doc = new DefaultStyledDocument(con);
		Style style = con.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_LEFT);
		StyleConstants.setFontSize(style, 14);
		StyleConstants.setSpaceAbove(style, 4);
		StyleConstants.setSpaceBelow(style, 4);
		
		try {
			if(errors==true){
				StyleConstants.setForeground(style, Color.red);
				doc.insertString(doc.getLength(), content.toString(), style);
			}
			if(success==true){
				StyleConstants.setForeground(style, Color.green);
				doc.insertString(doc.getLength(), "Compilation éffectuée avec succés !!"
						+content.toString(), style);
			}
			

		} catch (BadLocationException badLocationException) {
			System.err.println("Oops");
		}
		outputScreen.setDocument(doc);

	}

	public void setDocumentContent(StringBuilder sb) {
		context = new StyleContext();
		document = new DefaultStyledDocument(context);
		Style style = context.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setAlignment(style, StyleConstants.ALIGN_LEFT);
		StyleConstants.setFontSize(style, 14);
		StyleConstants.setSpaceAbove(style, 4);
		StyleConstants.setSpaceBelow(style, 4);

		try {
			if (sb == null) {
				document.insertString(document.getLength(), "//Your code here",
						style);
			} else {

				document.insertString(document.getLength(), sb.toString(),
						style);
			}

		} catch (BadLocationException badLocationException) {
			System.err.println("Oops");
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == workspaceExplorer) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) workspaceExplorer
					.getLastSelectedPathComponent();
			selectedFilePath = getSelectedFilePath(workspaceExplorer
					.getSelectionPath().getPath());
			System.out.println(selectedFilePath);
			String p = workspacePath.substring(0,
					workspacePath.lastIndexOf("/"));
			File selectedFile = new File(p + "/" + selectedFilePath);
			if (selectedFile.isDirectory()) {
				// System.err.println("Dir");
			} else {
				System.out.println("This is a file");
				FileReader fr = new FileReader();
				fr.setFile(selectedFile);
				fr.readFile();
				System.out.println(fr.getFileContent().toString());
				// mainEditor.setText("");
				this.setDocumentContent(fr.getFileContent());
				JTextPane ce = new JTextPane(document);
				JScrollPane sc = new JScrollPane(ce);
				// codeEditorTab.addTab("File", sc);
				if (existedTab(node) == -1) {
					codeEditorTab.addTab(node.getUserObject().toString(), sc);
					JPanel customizedTabPanel = getCustomizedTabPanel(node
							.getUserObject().toString());
					codeEditorTab
							.setSelectedIndex(codeEditorTab.getTabCount() - 1);
				} else {
					codeEditorTab.setSelectedIndex(existedTab(node));
				}

				// setDocumentContent(context, document,null);
				// codeEditor.add(codeEditorTab);
				codeEditorTab.revalidate();
				codeEditorTab.validate();
				// mainPanel.revalidate();
				// mainPanel.validate();
				// this.pack();
				// editorScrollPane.add(codeEditor);
				// mainEditor.setCaretPosition(0);
			}
		}

	}

	private int existedTab(DefaultMutableTreeNode node) {
		for (int i = 0; i < this.codeEditorTab.getTabCount(); i++) {
			if (node.getUserObject().toString()
					.equals(codeEditorTab.getTabTitleAt(i).toString())) {
				return i;
			}
		}
		return -1;
	}

	public String getSelectedFilePath(Object[] paths) {
		String selectedFilePath = "";
		// paths = workspaceExplorer.getSelectionPath().getPath();
		for (int i = 0; i < paths.length; i++) {
			selectedFilePath += paths[i];
			if (i + 1 < paths.length) {
				selectedFilePath += File.separator;
			}
		}
		return selectedFilePath;
	}

}
