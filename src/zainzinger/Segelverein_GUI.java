package zainzinger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
/**
 * GUI für das Verwalten der Boote
 * @author lukaszainzinger
 * @version 2015-03-16
 */
public class Segelverein_GUI {

	private JFrame frame;
	private JTable boot_table;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_personen;
	private JTextField textField_tiefgang;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Segelverein_GUI window = new Segelverein_GUI();
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
	public Segelverein_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JPanel button_panel = new JPanel();
		frame.getContentPane().add(button_panel, BorderLayout.SOUTH);
		button_panel.setLayout(new GridLayout(2, 4, 0, 0));
		
		textField_id = new JTextField();
		button_panel.add(textField_id);
		textField_id.setColumns(10);
		
		textField_name = new JTextField();
		button_panel.add(textField_name);
		textField_name.setColumns(10);
		
		textField_personen = new JTextField();
		button_panel.add(textField_personen);
		textField_personen.setColumns(10);
		
		textField_tiefgang = new JTextField();
		button_panel.add(textField_tiefgang);
		textField_tiefgang.setColumns(10);
		
		JButton button_create = new JButton("Eintragen");
		button_panel.add(button_create);
		
		JButton button_update = new JButton("Update");
		button_panel.add(button_update);
		
		JButton button_refresh = new JButton("Neu Laden");
		button_panel.add(button_refresh);
		
		JButton button_delete = new JButton("Löschen");
		button_panel.add(button_delete);
		
		JPanel anzeige_panel = new JPanel();
		frame.getContentPane().add(anzeige_panel, BorderLayout.CENTER);
		
		boot_table = new JTable();
		anzeige_panel.add(boot_table);
	}

}
