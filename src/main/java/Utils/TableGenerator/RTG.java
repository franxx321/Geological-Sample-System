package Utils.TableGenerator;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class RTG  extends TableGenerator{
    @Override
    public DefaultTableModel generateTable(String title, List<Object> atributes)throws SQLException {
        this.startConn();
        this.setQuery(this.getConn().createStatement());
        this.setResult(this.getQuery().executeQuery("SELECT * FROM  Personas"));
        DefaultTableModel ret = this.resultToTable(this.getResult());
        title="Las Personas actualmente cargadas son:";
        this.getConn().close();
        return ret;
    }
}
