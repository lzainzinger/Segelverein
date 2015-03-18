package zainzinger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
/**
 * GUI für das Verwalten der Boote
 * @author lukaszainzinger
 * @version 2015-03-18
 */
public class Segelverein_GUI {

	private JFrame frame;
	private JTable boot_table;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtPersonen;
	private JTextField txtTiefgang;
	JDBC_Controller_PSQL con;

	/**
	 * Create the application.
	 */
	public Segelverein_GUI(JDBC_Controller_PSQL con) {
		this.con = con;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel button_panel = new JPanel();
		getFrame().getContentPane().add(button_panel, BorderLayout.SOUTH);
		button_panel.setLayout(new GridLayout(2, 4, 0, 0));
		
		txtId = new JTextField();
		txtId.setText("ID");
		button_panel.add(txtId);
		txtId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("NAME");
		button_panel.add(txtName);
		txtName.setColumns(10);
		
		txtPersonen = new JTextField();
		txtPersonen.setText("PERSONEN ");
		button_panel.add(txtPersonen);
		txtPersonen.setColumns(10);
		
		txtTiefgang = new JTextField();
		txtTiefgang.setText("TIEFGANG");
		button_panel.add(txtTiefgang);
		txtTiefgang.setColumns(10);
		
		JButton button_create = new JButton("Eintragen");
		button_panel.add(button_create);
		
		JButton button_update = new JButton("Update");
		button_panel.add(button_update);
		
		JButton button_refresh = new JButton("Neu Laden");
		button_panel.add(button_refresh);
		
		JButton button_delete = new JButton("Löschen");
		button_panel.add(button_delete);
		
		JPanel anzeige_panel = new JPanel();
		getFrame().getContentPane().add(anzeige_panel, BorderLayout.CENTER);
		
		boot_table = new JTable();
		anzeige_panel.add(boot_table);
		
		
		//ActionListener CREATE
		button_create.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtId.getText() != "ID" && txtName.getText() != "NAME" && txtPersonen.getText() != "PERSONEN" && txtTiefgang.getText() != "TIEFGANG"){
					try {
						con.insert("boot","id, name, personen, tiefgang", txtId.getText() + ", '"+ txtName.getText() + "', " + txtPersonen.getText() + ", " +  txtTiefgang.getText()+"");
					} catch (SQLException e1) {
						System.err.println("Bitte alle Textfelder RICHTIG befüllen!");
						e1.printStackTrace();
					}
				}else{
					System.err.println("Bitte alle Textfelder befüllen!");
				}	
			}
		});
		
		//ActionListener DELETE
		button_delete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtId.getText() != "ID" || txtName.getText() != "NAME" || txtPersonen.getText() != "PERSONEN" || txtTiefgang.getText() != "TIEFGANG"){
					try {
						if(txtId.getText() != "ID" && txtId.getText() != ""){
							con.delete("boot","id", txtId.getText());
						}else if(txtName.getText() != "NAME" && txtName.getText() != ""){
							con.delete("boot","name", txtName.getText());
						}else if(txtPersonen.getText() != "PERSONEN" && txtPersonen.getText() != ""){
							con.delete("boot","personen", txtPersonen.getText());
						}else{
							con.delete("boot", "tiefgang", txtTiefgang.getText());
						}
					} catch (SQLException e1) {
						System.err.println("Bitte alle Textfelder RICHTIG befüllen!");
						e1.printStackTrace();
					}
				}else{
					System.err.println("Bitte ein Feld ausfüllen um einen Eintrag zu Löschen!");
				}	
			}
		});
		
		//ActionListener UPDATE
		button_update.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtId.getText() != "ID" && txtId.getText() != ""){
					try {
						if(txtName.getText() != "NAME" && txtName.getText() != ""){
							con.update("boot","name", "'" + txtName.getText() + "'", "id", txtId.getText());
						}
						if(txtPersonen.getText() != "PERSONEN" && txtPersonen.getText() != ""){
							con.update("boot","personen", txtPersonen.getText(), "id", txtId.getText());
						}
						if(txtTiefgang.getText() != "TIEFGANG" && txtTiefgang.getText() != ""){
							con.update("boot", "tiefgang", txtTiefgang.getText(), "id", txtId.getText());
						}
					} catch (SQLException e1) {
						System.err.println("Bitte alle Textfelder RICHTIG befüllen!");
						e1.printStackTrace();
					}
				}else{
					System.err.println("Bitte ID angeben um UPDATE durchzuführen!");
				}	
			}
		});
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
