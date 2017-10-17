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
import java.awt.Point;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class FormTablero extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String options[] = {"Gatos", "Raton"};
					String opcion = (String) 
							JOptionPane.showInputDialog(null, "Elije jugador",
														null, JOptionPane.QUESTION_MESSAGE, null, 
														options, 1);
					
					FormTablero frame = new FormTablero((opcion.equals("Gatos") ? 1 : 9));
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
	public FormTablero(int jugador) {
		
		if(jugador == 9) {
			iniciarPosGatos();
		}
		
		setFont(new Font("Ubuntu", Font.PLAIN, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormTablero.class.getResource("/com/guis/gatosraton/views/img/iconjuego.png")));
		setTitle("Inteligencia Artificial - Esrategia MINIMAX - Los gatos y el rat\u00F3n");
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
		
		
		/********/
		rdbtnGato_1.setEnabled(false);
		rdbtnGato_2.setEnabled(false);
		rdbtnGato_3.setEnabled(false);
		rdbtnGato_4.setEnabled(false);
		
		/********/
		
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
					mostrarTurno();
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
					mostrarTurno();
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
				LispObject nuevoEstado;
				if(jugador == 1) {
					nuevoEstado = convertidor.ejecutarAccion("RETRO-RATON-DERECHA");
				} else {
					Point p = posGato[gatoElegido()];
					nuevoEstado = convertidor.ejecutarAccion("AVANZAR-GATO-DERECHA",p.x, p.y);

				}
				if(!nuevoEstado.printObject().equals("NIL")) {
					convertidor.setEActual(nuevoEstado);
					convertidor.convertirTablero(tablero, convertidor.getEActual());
					mostrarTurno();
					update(getGraphics());
					if(jugador == 1) {
						juegaGato();
					} else {
						moverGatoElegido("AVANZAR-GATO-DERECHA");
						
						juegaRaton();
					}
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
				LispObject nuevoEstado;
				if(jugador == 1) {
					nuevoEstado = convertidor.ejecutarAccion("RETRO-RATON-IZQUIERDA");
				} else {
					Point p = posGato[gatoElegido()];
					nuevoEstado = convertidor.ejecutarAccion("AVANZAR-GATO-IZQUIERDA",p.x, p.y);

				}
				if(!nuevoEstado.printObject().equals("NIL")) {
					convertidor.setEActual(nuevoEstado);
					convertidor.convertirTablero(tablero, convertidor.getEActual());
					mostrarTurno();
					update(getGraphics());
					if(jugador == 1) {
						juegaGato();
					} else {
						moverGatoElegido("AVANZAR-GATO-IZQUIERDA");
						juegaRaton();
					}
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
				int resultado = JOptionPane.showConfirmDialog(rootPane, "¿Desea Iniciar el juego?");
				
				if(resultado != JOptionPane.CANCEL_OPTION) {
					if(jugador == 1) {
						int turno = (resultado == JOptionPane.YES_OPTION) ? 9 : 1;
						convertidor.iniciarJuego(turno);
						convertidor.convertirTablero(tablero, convertidor.getEActual());
						mostrarTurno();
						if(turno == 1) {
							juegaGato();
						}
						btnArribaDerecha.setEnabled(true);
						btnArribaIzquierda.setEnabled(true);

					} else {
						iniciarPosGatos();
						int turno = (resultado == JOptionPane.YES_OPTION) ? 1 : 9;
						convertidor.iniciarJuego(turno);
						convertidor.convertirTablero(tablero, convertidor.getEActual());
						mostrarTurno();
						if(turno == 9) {
							juegaRaton();
						}
						btnArribaDerecha.setEnabled(false);
						btnArribaIzquierda.setEnabled(false);

					}

					btnAbajoDerecha.setEnabled(true);
					btnAbajoIzquierda.setEnabled(true);
				}
			}
		});
		btnNuevoJuego.setBounds(28, 22, 139, 45);
		panel_1.add(btnNuevoJuego);
		btnAbajoDerecha.setEnabled(false);
		btnAbajoIzquierda.setEnabled(false);
		btnArribaDerecha.setEnabled(false);
		btnArribaIzquierda.setEnabled(false);
		
		lblTurno = new JLabel("Turno:");
		lblTurno.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurno.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTurno.setBounds(28, 88, 139, 39);
		panel_1.add(lblTurno);
		
		rdbtnGato_1 = new JRadioButton("Gato 1");
		rdbtnGato_1.setSelected(true);
		buttonGroup.add(rdbtnGato_1);
		rdbtnGato_1.setBounds(38, 155, 109, 23);
		panel_1.add(rdbtnGato_1);
		
		rdbtnGato_2 = new JRadioButton("Gato 2");
		buttonGroup.add(rdbtnGato_2);
		rdbtnGato_2.setBounds(38, 181, 109, 23);
		panel_1.add(rdbtnGato_2);
		
		rdbtnGato_3 = new JRadioButton("Gato 3");
		buttonGroup.add(rdbtnGato_3);
		rdbtnGato_3.setBounds(38, 210, 109, 23);
		panel_1.add(rdbtnGato_3);
		
		rdbtnGato_4 = new JRadioButton("Gato 4");
		buttonGroup.add(rdbtnGato_4);
		rdbtnGato_4.setBounds(38, 236, 109, 23);
		panel_1.add(rdbtnGato_4);
	}
	
	private void juegaGato() {
		convertidor.setEActual(convertidor.jugadaMinimax());
		convertidor.convertirTablero(tablero, convertidor.getEActual());
		mostrarTurno();
		update(getGraphics());
	}
	
	private void juegaRaton() {
		convertidor.setEActual(convertidor.jugadaMinimaxRaton());
		System.out.println(convertidor.getEActual().printObject());
		convertidor.convertirTablero(tablero, convertidor.getEActual());
		mostrarTurno();
		update(getGraphics());
		
	}
	
	private void llenarTablero (int xRaton, int yRaton) {
		boolean color = true;
		for( int i = 0; i < 8; i++) {
			for(int j = 0; j<8 ; j++) {
				tablero[i][j] = new JButton();
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
	
	private void iniciarPosGatos() {
		posGato[0] = new Point(0, 0);
		posGato[1] = new Point(0, 2);
		posGato[2] = new Point(0, 4);
		posGato[3] = new Point(0, 6);
	}
	private void mostrarTurno() {
		String turno = (convertidor.getEActual().NTH(1).printObject().equals("1")) ? "Gato" : "Raton";
		lblTurno.setText("Turno: " + turno);
	}
	
	private void moverGatoElegido(String accion) {
		switch(accion) {
		case "AVANZAR-GATO-IZQUIERDA":
			posGato[gatoElegido()].translate(1, -1);
			break;
		case "AVANZAR-GATO-DERECHA":
			posGato[gatoElegido()].translate(1, 1);
			break;
		}
	}
	private int gatoElegido() {
		if(rdbtnGato_1.isSelected()) {
			return 0;
		} else if(rdbtnGato_2.isSelected()) {
			return 1;
		} else if(rdbtnGato_3.isSelected()) {
			return 2;
		} else {
			return 3;
		}
	}
	private JRadioButton rdbtnGato_1;
	private JRadioButton rdbtnGato_2;
	private JRadioButton rdbtnGato_3;
	private JRadioButton rdbtnGato_4;
	private Point posGato[] = new Point[4];
	private JLabel lblTurno;
	private JButton tablero [][] = new JButton[8][8];
	private JPanel panel;
	private ConvertirLisp convertidor;
	private final ButtonGroup buttonGroup = new ButtonGroup();
}
