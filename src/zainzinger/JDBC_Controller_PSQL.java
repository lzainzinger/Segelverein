package zainzinger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.ds.PGPoolingDataSource;


/**
 * @author lukaszainzinger
 * @version 2015-03-18
 */
public class JDBC_Controller_PSQL {	
	private String name;
	private String server;
	private String benutzer;
	private String passwort;
	private String datenbank;
	private Connection con;
	private Statement st;
	private PGPoolingDataSource ds;
	
	/**
	 * Konstruktor von Controller
	 * @param server der Server der verwendet wird
	 * @param benutzer der benutzer mit den amgemeldet wird
	 * @param passwort das passwort des benutzer (leer wenn es keines gibt)
	 * @param datenbank die datenbank die verwendet wird
	 */
	public JDBC_Controller_PSQL(String server,String benutzer,String passwort,String datenbank){
		this.server = server;
		this.benutzer = benutzer;
		this.passwort = passwort;
		this.datenbank = datenbank;
	}	
	public void connect() throws SQLException {
		ds = new PGPoolingDataSource();
		ds.setServerName(server);
		ds.setUser(name);
		ds.setPassword(passwort);
		ds.setDatabaseName(datenbank);
		
		con = ds.getConnection();
		con.setAutoCommit(false);
		st = con.createStatement();
	}
	/**
	 * Query
	 * @param query Query 
	 * @return ResultSet Rückgabe der Angegebenen Query
	 * @throws SQLException 
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		return st.executeQuery(query);
	}
	
	/**
	 * INSERT Methode
	 * @param tabname Name der Tabelle
	 * @param param Die Parameter der Tabelle, Komma getrennt
	 * @param values Die einzutragenden Werte, Komma getrennt
	 * @throws SQLException 
	 */
	public void insert(String tabname, String param, String values) throws SQLException{
		String sql = "INSERT INTO " + tabname + " (" + param +") VALUES (" + values +");";
		System.out.println(sql);
		st.executeUpdate(sql);
		con.commit();
	}
	
	/**
	 * DELETE Methode
	 * @param tabname Name der Tabelle
	 * @param param Die Parameter der Tabelle nach der Verglichen wird (WHERE param)
	 * @param values Der benötigte Wert, (= values)
	 * @throws SQLException 
	 */
	public void delete(String tabname, String param, String values) throws SQLException{
		String sql = "DELETE FROM " + tabname + " WHERE " + param +" = " + values +";";
		System.out.println(sql);
		st.executeUpdate(sql);
		con.commit();
	}
	
	/**
	 * UPDATE Methode
	 * @param tabname Name der Tabelle
	 * @param param Die Parameter der Tabelle nach der Verglichen wird (WHERE param)
	 * @param values Der benötigte Wert, (= values)
	 * @param id Identification by
	 * @param expr Expression to Identify 
	 * @throws SQLException 
	 */
	public void update(String tabname, String param, String values, String id, String expr) throws SQLException{
		String sql = "UPDATE " + tabname + " SET " + param +" = " + values +" WHERE " + id + " = " + expr + " ;";
		System.out.println(sql);
		st.executeUpdate(sql);
		con.commit();
	}
	
	/**
	 * Erstellt aus einem ResultSet ein 2 Dimensionales Object Array (für JTables)
	 * @param rs ResultSet
	 * @return Object[][]
	 */
	public Object[][] forJTable(ResultSet rs){
		Object[][] data = {{}};

		return data;
	}
	
	/**
	 * Schließt alle Connections
	 */
	public void close() {
		try {
			st.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		try {
			con.commit();
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Gibt die Connection zurück
	 * @return conenection
	 */
	public Connection getConnection() {
		return con;
	}
	/**
	 * Gibt das Statement zurück
	 * @return statement
	 */
	public Statement getStatement() {
		return st;
	}

}

