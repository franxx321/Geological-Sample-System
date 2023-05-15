package Utils.TableGenerator;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;
import java.util.Vector;

public abstract class  TableGenerator {

    private static final String DB_NAME = "TPL2";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String DB_USER = "prueba";
    private static final String DB_PWD = "admin";

    private /*static*/ Connection conn = null;
    private /*static*/ Statement query = null;
    private /*static*/ PreparedStatement p_query = null;
    private /*static*/ ResultSet result = null;

    public abstract DefaultTableModel generateTable(String title, List<Object> atributes) throws SQLException;

    public  DefaultTableModel resultToTable(ResultSet resultSet) throws SQLException{
        // Esta es una función auxiliar que les permite convertir los resultados de las
        // consultas (ResultSet) a un modelo interpretable para la tabla mostrada en pantalla
        // (es decir, para un objeto de tipo JTable, ver línea 81)
        ResultSetMetaData metaData = resultSet.getMetaData();

        // creando las culmnas de la tabla
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // creando las filas de la tabla con los resultados de la consulta
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(resultSet.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    };

    public  Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public void startConn()throws SQLException{
        this.conn=DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
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
