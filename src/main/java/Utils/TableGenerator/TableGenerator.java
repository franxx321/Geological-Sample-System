package Utils.TableGenerator;

import Utils.DBConnector;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;
import java.util.Vector;

public abstract class  TableGenerator extends DBConnector {

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
    }
}
