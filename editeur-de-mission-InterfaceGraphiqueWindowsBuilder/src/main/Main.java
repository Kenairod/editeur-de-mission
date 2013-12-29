package main;

import java.awt.EventQueue;

import view.Editeur;


public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editeur frame = new Editeur();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
