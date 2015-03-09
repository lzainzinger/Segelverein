package zainzinger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.postgresql.ds.PGPoolingDataSource;


/**
 * @author lukaszainzinger
 *
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
	 * Schließt alle Connections
	 */
	public void close() {
		try {
			st.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		try {
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	/**
	 * Gibt die Connection zurück
	 * @return conenection
	 */
	public Connection getCon() {
		return con;
	}
	/**
	 * Gibt das Statement zurück
	 * @return statement
	 */
	public Statement getSt() {
		return st;
	}
}

