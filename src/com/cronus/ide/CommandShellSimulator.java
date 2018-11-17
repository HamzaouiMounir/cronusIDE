package com.cronus.ide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandShellSimulator {
	private StringBuilder errors, output ;
	private boolean errorExist=false;
	public StringBuilder getOutput() {
		return this.output;
	}

	public void setOutput(StringBuilder output) {
		this.output = output;
	}

	public StringBuilder getErrors() {
		return this.errors;
	}
	
	public boolean getCompilationState(){
		return this.errorExist;
	}

	public void compile(String cmd) {
		Process p;
		String er=null,out=null;
		try {
			p = Runtime.getRuntime().exec(cmd);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));
			this.errors = new StringBuilder();
			while ((out= stdInput.readLine()) != null) {
				this.output.append(out);
				System.out.println(out);
			}
			while ((er = stdError.readLine()) != null) {
				this.errors.append(er);
				this.errors.append("");
				System.err.println(er);
			}
			if(!this.errors.toString().isEmpty()){
				//System.out.println("Not empty");
				this.errorExist=true;
				//System.out.println(this.getCompilationState());
			}
			// System.exit(0);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(-1);
		}
	}
	/*
	 * public static void main(String args[]) {
	 * 
	 * String s = null;
	 * 
	 * try { // run the Unix "ps -ef" command // using the Runtime exec method:
	 * Process p = Runtime.getRuntime().exec(
	 * "java -classpath /home/hamzaoui/workspace/test/ Test");
	 * 
	 * BufferedReader stdInput = new BufferedReader(new
	 * InputStreamReader(p.getInputStream()));
	 * 
	 * BufferedReader stdError = new BufferedReader(new
	 * InputStreamReader(p.getErrorStream()));
	 * 
	 * // read the output from the command
	 * System.out.println("Here is the standard output of the command:\n");
	 * while ((s = stdInput.readLine()) != null) { System.out.println(s); }
	 * 
	 * // read any errors from the attempted command
	 * System.out.println("Here is the standard error of the command (if any):\n"
	 * ); while ((s = stdError.readLine()) != null) { System.err.println(s); }
	 * 
	 * System.exit(0); } catch (IOException e) {
	 * System.out.println("exception happened - here's what I know: ");
	 * e.printStackTrace(); System.exit(-1); } }
	 */
}
