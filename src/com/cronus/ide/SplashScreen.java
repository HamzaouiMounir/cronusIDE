package com.cronus.ide;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class SplashScreen extends JFrame {
	JProgressBar progressIntro;
	JPanel imageHolder;
	Container c ; 
	String workspacePath;
	JLabel introImg = new JLabel();
	public SplashScreen(String workspacePath) {
		this.workspacePath=workspacePath;
		imageHolder = new JPanel();
		imageHolder.setPreferredSize(new Dimension(700,350));
		imageHolder.setLayout(new BorderLayout());
		progressIntro = new JProgressBar();
		progressIntro.setPreferredSize(new Dimension(500,30));
		introImg.setIcon(new ImageIcon("assets/icons/splash.jpg"));
		imageHolder.add(introImg, BorderLayout.CENTER);
		c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(imageHolder,BorderLayout.CENTER);
		c.add(progressIntro,BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 500);
		this.setUndecorated(true);
		setLocationRelativeTo(getParent());
		this.setAlwaysOnTop(true);
		progressIntro.setMaximum(1000);
		progressIntro.setSize(new Dimension(700,20));
		
		new progress(this).start();
		setVisible(true);
		

	}
	
	public String getWokSpacePath(){
		return this.workspacePath;
	}
	

	/*public static void main(String[] args) {
		
			new SplashScreen();
			
	}*/
	
}



class progress extends Thread
{
	
	SplashScreen sp;
	public progress(SplashScreen sp)
	
	{
		this.sp=sp;
	}
	
	public void run()
	{
		MainFrame cronus = new MainFrame(sp.getWokSpacePath());
		
		for(int i=0;i<100;i++)
		{
			sp.progressIntro.setValue(sp.progressIntro.getValue()+80);
			try
			{
				new Thread().sleep(100);
				
				
			}
			catch(Exception e){
				
			}
		}
		sp.dispose();
		cronus.setVisible(true);
		
		
	}
}
