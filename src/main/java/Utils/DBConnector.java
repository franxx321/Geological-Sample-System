package Utils;

import java.sql.*;

public abstract class DBConnector {

    private static final String DB_NAME = "tpl2";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String DB_USER = "prueba";
    private static final String DB_PWD = "Holamundo1";

    private /*static*/ Connection conn = null;
    private /*static*/ Statement query = null;
    private /*static*/ PreparedStatement p_query = null;
    private /*static*/ ResultSet result = null;
    public  Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void startConn()throws SQLException {
        try{
        this.conn=DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);}
        catch(SQLException e){
            System.out.println("Fallo!" + e.getMessage());
        
    }
    }

    public  Statement getQuery() {
        return query;
    }

    public  void setQuery(Statement query) {
        this.query = query;
    }

    public  PreparedStatement getP_query() {
        return p_query;
    }

    public  void setP_query(PreparedStatement p_query) {
        this.p_query = p_query;
    }

    public  ResultSet getResult() {
        return result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }
}
