package com.cronus.ide;

public class Executor extends Thread {
	private String command;
	CommandShellSimulator simExecutor = new CommandShellSimulator();
	boolean running = false;
	public Executor(String command){
		this.command=command;
		
	}
	public String getCommandToExecute(){
		return this.command;
	}
	public CommandShellSimulator getShell(){
		return this.simExecutor;
	}
	public void execute(){
		this.simExecutor.compile(this.command);
	}
	public void setExecState(boolean running){
		this.running = running;
	}
	public void run(){
		while (running==true){
			this.execute();
		}
	}
	
}
