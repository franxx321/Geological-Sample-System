package Utils.TableGenerator;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.List;

public class BTG  extends TableGenerator {



    @Override
    public DefaultTableModel generateTable(String title, List<Object> atributes) throws SQLException {
        this.startConn();
        this.setQuery(this.getConn().createStatement());
        this.setResult(this.getQuery().executeQuery("SELECT * FROM  Cajas"));
        DefaultTableModel ret = this.resultToTable(this.getResult());
        title="Las cajas actualmente cargadas son:";
        return ret;
    }
}
