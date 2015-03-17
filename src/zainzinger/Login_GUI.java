package zainzinger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
/**
 * GUI für den Login
 * @author lukaszainzinger
 * @version 2015-03-17
 */
public class Login_GUI {

	private JFrame frame;
	private JTextField txt_db;
	private JTextField txtUser;
	private JTextField txtPasswort;
	private JTextField txtServer;
	private JDBC_Controller_PSQL con;
	private Segelverein_GUI segelGUI_Boot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_GUI window = new Login_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public Login_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(6, 2, 0, 0));
		
		JLabel lblVerbindungZurDatenbank = new JLabel("Verbindung zur Datenbank!");
		panel.add(lblVerbindungZurDatenbank);
		
		JLabel label = new JLabel(" ");
		panel.add(label);
		
		JLabel lblDatabase = new JLabel("Database:");
		panel.add(lblDatabase);
		
		txt_db = new JTextField();
		panel.add(txt_db);
		txt_db.setColumns(10);
		
		JLabel lblUser = new JLabel("User:");
		panel.add(lblUser);
		
		txtUser = new JTextField();
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblPasswort = new JLabel("Passwort:");
		panel.add(lblPasswort);
		
		txtPasswort = new JTextField();
		panel.add(txtPasswort);
		txtPasswort.setColumns(10);
		
		JLabel lblServer = new JLabel("Server:");
		panel.add(lblServer);
		
		txtServer = new JTextField();
		panel.add(txtServer);
		txtServer.setColumns(10);
		
		JLabel label_1 = new JLabel(" ");
		panel.add(label_1);
		
		JButton btnConnect = new JButton("Connect!");
		panel.add(btnConnect);
		btnConnect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int error = 0;
				con = new JDBC_Controller_PSQL(txtServer.getText(), txtUser.getText(), txtPasswort.getText(), txt_db.getText());
				try {
					con.connect();
				} catch (SQLException e1) {
					e1.printStackTrace();
					error = 1;
				}
				if(error==0){
					frame.hide();
					segelGUI_Boot = new Segelverein_GUI();
					
				}
				
			} });
	}

}
