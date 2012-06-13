package gr.uoa.di.s3lab.bpelcube.prototype;

import gr.uoa.di.s3lab.bpelcube.prototype.model.BPELProcess;
import gr.uoa.di.s3lab.p2p.P2PNodeDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BPELcubeNodeDB extends P2PNodeDB {

	public BPELcubeNodeDB(String home) {
		super(home);
	}
	
	private void createTable_DEPLOYED_PROCESSES() {
		String sql = "CREATE CACHED TABLE DEPLOYED_PROCESSES (" +
				"PROCESS_ID VARCHAR(255) NOT NULL," +
				"PROCESS_OBJ OBJECT NOT NULL," +
				"PRIMARY KEY (PROCESS_ID)" +
				")";
		this.executeDDL(sql);
	}

	@Override
	protected void createTables() {
		createTable_DEPLOYED_PROCESSES();
	}

	@Override
	protected void clear() {
		
	}
	
	/**
	 * Adds the specified BPEL process to the DEPLOYED_PROCESSES table.
	 * 
	 * @param bpelProcess the BPEL process
	 */
	public void addDeployedProcess(BPELProcess bpelProcess) {
		log.debug("Adding BPEL process with id: " + bpelProcess.getId());
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO DEPLOYED_PROCESSES(PROCESS_ID,PROCESS_OBJ) VALUES (?,?)";
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, bpelProcess.getId());
			stmt.setObject(2, bpelProcess);
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {}
		}
	}
	
	/**
	 * Removes the BPEL process that is identified by the specified id.
	 * 
	 * @param processId the process id
	 */
	public void removeDeployedProcess(String processId) {
		log.debug("Removing BPEL process with id: " + processId);
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM DEPLOYED_PROCESSES WHERE PROCESS_ID=?";
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, processId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.debug(e.getMessage());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {}
		}
	}
	
	/**
	 * Gets the deployed BPEL process that is identified by the specified id.
	 * 
	 * @param processId the specified process id
	 * @return a BPELProcess object, or null
	 */
	public BPELProcess getDeployedProcess(String processId) {
		log.debug("Looking for deployed BPEL process with id: " + processId);
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String sql = "SELECT PROCESS_OBJ FROM DEPLOYED_PROCESSES WHERE PROCESS_ID=?";
			stmt = this.connection.prepareStatement(sql);
			stmt.setString(1, processId);
			rset = stmt.executeQuery();
			if (rset.next()) {
				log.debug("BPEL process found.");
				return (BPELProcess) rset.getObject(1);
			}
			log.debug("BPEL process not found.");
			return null;
		} catch (SQLException e) {
			log.debug(e.getMessage());
			return null;
		} finally {
			try {
				if (rset != null) {
					rset.close();
					rset = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {}
		}
	}
	
	/**
	 * Gets the identifiers of all deployed BPEL processes.
	 * 
	 * @return a list with the retrieved process identifiers
	 */
	public List<String> getAllDeployedProcessIds() {
		log.debug("Getting the ids of all deployed BPEL processes.");
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<String> queryResult = new ArrayList<String>();
		try {
			String sql = "SELECT PROCESS_ID FROM DEPLOYED_PROCESSES";
			stmt = this.connection.prepareStatement(sql);
			rset = stmt.executeQuery();
			while (rset.next()) {
				queryResult.add(rset.getString(1));
			}
			return queryResult;
		} catch (SQLException e) {
			log.debug(e.getMessage());
			return queryResult;
		} finally {
			try {
				if (rset != null) {
					rset.close();
					rset = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {}
		}
	}
	
}
