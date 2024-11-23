package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ControladorLogin;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.awt.Cursor;
import java.awt.Font;

public class VistaLogin extends JFrame{
	
	private JPanel contentPane;
	private ControladorLogin c;
	private JButton btnSalir;
	private JTextField txtCuenta;
	private JPasswordField txtContrasenia;
	private JButton btnLogin;


	public VistaLogin (ControladorLogin c) {
		this.setC(c);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(300, 200, 450, 400);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		JLabel lblLogin = new JLabel("LOGIN ");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblLogin.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogin.setBounds(65, 11, 375, 107);
		contentPane.add(lblLogin);
		
		JLabel lblCuenta = new JLabel("Cuenta: ");
		lblCuenta.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCuenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblCuenta.setBounds(65, 106, 80, 20);
		contentPane.add(lblCuenta);
		
		JLabel lblContrasenia = new JLabel("Contraseña: ");
		lblContrasenia.setVerticalAlignment(SwingConstants.BOTTOM);
		lblContrasenia.setHorizontalAlignment(SwingConstants.LEFT);
		lblContrasenia.setBounds(65, 182, 80, 21);
		contentPane.add(lblContrasenia);
		
		btnSalir = new JButton("Volver", new FlatSVGIcon("arrow_back_ios_24dp_EFEFEF_FILL0_wght400_GRAD0_opsz24.svg", 20, 20));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSalir.setCursor(new Cursor(HAND_CURSOR));
		btnSalir.putClientProperty( FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_ROUND_RECT);
		btnSalir.setBounds(340, 349, 100, 40);
		contentPane.add(btnSalir);
		
		// -- TEXTFIELDS DE DATOS
		txtCuenta = new JTextField();
		txtCuenta.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su nombre de cuenta");
		//txtCuenta.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("account_circle_24dp_999999_FILL0_wght400_GRAD0_opsz24.svg"));
		txtCuenta.setBounds(65, 131, 320, 40);
		contentPane.add(txtCuenta);
		txtCuenta.setColumns(10);
		
		txtContrasenia = new JPasswordField();
		txtContrasenia.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su contraseña");
		//txtContrasenia.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("password_24dp_999999_FILL0_wght400_GRAD0_opsz24.svg"));
		txtContrasenia.setBounds(65, 207, 320, 40);
		contentPane.add(txtContrasenia);
	
		btnLogin = new JButton("Logearse");
		btnLogin.setBounds(65, 263, 320, 40);
		btnLogin.setCursor(new Cursor(HAND_CURSOR));
		contentPane.add(btnLogin);
		
		
		
	}
    
	public ControladorLogin getC() {
		return c;
	}
	public void setC(ControladorLogin c) {
		this.c = c;
	}

	public JButton getBtnSalir() {
		return btnSalir;
	}
	public JTextField getTxtCuenta() {
		return txtCuenta;
	}
	public JPasswordField getTxtContrasenia() {
		return txtContrasenia;
	}
	public JButton getBtnNewButton() {
		return btnLogin;
	}

	public JButton getBtnLogin(){
		return btnLogin;
	}

}
