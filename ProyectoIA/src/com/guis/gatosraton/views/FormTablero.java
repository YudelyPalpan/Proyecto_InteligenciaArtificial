package com.guis.gatosraton.views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JButton;

public class FormTablero extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormTablero frame = new FormTablero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormTablero() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBounds(22, 11, 480, 480);
		panel.setLayout(new GridLayout(8, 8, 1, 1));
		
		llenarTablero(7,3);
		contentPane.setLayout(null);
		contentPane.add(panel);
	}
	
	
	private void llenarTablero (int xRaton, int yRaton) {
		boolean color = true;
		for( int i = 0; i < 8; i++) {
			for(int j = 0; j<8 ; j++) {
				tablero[i][j] = new JButton("[" + i + "," + j + "]");
				if((i == 0 && j % 2 == 0) || (xRaton == i && yRaton == j)) {
					tablero[i][j].setEnabled(true);
					tablero[i][j].setText(tablero[i][j].getText() + "G");
				} else {
					tablero[i][j].setEnabled(false);
				}
				if(color) {
					tablero[i][j].setBackground(Color.WHITE);
				} else {
					tablero[i][j].setBackground(Color.BLACK);
				}
				color = !color;
				panel.add(tablero[i][j]);
			}
			color = !color;
		}
		
		tablero[xRaton][yRaton].setText(tablero[xRaton][yRaton].getText() + "R");
	}
	private JButton tablero [][] = new JButton[8][8];
	private JPanel panel;
	private Point posRaton;
}
