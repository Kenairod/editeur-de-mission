package main;

import java.awt.EventQueue;

import view.Fenetre;


public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetre frame = new Fenetre();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
