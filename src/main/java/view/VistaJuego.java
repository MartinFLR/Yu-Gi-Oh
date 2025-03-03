package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import controller.ControladorJuego;
import view.componentes.BotonPregunta;
import view.componentes.FuentePersonalizada;
import view.componentes.PanelEscalon;
import view.componentes.PanelJugadorFinal;
import view.componentes.PanelJugadorNormal;

public class VistaJuego extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorJuego c;

	private JLabel lblprePregunta;
	private BotonPregunta btnpreRespuesta1 = new BotonPregunta();
	private BotonPregunta btnpreRespuesta2 = new BotonPregunta();
	private BotonPregunta btnpreRespuesta3 = new BotonPregunta();
	private BotonPregunta btnpreRespuesta4 = new BotonPregunta();
	
	private JPanel panelAproximacion;
	private JButton btnaproxEnviar;
	private JFormattedTextField txtaproxRespuesta;
	
	private JPanel panelColumna;
	private JPanel panelJugadores;
	
	private JLabel lblaproxPregunta;
	private DefaultTableModel defTable;
	
	private JPanel panelFinal;
	
	// ATRIBUTOS PARA CADA JUGADOR Y ESCALON
	private ArrayList<PanelEscalon> escalones = new ArrayList<PanelEscalon>();
	private ArrayList<PanelJugadorNormal> jugadorNormal = new ArrayList<PanelJugadorNormal>();
	private ArrayList<PanelJugadorFinal> jugadorFinal = new ArrayList<PanelJugadorFinal>();
	private JPanel panelPregunta_1;
	private JPanel panelGanador;
	private JButton btnGanadorContinuar;
	private JLabel lblGanadorTexto;
	private JLabel lblGanadorImagen;
	private JScrollPane sc;
	
	private JLabel background;
	
	private JButton btnprePausa;
	private JButton btnaproxPausa;
	
	
	public VistaJuego(ControladorJuego c) {
		this.setC(c);
		setTitle("8 ESCALONES");
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.addKeyListener(c);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		
        
        //
		// PANEL GANADOR
		//
		
		panelGanador = new JPanel();
		panelGanador.setBounds(407, 140, 450, 400);
		contentPane.add(panelGanador);
		panelGanador.setLayout(null);
		
		lblGanadorImagen = new JLabel("");
		lblGanadorImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanadorImagen.setVerticalAlignment(SwingConstants.CENTER);
		lblGanadorImagen.setBounds(150, 11, 150, 150);
		panelGanador.add(lblGanadorImagen);
		
		JLabel lblTexto = new JLabel("!Felicidades, has ganado los 8 ESCALONES!");
		lblTexto.setFont(new FuentePersonalizada().getFuente("fonts/Helvetica Bold Condensed.otf", 20f));
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setBounds(10, 189, 430, 50);
		panelGanador.add(lblTexto);
		
		lblGanadorTexto = new JLabel("");
		lblGanadorTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanadorTexto.setVerticalAlignment(SwingConstants.TOP);
		lblGanadorTexto.setFont(new FuentePersonalizada().getFuente("fonts/YatraOne-Regular.ttf", 40f));
		lblGanadorTexto.setBounds(10, 250, 430, 90);
		panelGanador.add(lblGanadorTexto);
		
		btnGanadorContinuar = new JButton("SALIR");
		btnGanadorContinuar.setBounds(165, 349, 120, 40);
		btnGanadorContinuar.setCursor(new Cursor(HAND_CURSOR));
		panelGanador.add(btnGanadorContinuar);
		
		panelGanador.setVisible(false);
		
		//
		// PANEL COLUMNAS
		//
		
		panelColumna = new JPanel();
		panelColumna.setBackground(Color.BLACK);
		panelColumna.setBounds(0, 0, 85, 683);
		contentPane.add(panelColumna);
		GridLayout gridLayoutColumna = new GridLayout(8, 1);
        gridLayoutColumna.setVgap(2); 
		panelColumna.setLayout(gridLayoutColumna);
		
		// --- FALTA CAMBIAR COLOR A LOS ESCALONES Y LOS GAPS ENTRE LAYOUT
		for (int i = 0; i < 8; i++) {
			this.escalones.add(new PanelEscalon(i+1));
			panelColumna.add(this.escalones.get(i));
		}
		// Cambia el color del primer escalon por default
		this.escalones.getFirst().setcolorUso();
		
		for (int i = 0; i < 2; i++) {
			this.jugadorFinal.add(new PanelJugadorFinal());
		}
		
		//
		// PANEL JUGADORES
		//
		
		panelJugadores = new JPanel() {
		    @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            ImageIcon imageIcon = new ImageIcon("imagenes/image.png");
	            Image image = imageIcon.getImage();
	            g.drawImage(image, 0, 0, getWidth(), getHeight(), this); 
	        	}
			};
		panelJugadores.setBackground(Color.BLACK);
		panelJugadores.setSize(new Dimension(5, 5));
		panelJugadores.setBounds(85, 508, 1181, 175);
		contentPane.add(panelJugadores);
		GridLayout gridLayoutJugador = new GridLayout (1, 9);
		gridLayoutJugador.setHgap(10); 
		gridLayoutJugador.setVgap(20); 
		panelJugadores.setLayout(gridLayoutJugador);
		panelJugadores.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		for (int i = 0; i < 9; i++) {
			this.jugadorNormal.add(new PanelJugadorNormal());
			panelJugadores.add(this.jugadorNormal.get(i));
		}
		
		panelJugadores.setVisible(true);
				
		//
		// PANEL JUGADORES (FINAL)
		//
		
		panelFinal = new JPanel() {
			@Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            ImageIcon imageIcon = new ImageIcon("imagenes/image.png");
	            Image image = imageIcon.getImage();
	            g.drawImage(image, 0, 0, getWidth(), getHeight(), this); 
	        	}
			};
		panelFinal.setBounds(85, 508, 1181, 175);
		contentPane.add(panelFinal);
		GridLayout gridLayoutFinal = new GridLayout (2, 1);
		gridLayoutFinal.setHgap(10); 
		gridLayoutFinal.setVgap(10); 
		panelFinal.setLayout(gridLayoutFinal);
		
		
		
		for (int i = 0; i < 2; i++) {
			panelFinal.add(this.jugadorFinal.get(i));
		}
		
		panelFinal.setVisible(false);
		
		//---------------------Panel Principal----------------------------------
		//
		// PANEL APROXIMACION
		//
		
		panelAproximacion = new JPanel() {
			@Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            ImageIcon imageIcon = new ImageIcon("imagenes/image.png");
	            Image image = imageIcon.getImage();
	            g.drawImage(image, 0, 0, getWidth(), getHeight(), this); 
	        	}
			};
		panelAproximacion.setBounds(85, 0, 1181, 508);
		contentPane.add(panelAproximacion);
		panelAproximacion.setLayout(null);
		
		btnaproxEnviar = new JButton("CONFIRMAR");
		btnaproxEnviar.setBounds(283, 325, 170, 65);
		panelAproximacion.add(btnaproxEnviar);
		btnaproxEnviar.setFont(new FuentePersonalizada().getFuente("fonts/Helvetica Bold Condensed.otf", 15f));
		btnaproxEnviar.setBackground(new Color(222, 226, 230));
		btnaproxEnviar.setForeground(new Color(37, 36, 34));
		
		NumberFormat formatoNumero = NumberFormat.getIntegerInstance();
		formatoNumero.setGroupingUsed(false);
        NumberFormatter formateador = new NumberFormatter(formatoNumero);
        formateador.setAllowsInvalid(false);
        formateador.setMinimum(0);
        formateador.setMaximum(10000000); 
		txtaproxRespuesta = new JFormattedTextField();
		txtaproxRespuesta.setBounds(283, 249, 170, 65);
		panelAproximacion.add(txtaproxRespuesta);
		txtaproxRespuesta.setBackground(new Color(222, 226, 230));
		txtaproxRespuesta.setForeground(new Color(37, 36, 34));
		txtaproxRespuesta.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Respuesta numerica");
		txtaproxRespuesta.setFont(new FuentePersonalizada().getFuente("fonts/Helvetica.ttf", 15f));

		String[] column = {"Jugador", "Respuesta"};
		defTable = new DefaultTableModel(null,column);
		JTable table = new JTable(defTable);
		table.setBounds(214, 188, 347, 118);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
	    table.setFont(new FuentePersonalizada().getFuente("fonts/Helvetica.ttf", 20f));
	    table.setRowHeight(28);
	    table.getTableHeader().setReorderingAllowed(false);
	    table.getTableHeader().setResizingAllowed(false);
		sc = new JScrollPane();
		sc.setBounds(501, 239, 418, 160);
		sc.setViewportView(table);
		panelAproximacion.add(sc);
		
		JPanel panelPregunta = new JPanel();
		panelPregunta.setBackground(new Color(222, 226, 230));
		panelPregunta.setBounds(240, 30, 680, 190);
		panelAproximacion.add(panelPregunta);
		
		lblaproxPregunta = new JLabel();
		lblaproxPregunta.setBounds(0,0, 680, 190);
		lblaproxPregunta.setForeground(new Color(37, 36, 34));
		lblaproxPregunta.setFont(new FuentePersonalizada().getFuente("fonts/Helvetica Bold Condensed.otf", 30f));
		panelPregunta.add(lblaproxPregunta);
		
		btnaproxPausa = new JButton();
		btnaproxPausa.setBounds(1111, 11, 60, 60);
		panelAproximacion.add(btnaproxPausa);
		btnaproxPausa.setIcon(new FlatSVGIcon("menu_24dp_EFEFEF_FILL0_wght400_GRAD0_opsz24.svg"));

		panelAproximacion.setVisible(false);
		
		
		//
		// PANEL PREGUNTA
		//

		
		panelPregunta_1 = new JPanel() {
			@Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            ImageIcon imageIcon = new ImageIcon("imagenes/image.png");
	            Image image = imageIcon.getImage();
	            g.drawImage(image, 0, 0, getWidth(), getHeight(), this); 
	        	}
			};
		panelPregunta_1.setBounds(85, 0, 1181, 508);
		contentPane.add(panelPregunta_1);
	
		panelPregunta_1.setLayout(null);

		btnpreRespuesta1.setBounds(261, 247, 280, 100);
		btnpreRespuesta1.setForeground(new Color(37, 36, 34));
		btnpreRespuesta1.setCursor(new Cursor(HAND_CURSOR));
		btnpreRespuesta1.setFont(new FuentePersonalizada().getFuente("fonts/Helvetica Bold Condensed.otf", 20f));
		panelPregunta_1.add(btnpreRespuesta1);
		
		btnpreRespuesta2.setBounds(621, 247, 280, 100);
		btnpreRespuesta2.setForeground(new Color(37, 36, 34));
		btnpreRespuesta2.setCursor(new Cursor(HAND_CURSOR));
		btnpreRespuesta2.setFont(new FuentePersonalizada().getFuente("fonts/Helvetica Bold Condensed.otf", 20f));
		panelPregunta_1.add(btnpreRespuesta2);
		
		btnpreRespuesta3.setBounds(261, 358, 280, 100);
		btnpreRespuesta3.setForeground(new Color(37, 36, 34));
		btnpreRespuesta3.setCursor(new Cursor(HAND_CURSOR));
		btnpreRespuesta3.setFont(new FuentePersonalizada().getFuente("fonts/Helvetica Bold Condensed.otf", 20f));
		panelPregunta_1.add(btnpreRespuesta3);

		btnpreRespuesta4.setBounds(621, 358, 280, 100);
		btnpreRespuesta4.setForeground(new Color(37, 36, 34));
		btnpreRespuesta4.setCursor(new Cursor(HAND_CURSOR));
		btnpreRespuesta4.setFont(new FuentePersonalizada().getFuente("fonts/Helvetica Bold Condensed.otf", 20f));
		panelPregunta_1.add(btnpreRespuesta4);
		
		JPanel panel = new JPanel();
		panel.setBounds(240, 30, 680, 190);
		panel.setBackground(new Color(222, 226, 230));
		panel.setLayout(null);
		panelPregunta_1.add(panel);		
		lblprePregunta = new JLabel();
		lblprePregunta.setBounds(0,0, 680, 190);
		lblprePregunta.setFont(new FuentePersonalizada().getFuente("fonts/Helvetica Bold Condensed.otf", 30f));
		lblprePregunta.setForeground(new Color(37, 36, 34));
		panel.add(lblprePregunta);	
		
		btnprePausa = new JButton();
		btnprePausa.setBounds(1111, 11, 60, 60);
		btnprePausa.setIcon(new FlatSVGIcon("menu_24dp_EFEFEF_FILL0_wght400_GRAD0_opsz24.svg"));
		panelPregunta_1.add(btnprePausa);
		
		
		
		ImageIcon bg = new ImageIcon("imagenes\\07-43-00-535_512.gif");
        Image scaledBackground = bg.getImage().getScaledInstance(1280,720,Image.SCALE_DEFAULT);
        ImageIcon backgroundGanador = new ImageIcon(scaledBackground);
        background = new JLabel(backgroundGanador);
        background.setBounds(0, 0, 1280, 720);
        contentPane.add(background);
        
        background.setVisible(false);
        
		addKeyListener(c);
		setFocusable(true);
	}
	
	//GET DE LOS ESCALONES
	public void setBackgroundGanador() {
		contentPane.setBackground(Color.black);
		background.setVisible(true);
		
	}
	
	public void setbackgroundOriginal() {
		background.setVisible(false);
	}
	
	public JPanel getPanelColumna() {
		return panelColumna;
	}	
	public ArrayList<PanelEscalon> getEscalones() {
		return escalones;
	}
	//SET DE ESCALON EN USO
	public void setEscalonUso(int nroEscalon) {
		for (PanelEscalon panelEscalon : escalones) {
			panelEscalon.setcolorNoUso();
		}
		escalones.get(nroEscalon).setcolorUso();
	}

	//GET DE LOS JUGADORES
	public JPanel getPanelJugadores() {
		return panelJugadores;
	}
	public ArrayList<PanelJugadorNormal> getJugadorNormal() {
		return jugadorNormal;
	}
	public ArrayList<PanelJugadorFinal> getJugadorFinal() {
		return jugadorFinal;
	}

	public ControladorJuego getC() {return c;}
	public void setC(ControladorJuego c) {this.c = c;}
	
	// GET PANEL DEL GANADOR
	public void setGanadorNombre(String nombre) {
		lblGanadorTexto.setText(nombre);
	}
	public void setGanadorImagen(ImageIcon img) {
        Image scaledGif = img.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT);
        ImageIcon Gif = new ImageIcon(scaledGif);
		lblGanadorImagen.setIcon(Gif);
	}
	
	public JPanel getPanelGanador() {return panelGanador;}
	public JButton getBtnGanadorContinuar() {return btnGanadorContinuar;}
	public JLabel getLblGanadorTexto() {return lblGanadorTexto;}
	public JLabel getLblGanadorImagen() {return lblGanadorImagen;}

	public DefaultTableModel getDefTable() {return defTable;}
	public void setDefTable(DefaultTableModel defTable) {this.defTable = defTable;}

	public JPanel getPanelPregunta() {return panelPregunta_1;}
	public JLabel getLblprePregunta() {return lblprePregunta;}
	public void setLblprePregunta(JLabel lblprePregunta) {this.lblprePregunta = lblprePregunta;}

	public JButton getBtnpreRespuesta2() {return btnpreRespuesta2;}
	public JButton getBtnpreRespuesta1() {return btnpreRespuesta1;}
	public JButton getBtnpreRespuesta3() {return btnpreRespuesta3;}
	public JButton getBtnpreRespuesta4() {return btnpreRespuesta4;}
	public void setBtnpreRespuesta2(BotonPregunta btnpreRespuesta2) {this.btnpreRespuesta2 = btnpreRespuesta2;}
	public void setBtnpreRespuesta1(BotonPregunta btnpreRespuesta1) {this.btnpreRespuesta1 = btnpreRespuesta1;}
	public void setBtnpreRespuesta3(BotonPregunta btnpreRespuesta3) {this.btnpreRespuesta3 = btnpreRespuesta3;}
	public void setBtnpreRespuesta4(BotonPregunta btnpreRespuesta4) {this.btnpreRespuesta4 = btnpreRespuesta4;}

	public JLabel getlblaproxPregunta() {return lblaproxPregunta;}
	public JPanel getPanelAproximacion() {return panelAproximacion;}
	public JButton getBtnaproxEnviar() {return btnaproxEnviar;}
	public void setBtnaproxEnviar(JButton btnaproxEnviar) {this.btnaproxEnviar = btnaproxEnviar;}
	public JFormattedTextField getTxtaproxRespuesta() {return txtaproxRespuesta;}
	public void setTxtaproxRespuesta(JFormattedTextField txtaproxRespuesta) {this.txtaproxRespuesta = txtaproxRespuesta;}
	
	public JPanel getPanelFinal() {return panelFinal;}

	public JButton getBtnprePausa() {return btnprePausa;}
	public JButton getBtnaproxPausa() {return btnaproxPausa;}
	
	
	}
