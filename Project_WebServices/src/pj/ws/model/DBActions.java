package pj.ws.model;

import java.sql.*;

public class DBActions {

	private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public DBActions(String url, String login, String password)
    {
        try {
            conn = DriverManager.getConnection(url, login, password);
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }


    public Statement getStatement() throws SQLException
    {
        stmt = conn.createStatement();
        return stmt;
    }
    
    public PreparedStatement getPStatement(String PQuery) throws SQLException
    {
        pstmt = conn.prepareStatement(PQuery);
        return pstmt;
    }
    
    public ResultSet getResultSet(String SQLQuery) throws SQLException
    {
        stmt = getStatement();
        rs = stmt.executeQuery(SQLQuery);
        return rs;
    }	


}
