package com.guis.gatosraton.views;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.armedbear.lisp.LispObject;

import com.guis.gatosraton.test.ConvertirLisp;

import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;

public class FormTablero extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		setFont(new Font("Ubuntu", Font.PLAIN, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormTablero.class.getResource("/com/guis/gatosraton/views/img/iconjuego.png")));
		setTitle("Los gatos y el rat\u00F3n");
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
		
		JButton btnArribaDerecha = new JButton("");
		btnArribaDerecha.setBackground(new Color(100, 149, 237));
		btnArribaDerecha.setIcon(new ImageIcon(FormTablero.class.getResource("/com/guis/gatosraton/views/img/ad.png")));
		btnArribaDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LispObject nuevoEstado = convertidor.ejecutarAccion("AVANZAR-RATON-DERECHA");
				if(!nuevoEstado.printObject().equals("NIL")) {
					convertidor.setEActual(nuevoEstado);
					convertidor.convertirTablero(tablero, convertidor.getEActual());
					update(getGraphics());
					juegaGato();
					mostrarMensajeGanador();
				} else {
					JOptionPane.showMessageDialog(rootPane, "Jugada no valida", "Jugada no valida", JOptionPane.WARNING_MESSAGE);
				}
			
			}
		});
		btnArribaDerecha.setBounds(111, 287, 56, 59);
		panel_1.add(btnArribaDerecha);
		
		JButton btnArribaIzquierda = new JButton("");
		btnArribaIzquierda.setBackground(new Color(100, 149, 237));
		btnArribaIzquierda.setIcon(new ImageIcon(FormTablero.class.getResource("/com/guis/gatosraton/views/img/ai.png")));
		btnArribaIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LispObject nuevoEstado = convertidor.ejecutarAccion("AVANZAR-RATON-IZQUIERDA");
				if(!nuevoEstado.printObject().equals("NIL")) {
					convertidor.setEActual(nuevoEstado);
					convertidor.convertirTablero(tablero, convertidor.getEActual());
					update(getGraphics());
					juegaGato();
					mostrarMensajeGanador();
				} else {
					JOptionPane.showMessageDialog(rootPane, "Jugada no valida", "Jugada no valida", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnArribaIzquierda.setBounds(28, 287, 56, 59);
		panel_1.add(btnArribaIzquierda);
		
		JButton btnAbajoDerecha = new JButton("");
		btnAbajoDerecha.setBackground(new Color(100, 149, 237));
		btnAbajoDerecha.setIcon(new ImageIcon(FormTablero.class.getResource("/com/guis/gatosraton/views/img/rd.png")));
		btnAbajoDerecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LispObject nuevoEstado = convertidor.ejecutarAccion("RETRO-RATON-DERECHA");
				if(!nuevoEstado.printObject().equals("NIL")) {
					convertidor.setEActual(nuevoEstado);
					convertidor.convertirTablero(tablero, convertidor.getEActual());
					update(getGraphics());
					juegaGato();
					mostrarMensajeGanador();
				} else {
					JOptionPane.showMessageDialog(rootPane, "Jugada no valida", "Jugada no valida", JOptionPane.WARNING_MESSAGE);
				}
			
			}
		});
		btnAbajoDerecha.setBounds(111, 372, 56, 59);
		panel_1.add(btnAbajoDerecha);
		
		JButton btnAbajoIzquierda = new JButton("");
		btnAbajoIzquierda.setBackground(new Color(100, 149, 237));
		btnAbajoIzquierda.setIcon(new ImageIcon(FormTablero.class.getResource("/com/guis/gatosraton/views/img/ri.png")));
		btnAbajoIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LispObject nuevoEstado = convertidor.ejecutarAccion("RETRO-RATON-IZQUIERDA");
				if(!nuevoEstado.printObject().equals("NIL")) {
					convertidor.setEActual(nuevoEstado);
					convertidor.convertirTablero(tablero, convertidor.getEActual());
					update(getGraphics());
					juegaGato();
					mostrarMensajeGanador();
				} else {
					JOptionPane.showMessageDialog(rootPane, "Jugada no valida", "Jugada no valida", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnAbajoIzquierda.setBounds(28, 372, 56, 59);
		panel_1.add(btnAbajoIzquierda);
		
		JButton btnNuevoJuego = new JButton("Nuevo Juego");
		btnNuevoJuego.setBackground(new Color(100, 149, 237));
		btnNuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				convertidor.iniciarJuego();
				juegaGato();
				btnAbajoDerecha.setEnabled(true);
				btnAbajoIzquierda.setEnabled(true);
				btnArribaDerecha.setEnabled(true);
				btnArribaIzquierda.setEnabled(true);
				
			}
		});
		btnNuevoJuego.setBounds(28, 22, 139, 45);
		panel_1.add(btnNuevoJuego);
		btnAbajoDerecha.setEnabled(false);
		btnAbajoIzquierda.setEnabled(false);
		btnArribaDerecha.setEnabled(false);
		btnArribaIzquierda.setEnabled(false);
	}
	
	private void juegaGato() {
		convertidor.setEActual(convertidor.jugadaMinimax());
		convertidor.convertirTablero(tablero, convertidor.getEActual());
		update(getGraphics());
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
	
	private void mostrarMensajeGanador() {
		String mensaje = convertidor.jugadorGanador();
		if (mensaje != null) {
			JOptionPane.showMessageDialog(contentPane, mensaje);
		}
	}
	private JButton tablero [][] = new JButton[8][8];
	private JPanel panel;
	private ConvertirLisp convertidor;
}
