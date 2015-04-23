package zainzinger;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
/**
 * GUI für das Verwalten der Boote
 * @author lukaszainzinger
 * @version 2015-04-23
 */
public class Segelverein_GUI {

	private JFrame frame;
	private JTable boot_table;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtPersonen;
	private JTextField txtTiefgang;
	private JDBC_Controller_PSQL con;
	private Object[][] tabelle;
	private Object[] colnames = {"id", "name", "personen", "tiefgang"};
	private JRadioButton rdbtnSportboot;
	private JRadioButton rdbtnTourenboot;
	private JTextField txtBootsklasse;
	private JTextField txtSegelflaeche, txtMName, txtMKey, txtMAKlasse, txtWName, txtWJahr, txtWDatum, txtWLaenge;
	private DefaultTableModel model;

	/**
	 * Create the application.
	 */
	public Segelverein_GUI(JDBC_Controller_PSQL con) {
		this.con = con;
		ResultSet rs;
		try {
			rs = con.executeQuery("SELECT * FROM boot;");
			this.tabelle = con.forJTable(rs, 4);
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabpane = new JTabbedPane();
		
		JPanel button_panel = new JPanel();
		getFrame().getContentPane().add(button_panel, BorderLayout.SOUTH);
		button_panel.setLayout(new GridLayout(3, 4, 0, 0));
		
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
		
		rdbtnTourenboot = new JRadioButton("Tourenboot");
		button_panel.add(rdbtnTourenboot);
		
		txtBootsklasse = new JTextField();
		txtBootsklasse.setText("BOOTSKLASSE");
		button_panel.add(txtBootsklasse);
		txtBootsklasse.setColumns(10);
		
		rdbtnSportboot = new JRadioButton("Sportboot");
		button_panel.add(rdbtnSportboot);
		
		txtSegelflaeche = new JTextField();
		txtSegelflaeche.setText("SEGELFLAECHE");
		button_panel.add(txtSegelflaeche);
		txtSegelflaeche.setColumns(10);
		
		JButton button_create = new JButton("Eintragen");
		button_panel.add(button_create);
		
		JButton button_update = new JButton("Update");
		button_panel.add(button_update);
		
		JButton button_refresh = new JButton("Neu Laden");
		button_panel.add(button_refresh);
		
		JButton button_delete = new JButton("Löschen");
		button_panel.add(button_delete);
		
		model = new DefaultTableModel(tabelle, colnames);
		
		boot_table = new JTable(tabelle, colnames);
		final JScrollPane anzeige_panel = new JScrollPane(boot_table);
		boot_table.setFillsViewportHeight(true);
		
		JPanel jp2 = new JPanel(new GridLayout(10,1));
		JLabel lman = new JLabel("Mannschaft:");
		txtMName = new JTextField("MannschaftsName");
		txtMKey = new JTextField("TrainerKey");
		txtMAKlasse = new JTextField("AltersKlasse");
		JLabel lwettfahrt = new JLabel("Wettfahrt:");
		txtWName = new JTextField("WettfahrtName");
		txtWJahr = new JTextField("WettfahrtJahr");
		txtWDatum = new JTextField("WettfahrtDatum");
		txtWLaenge = new JTextField("WettfahrtLänge");
		JButton btnEintragen = new JButton("Eintragen");
		
		jp2.add(lman);
		jp2.add(txtMName);
		jp2.add(txtMKey);
		jp2.add(txtMAKlasse);
		jp2.add(lwettfahrt);
		jp2.add(txtWName);
		jp2.add(txtWJahr);
		jp2.add(txtWDatum);
		jp2.add(txtWLaenge);
		jp2.add(btnEintragen);
		
		btnEintragen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtMName.getText() != "MannschaftsName" && txtMKey.getText() != "TrainerKey" && txtMAKlasse.getText() != "AltersKlasse" ){
					try {
						con.insert("mannschaft", "name, key, aklasse", "'" + txtMName.getText() + "', " + txtMKey.getText() + ", " + txtMAKlasse.getText() );
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(txtWName.getText() !="WettfahrtName" && txtWJahr.getText() != "WettfahrtJahr" && txtWDatum.getText() != "WettfahrtDatum" && txtWLaenge.getText() != "WettfahrtLänge"){
					try {
						con.insert("wettfahrt", "wname, wjahr, datum, laenge", "'"+ txtWName.getText() + "', '" + txtWJahr.getText() + "', " + txtWDatum.getText() + ", " + txtWLaenge.getText() );
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		
		JPanel jp1 = new JPanel(new GridLayout(2,1));
		jp1.add(anzeige_panel);
		jp1.add(button_panel);
		
		tabpane.addTab("Boot", jp1);
		tabpane.addTab("Wettfahrt/Mannschaft", jp2);
		getFrame().getContentPane().add(tabpane, BorderLayout.CENTER);
		
		
		//ActionListener CREATE
		button_create.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txtId.getText() != "ID" && txtName.getText() != "NAME" && txtPersonen.getText() != "PERSONEN" && txtTiefgang.getText() != "TIEFGANG"){
					try {
						con.insert("boot","id, name, personen, tiefgang", txtId.getText() + ", '"+ txtName.getText() + "', " + txtPersonen.getText() + ", " +  txtTiefgang.getText()+"");
						if(rdbtnSportboot.isSelected() && txtSegelflaeche.getText() != "SAEGELFLAECHE"){
							con.insert("sportboot","id, segelflaeche", "" + txtId.getText()+ ", " + txtSegelflaeche.getText());
						}else if(rdbtnTourenboot.isSelected() && txtBootsklasse.getText() != "BOOTSKLASSE"){
							con.insert("tourenboot","id, bootsklasse","" + txtId.getText() + ", '" + txtBootsklasse.getText() +"'");
						}
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
				if(txtId.getText() != "ID"){
					try {
						ResultSet rs1 = con.executeQuery("SELECT id FROM sportboot;");
						while(rs1.next()){
							int x = rs1.getInt(1);
							if(x == Integer.parseInt(txtId.getText())){
								con.delete("sportboot", "id", txtId.getText());
							}
						}
						ResultSet rs2 = con.executeQuery("SELECT id FROM tourenboot;");
						while(rs2.next()){
							int x1 = rs2.getInt(1);
							if(x1 == Integer.parseInt(txtId.getText())){
								con.delete("tourenboot", "id", txtId.getText());
							}
						}
						if(txtId.getText() != "ID" && txtId.getText() != ""){
							con.delete("boot","id", txtId.getText());
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
	
		//ActionListener REFRESH
				button_refresh.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							ResultSet rs = con.executeQuery("SELECT * FROM boot;");
							tabelle = con.forJTable(rs, 4);
							JTable neu = new JTable(tabelle, colnames);
							rs.close();
							//model.fireTableDataChanged();
							anzeige_panel.remove(boot_table);
							anzeige_panel.add(neu);
							anzeige_panel.repaint();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
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
