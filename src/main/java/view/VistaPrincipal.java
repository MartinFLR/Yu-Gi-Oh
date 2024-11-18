package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import controller.ControladorPrincipal;

public class VistaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ControladorPrincipal controlador;
	
	private JButton btnJugar;
	private JButton btnOpciones;
	private JButton BtnSalir;
	private JButton btnCreditos;
	private JButton btnLogin;
	private JButton btnAyuda;
	private JButton btnRanking;
	private JButton btnModificar;
	
	private JPanel panelAyuda;
	private JPanel panelCreditos;
	private JButton btnSalirAyuda;
	private JButton btnSalirCreditos;


	public VistaPrincipal(ControladorPrincipal controlador) {
		this.setControlador(controlador);
		setTitle("8 ESCALONES");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		//---PANELES (VENTANAS EXTRAS)
		
		
		panelAyuda = new JPanel();
		panelAyuda.setBounds(232, 65, 800, 550);
		contentPane.add(panelAyuda);
		panelAyuda.setLayout(null);
		panelAyuda.setVisible(false);
		
		btnSalirAyuda = new JButton("Volver");
		btnSalirAyuda.setBounds(673, 499, 100, 40);
		panelAyuda.add(btnSalirAyuda); 
		JLabel lblAyuda = new JLabel("<html>- Participan 9 jugadores que deberán ir avanzando por 8 escalones en los cuales se irá eliminando a uno por cada subida, llegando al último escalón una final de dos jugadores.<br>"
				+ "- Cada escalón tiene una temática que será elegida al azar antes de comenzar el juego y los jugadores se irán enterando si avanzan a ese escalón. <br>"
				+ "- Las preguntas realizadas a cada participante se harán de forma aleatoria y del tipo multiple choice para responder. <br>"
				+ "- En caso de empate para eliminar a un participante se hará una pregunta de aproximación. <br>"
				+ "- Una pregunta de aproximación es cuando los participantes tienen que dar una respuesta y gana quien se haya acercado más a la respuesta correcta, por ejemplo, si se pregunta cuándo fue el mundial que se realizó en Argentina y uno contesta 1970 y el otro 1982, el que se acerque más al valor correcto, que sería 1978, ganaría. Por cada escalón se harán dos rondas de preguntas.</html>");
		lblAyuda.setVerticalAlignment(SwingConstants.TOP);
		lblAyuda.setBounds(10, 96, 780, 392);
		lblAyuda.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelAyuda.add(lblAyuda);
		
		JLabel lblTitulo = new JLabel("AYUDA");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitulo.setBounds(10, 11, 780, 74);
		panelAyuda.add(lblTitulo); 

		
		panelCreditos = new JPanel();
		panelCreditos.setBounds(232, 65, 800, 550);
		contentPane.add(panelCreditos);
		panelCreditos.setLayout(null);
		panelCreditos.setVisible(false);
		
		btnSalirCreditos = new JButton("Volver");
		btnSalirCreditos.setBounds(690, 499, 100, 40);
		panelCreditos.add(btnSalirCreditos);

		JLabel lblLogica = new JLabel("<html>Botha, Ana<br>Montiel, Nicolas<br>Rojas, Axel</html>");
		lblLogica.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogica.setVerticalAlignment(SwingConstants.TOP);
		lblLogica.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLogica.setBounds(280, 151, 250, 388);
		panelCreditos.add(lblLogica);
		JLabel lblABM = new JLabel("<html>Bustamante, Nicolas<br>Flores, Martin<br>Titos, Felix Aldo</html>");
		lblABM.setHorizontalAlignment(SwingConstants.CENTER);
		lblABM.setVerticalAlignment(SwingConstants.TOP);
		lblABM.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblABM.setBounds(31, 151, 250, 388);
		panelCreditos.add(lblABM);
		JLabel lblIGU = new JLabel("<html>Arbita, Rodrigo<br>Lopez, Gabriel<br>Toconas, Walter</html>");
		lblIGU.setHorizontalAlignment(SwingConstants.CENTER);
		lblIGU.setVerticalAlignment(SwingConstants.TOP);
		lblIGU.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIGU.setBounds(540, 149, 250, 339);
		panelCreditos.add(lblIGU);
		JLabel lblCreditos = new JLabel("CREDITOS");
		lblCreditos.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblCreditos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreditos.setBounds(10, 11, 780, 72);
		panelCreditos.add(lblCreditos);
		lblCreditos.setIcon(new FlatSVGIcon("creditos.svg", 50, 50));
		
		
		//---BOTONES PANTALLA PRINCIPAL
		btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(52, 196, 250, 60);
		contentPane.add(btnJugar);
		btnJugar.addActionListener(controlador);
		btnJugar.setFont(new Font("Tahoma", Font.BOLD, 15));
		

		btnOpciones = new JButton("OPCIONES");
		btnOpciones.setBounds(52, 267, 250, 60);		
		contentPane.add(btnOpciones);
		btnOpciones.addActionListener(controlador);
		btnOpciones.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		btnRanking = new JButton("RANKING");		
		btnRanking.setBounds(52, 338, 120, 50);
		contentPane.add(btnRanking);
		btnRanking.addActionListener(controlador);
		btnRanking.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		btnAyuda = new JButton("AYUDA");
		btnAyuda.setBounds(182, 338, 120, 50);
		contentPane.add(btnAyuda);
		btnAyuda.addActionListener(controlador);
		btnAyuda.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		BtnSalir = new JButton("SALIR");
		BtnSalir.setBounds(52, 399, 250, 60);
		contentPane.add(BtnSalir);
		BtnSalir.addActionListener(controlador);
		BtnSalir.setFont(new Font("Tahoma", Font.BOLD, 15));

	
		// BOTONES PEQUEÑOS
		btnLogin = new JButton();
		FlatSVGIcon iconLogin = new FlatSVGIcon("login.svg", 30, 30);
		btnLogin.setIcon(iconLogin);
		btnLogin.setBounds(52, 615, 55, 55);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(controlador);
	
		btnCreditos = new JButton();
		FlatSVGIcon iconCreditos = new FlatSVGIcon("creditos.svg", 30, 30);
		btnCreditos.setIcon(iconCreditos);
		btnCreditos.setBounds(117, 615, 55, 55);
		btnCreditos.addActionListener(controlador);
		contentPane.add(btnCreditos);
		
		btnModificar = new JButton();
		FlatSVGIcon iconModificar = new FlatSVGIcon("editar.svg", 30, 30);
		btnModificar.setIcon(iconModificar);
		btnModificar.setBounds(182, 615, 55, 55);
		contentPane.add(btnModificar);
		btnModificar.addActionListener(controlador);
		
		JLabel lblTitutlo = new JLabel("8 ESCALONES");
		lblTitutlo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitutlo.setFont(new Font("Comic Sans MS", Font.BOLD, 80));
		lblTitutlo.setForeground(Color.CYAN);
		lblTitutlo.setBounds(20, 16, 1244, 169);
		contentPane.add(lblTitutlo);
		
		

	}
	
	public ControladorPrincipal getControlador() {
		return controlador;
	}
	public void setControlador(ControladorPrincipal controlador) {
		this.controlador = controlador;
	}

	
	public JButton getBtnJugar() {
		return btnJugar;
	}
	public JButton getBtnOpciones() {
		return btnOpciones;
	}
	public JButton getBtnSalir() {
		return BtnSalir;
	}
	public JButton getBtnCreditos() {
		return btnCreditos;
	}
	public JButton getBtnLogin() {
		return btnLogin;
	}
	public JButton getBtnAyuda() {
		return btnAyuda;
	}
	public JButton getBtnRanking() {
		return btnRanking;
	}
	public JButton getBtnModificar() {
		return btnModificar;
	}
	public JPanel getPanelAyuda() {
		return panelAyuda;
	}
	public JPanel getPanelCreditos() {
		return panelCreditos;
	}
	public JButton getBtnSalirCreditos() {
		return btnSalirCreditos;
	}
	public JButton getBtnSalirAyuda() {
		return btnSalirAyuda;
	}

	public void actualizarEstadoLogin(boolean logueado) {
		if (logueado) {
			btnLogin.setEnabled(false); // Deshabilita el botón
		} 
	}
	

	}
	



