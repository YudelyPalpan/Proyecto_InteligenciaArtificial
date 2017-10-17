package com.guis.gatosraton.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormPrincipal extends JFrame {

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
					FormPrincipal frame = new FormPrincipal();
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
	public FormPrincipal() {
		setTitle("Los gatos y el rat\u00F3n");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormPrincipal.class.getResource("/com/guis/gatosraton/views/img/iconjuego.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 312, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLosGatosY = new JLabel("Elige a tu jugador");
		lblLosGatosY.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblLosGatosY.setHorizontalAlignment(SwingConstants.CENTER);
		lblLosGatosY.setBounds(0, 11, 296, 28);
		contentPane.add(lblLosGatosY);
		
		JButton btnGatos = new JButton("Gatos");
		btnGatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FormTablero frame = new FormTablero(9);
							frame.setVisible(true);
							FormPrincipal.this.setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnGatos.setIcon(new ImageIcon(FormPrincipal.class.getResource("/com/guis/gatosraton/views/img/gatito.png")));
		btnGatos.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnGatos.setBounds(36, 79, 228, 68);
		contentPane.add(btnGatos);
		
		JButton btnRaton = new JButton("Rat\u00F3n");
		btnRaton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FormTablero frame = new FormTablero(1);
							frame.setVisible(true);
							FormPrincipal.this.setVisible(false);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnRaton.setIcon(new ImageIcon(FormPrincipal.class.getResource("/com/guis/gatosraton/views/img/ratoncito.png")));
		btnRaton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnRaton.setBounds(36, 158, 228, 68);
		contentPane.add(btnRaton);
		setLocationRelativeTo(null);
	}
}
