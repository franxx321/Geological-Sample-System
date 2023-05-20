package Utils.TableGenerator;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class BWTG extends TableGenerator{

    /*CDESC Box Weight Table Generator, extends Table Generator
    *  Returns the code of all boxes alongside with the sum of the weights of the objects that contains*/
    @Override
    public DefaultTableModel generateTable(String title, List<Object> atributes) throws SQLException {
        this.startConn();
        this.setQuery(this.getConn().createStatement());
        this.setResult(this.getQuery().executeQuery("SELECT Ca_Cod_Contiene, SUM(O_Peso) FROM Objetos GROUP BY Ca_Cod_Contiene"));
        DefaultTableModel ret = this.resultToTable(this.getResult());
        this.getConn().close();
        title= "El peso de cada caja es: ";
        return ret;
    }
}
