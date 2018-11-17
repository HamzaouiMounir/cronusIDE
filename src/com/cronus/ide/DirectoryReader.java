package com.cronus.ide;

import java.io.File;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class DirectoryReader {
	private String dirName;
	private File file;
	private File[] directory;
	private String filePath;
	public DirectoryReader(){
		
	}
	public DirectoryReader(File file){
		this.file=file;
	}
	public DirectoryReader(String directoryName, String filePath, File file,
			File[] directory) {
		this.filePath = filePath;
		this.dirName = directoryName;
		this.file = file;
		this.directory = directory;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File[] getDirectory() {
		return directory;
	}

	public int getDirectorySize() {
		return this.directory.length;
	}

	public void setDirectory(File[] directory) {
		this.directory = directory;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String fp) {
		this.filePath = fp;
	}

	public File[] loadDirectory(String path) {
		this.file = new File(path);
		this.directory = file.listFiles();
		return this.getDirectory();
	}
	public DefaultMutableTreeNode getCurrentDirTree(File [] directory,String name){
		name=name.substring(name.lastIndexOf("/")+1,name.length());
		DefaultMutableTreeNode parent = new DefaultMutableTreeNode(name);
		DefaultMutableTreeNode subParent=null , child=null;
		for(int i=0;i<directory.length;i++){
			subParent =new DefaultMutableTreeNode(directory[i].getName());
			if(directory[i].isDirectory()){
				
				loadSubParentDirectory(directory[i].listFiles(),subParent);	
			}	
			parent.add(subParent);
			
		}
		
		return parent;
	}
	public void loadSubParentDirectory(File[] directory , DefaultMutableTreeNode subParent){
		for(int i=0;i<directory.length;i++){
			if(directory[i].isDirectory()){
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(directory[i].getName());
				subParent.add(child);
				loadSubParentDirectory(directory[i].listFiles(), child);
			}else{
				subParent.add(new DefaultMutableTreeNode(directory[i].getName()));
			}
		}
	}
	public void loadFileTree(JTree tree , DefaultMutableTreeNode treeNode){
		tree.setModel(new DefaultTreeModel(treeNode));
	}
	
	public static void main(String[] args) {
		/*String directoryName;
		File file = new File(System.getProperty("user.dir"));
		File[] fileRoot, childDir;
		int dirNumber = 0, fileNumber = 0;
		fileRoot = file.listFiles();
		for (int i = 0; i < fileRoot.length; i++) {
			System.out.println(fileRoot[i].getName());
			if (fileRoot[i].isDirectory()) {
				childDir = fileRoot[i].listFiles();
				for (int j = 0; j < childDir.length; i++) {
					System.out.println("---" + childDir[j].getName());
				}
				dirNumber++;
			} else if (fileRoot[i].isFile())
				fileNumber++;

		}*/
		// System.out.println("N° of files : "+fileNumber+" || N° of directories :"+dirNumber);

		/*
		 * BufferedReader reader =null; try {
		 * 
		 * reader = new BufferedReader(new FileReader(fileRoot[1])); String line
		 * =null; while(true){ line = reader.readLine(); if(line==null) break; }
		 * } catch (IOException e) { // TODO: handle exception
		 * e.printStackTrace(); }
		 */
		/*DirectoryReader dir = new DirectoryReader();
		dir.loadDirectory(System.getProperty("user.dir"));*/
		//System.out.println(dir.getDirectorySize());
		//dir.getCurrentDirTree(dir.getDirectory());
		String workspacePath="/home/hamzaoui/workspace";
		
		

	}

}
