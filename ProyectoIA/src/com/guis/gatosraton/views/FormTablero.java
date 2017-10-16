package com.guis.gatosraton.views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.armedbear.lisp.LispObject;

import com.guis.gatosraton.test.ConvertirLisp;

import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		convertidor = new ConvertirLisp("src/lisp/gatos_raton.lisp");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel = new JPanel();
		panel.setBounds(22, 11, 480, 480);
		panel.setLayout(new GridLayout(8, 8, 1, 1));
		
		llenarTablero(7,3);
		convertidor.convertirTablero(tablero,convertidor.getEActual());
		contentPane.setLayout(null);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(512, 11, 191, 480);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnArribaDerecha = new JButton("Arriba Derecha");
		btnArribaDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LispObject nuevoEstado = convertidor.ejecutarAccion("AVANZAR-RATON-DERECHA");
				System.out.println("Nuevo estado");
				System.out.println(nuevoEstado.printObject());
				convertidor.setEActual(nuevoEstado);
				System.out.println("Estado actual");
				System.out.println(convertidor.getEActual().printObject());
				convertidor.convertirTablero(tablero, convertidor.getEActual());
				update(getGraphics());
			}
		});
		btnArribaDerecha.setBounds(27, 65, 139, 45);
		panel_1.add(btnArribaDerecha);
		
		JButton btnArribaIzquierda = new JButton("Arriba Izquierda");
		btnArribaIzquierda.setBounds(24, 121, 142, 45);
		panel_1.add(btnArribaIzquierda);
		
		JButton btnAbajoDerecha = new JButton("Abajo Derecha");
		btnAbajoDerecha.setBounds(27, 177, 139, 45);
		panel_1.add(btnAbajoDerecha);
		
		JButton btnAbajoIzquierda = new JButton("Abajo Izquierda");
		btnAbajoIzquierda.setBounds(27, 233, 139, 45);
		panel_1.add(btnAbajoIzquierda);
	}
	
	
	private void llenarTablero (int xRaton, int yRaton) {
		boolean color = true;
		for( int i = 0; i < 8; i++) {
			for(int j = 0; j<8 ; j++) {
				tablero[i][j] = new JButton();
				/*if((i == 0 && j % 2 == 0) || (xRaton == i && yRaton == j)) {
					tablero[i][j].setEnabled(true);
					tablero[i][j].setText(tablero[i][j].getText() + "G");
				} else {
					tablero[i][j].setEnabled(false);
				}*/
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
	private ConvertirLisp convertidor;
}
