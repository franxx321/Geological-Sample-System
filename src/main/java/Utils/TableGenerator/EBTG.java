package Utils.TableGenerator;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class EBTG extends TableGenerator {

    @Override
    public DefaultTableModel generateTable(String title, List<Object> atributes) throws SQLException {
        //TODO cambiar todo
        this.startConn();
        this.setQuery(this.getConn().createStatement());
        this.setResult(this.getQuery().executeQuery("SELECT * FROM  Cajas"));
        this.getConn().close();
        return null;
    }

    /*CDESC Empty Box Table Geneator , Implements Table Generator.
    *  Generates a table with the code and the place of all the empty boxes in the BD*/
}
