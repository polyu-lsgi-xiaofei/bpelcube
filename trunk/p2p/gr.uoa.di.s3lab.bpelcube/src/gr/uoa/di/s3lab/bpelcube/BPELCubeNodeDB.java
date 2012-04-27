package gr.uoa.di.s3lab.bpelcube;

import gr.uoa.di.s3lab.p2p.P2PNodeDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hsqldb.Types;

/**
 * Implements the embedded database of the P2P engine node.
 * 
 * @author Michael Pantazoglou
 *
 */
public class BPELCubeNodeDB extends P2PNodeDB {

	public BPELCubeNodeDB(String home) {
		super(home);
	}
	
	private boolean createTable_P2P_SESSION() {
		
		String SQL = "CREATE CACHED TABLE P2P_SESSION (" +
				"P2P_SESSION_ID VARCHAR(100) NOT NULL," +
				"NODE_ROLE VARCHAR(10) NOT NULL," +
				"CREATION_TIME BIGINT NOT NULL," +
				"COMPLETION_TIME BIGINT," +
				"PRIMARY KEY (P2P_SESSION_ID)" +
				");";
		
		return this.executeDDL(SQL);
	}
	
	private boolean createTable_P2P_SESSION_ACTIVITIES() {
		
		String SQL = "CREATE CACHED TABLE P2P_SESSION_ACTIVITIES (" +
				"P2P_SESSION_ID VARCHAR(100) NOT NULL," +
				"ACTIVITY_ID VARCHAR(100) NOT NULL," +
				"PRIMARY KEY (P2P_SESSION_ID, ACTIVITY_ID)," +
				"FOREIGN KEY (P2P_SESSION_ID) REFERENCES P2P_SESSION (P2P_SESSION_ID) ON DELETE CASCADE" +
				");";
		
		return this.executeDDL(SQL);
	}
	
	private boolean createTable_P2P_SESSION_NEIGHBORS() {
		
		String SQL = "CREATE CACHED TABLE P2P_SESSION_NEIGHBORS (" +
				"P2P_SESSION_ID VARCHAR(100) NOT NULL," +
				"NEIGHBOR_ENDPOINT VARCHAR(255) NOT NULL," +
				"PRIMARY KEY (P2P_SESSION_ID, NEIGHBOR_ENDPOINT)," +
				"FOREIGN KEY (P2P_SESSION_ID) REFERENCES P2P_SESSION (P2P_SESSION_ID) ON DELETE CASCADE" +
				");";
		
		return this.executeDDL(SQL);
	}

	@Override
	protected void createTables() {
		createTable_P2P_SESSION();
		createTable_P2P_SESSION_ACTIVITIES();
		createTable_P2P_SESSION_NEIGHBORS();
	}

	@Override
	protected void clear() {
		
		String SQL;
		
		SQL = "DELETE FROM P2P_SESSION";
		this.executeDDL(SQL);
	}
	
	/**
	 * Returns true if the specified P2P session exists.
	 * 
	 * @param p2pSessionId
	 * @return
	 */
	public boolean p2pSessionExists(String p2pSessionId) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String SQL = "SELECT 1 FROM P2P_SESSION WHERE P2P_SESSION_ID=?";
			stmt = this.connection.prepareStatement(SQL);
			stmt.setString(1, p2pSessionId);
			rset = stmt.executeQuery();
			return rset.next();
		} catch (SQLException e) {
			log.debug(e.getMessage());
			return false;
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
	 * Adds a new P2P_SESSION row with the specified values.
	 * 
	 * @param p2pSessionId
	 * @param nodeRole
	 * @param creationTime
	 * @param completionTime
	 */
	public void addP2PSession(String p2pSessionId, 
			String nodeRole, 
			Long creationTime, 
			Long completionTime) {
		PreparedStatement stmt = null;
		try {
			String SQL = "INSERT INTO P2P_SESSION (P2P_SESSION_ID, NODE_ROLE, " +
					"CREATION_TIME, COMPLETION_TIME) VALUES (?,?,?,?)";
			stmt = this.connection.prepareStatement(SQL);
			stmt.setString(1, p2pSessionId);
			stmt.setString(2, nodeRole);
			stmt.setLong(3, creationTime);
			if (completionTime == null) {
				stmt.setNull(4, Types.BIGINT);
			} else {
				stmt.setLong(4, completionTime);
			}
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
	 * Removes the specified P2P_SESSION row.
	 * 
	 * @param p2pSessionId
	 */
	public void removeP2PSession(String p2pSessionId) {
		PreparedStatement stmt = null;
		try {
			String SQL = "DELETE FROM P2P_SESSION WHERE P2P_SESSION_ID=?";
			stmt = this.connection.prepareStatement(SQL);
			stmt.setString(1, p2pSessionId);
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
	 * Sets the completion time of the specified P2P session.
	 * 
	 * @param p2pSessionId
	 * @param completionTime
	 */
	public void setCompletionTime(String p2pSessionId, Long completionTime) {
		PreparedStatement stmt = null;
		try {
			String SQL = "UPDATE P2P_SESSION SET COMPLETION_TIME=? WHERE P2P_SESSION_ID=?";
			stmt = this.connection.prepareStatement(SQL);
			stmt.setLong(1, completionTime);
			stmt.setString(2, p2pSessionId);
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
	 * Adds a new P2P_SESSION_ACTIVITIES row with the specified values.
	 * 
	 * @param p2pSessionId
	 * @param activityId
	 */
	public void addP2PSessionActivity(String p2pSessionId, String activityId) {
		PreparedStatement stmt = null;
		try {
			String SQL = "INSERT INTO P2P_SESSION_ACTIVITIES " +
					"(P2P_SESSION_ID, ACTIVITY_ID) VALUES (?,?)";
			stmt = this.connection.prepareStatement(SQL);
			stmt.setString(1, p2pSessionId);
			stmt.setString(2, activityId);
			stmt.executeUpdate();
			log.debug("I will be responsible for activity " + 
					p2pSessionId + ": " + activityId);
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
	 * Gets all activities that belong to the specified P2P session.
	 * 
	 * @param p2pSessionId
	 * @return
	 */
	public List<String> getP2PSessionActivities(String p2pSessionId) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<String> result = new ArrayList<String>();
		try {
			String SQL = "SELECT ACTIVITY_ID FROM P2P_SESSION_ACTIVITIES WHERE P2P_SESSION_ID=?";
			stmt = this.connection.prepareStatement(SQL);
			stmt.setString(1, p2pSessionId);
			rset = stmt.executeQuery();
			while (rset.next()) {
				result.add(rset.getString(1));
			}
			return result;
		} catch (SQLException e) {
			log.debug(e.getMessage());
			return result;
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
	 * Returns true if the specified activity ecists in the specified P2P session.
	 * 
	 * @param p2pSessionId
	 * @param activityId
	 * @return
	 */
	public boolean activityExists(String p2pSessionId, String activityId) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			String SQL = "SELECT 1 FROM P2P_SESSION_ACTIVITIES WHERE " +
					"P2P_SESSION_ID=? AND ACTIVITY_ID=?";
			stmt = this.connection.prepareStatement(SQL);
			stmt.setString(1, p2pSessionId);
			stmt.setString(2, activityId);
			rset = stmt.executeQuery();
			if (rset.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			log.debug(e.getMessage());
			return false;
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
	 * Adds a new P2P_SESSION_NEIGHBORS row.
	 * 
	 * @param p2pSessionId
	 * @param neighborEndpoint
	 */
	public void addP2PSessionNeighor(String p2pSessionId, String neighborEndpoint) {
		PreparedStatement stmt = null;
		try {
			String SQL = "INSERT INTO P2P_SESSION_NEIGHBORS " +
					"(P2P_SESSION_ID, NEIGHBOR_ENDPOINT) VALUES (?,?)";
			stmt = this.connection.prepareStatement(SQL);
			stmt.setString(1, p2pSessionId);
			stmt.setString(2, neighborEndpoint);
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
	 * Returns the P2P endpoint addresses of the neighbors involved in the 
	 * specified P2P session.
	 * 
	 * @param p2pSessionId
	 * @return
	 */
	public List<String> getP2PSessionNeighbors(String p2pSessionId) {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<String> result = new ArrayList<String>();
		try {
			String SQL = "SELECT NEIGHBOR_ENDPOINT FROM P2P_SESSION_NEIGHBORS WHERE P2P_SESSION_ID=?";
			stmt = this.connection.prepareStatement(SQL);
			stmt.setString(1, p2pSessionId);
			rset = stmt.executeQuery();
			while (rset.next()) {
				result.add(rset.getString(1));
			}
			return result;
		} catch (SQLException e) {
			log.debug(e.getMessage());
			return result;
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
