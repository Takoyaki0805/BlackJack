package main;

import javax.swing.JFrame;

public class Frame extends JFrame{
	public Frame(String title){
		setTitle(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);
		setContentPane(new Panel());
	}
}