package Utils.TableGenerator;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class ROATG extends TableGenerator {
    @Override
    public DefaultTableModel generateTable(String title, List<Object> atributes) throws SQLException {

        this.startConn();
        this.setQuery(this.getConn().createStatement());
        this.setResult(this.getQuery().executeQuery("SELECT P_Nombre, P_Apellido, COUNT(P_DNI_Ingresa) FROM  Personas, Objetos GROUP BY (P_DNI_Ingresa) ORDER BY P_Apellido"));
        DefaultTableModel ret= this.resultToTable(this.getResult());
        this.getConn().close();
        title="Los Investigadores cargados, junto con la cantidad de objetos que encontraron son: ";
        return ret;
    }
    /*CDESC Reseacher Object Amount Table Generator, implements Table Generator
    *  Generates a Table with the First name, last name, and amount of objects found of every researcher in the BD*/
}
