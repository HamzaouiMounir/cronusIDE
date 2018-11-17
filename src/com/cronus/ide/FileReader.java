package com.cronus.ide;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class FileReader {
	private File file;
	private BufferedReader reader;
	private String[] allowedExtension = new String[] { ".java", ".html",
			".xml", ".txt", ".js", ".css", ".json" };
	private StringBuilder fileContent;
	public File getFile(){
		return this.file;
	}
	public void setFile(File file){
		this.file=file;
	}
	public StringBuilder getFileContent(){
		return this.fileContent;
	}
	public void readFile(){
		StringBuilder sb = new StringBuilder();
		try{
			this.reader= new BufferedReader(new java.io.FileReader(this.file));
			String line ="";
			
			while(true){
				if(this.reader.readLine()==null)
					break;
				line=this.reader.readLine();
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			
			this.reader.close();
			//System.out.println(sb.toString());
		}catch(IOException e){
			e.printStackTrace();
		}
		this.fileContent=sb;
	}
	/*public static void main(String[] args) {
		FileReader fr = new FileReader();
		fr.setFile(new File("/home/hamzaoui/workspace/Cronus/src/com/cronus/ide/SplashScreen.java"));
		fr.readFile();
		//TESTED WORKED
	}*/
}
