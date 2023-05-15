package Utils.TableGenerator;

import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class DOTG extends TableGenerator {
    /*CDESC Dated Object Table Generator, implements Table Generator.
    *  Given two dates generates a table with all the objects found within those given dates*/
    String a;

    @Override
    public DefaultTableModel generateTable(String title, List<Object> atributes)throws SQLException {
        Date initialDate=(Date)atributes.get(0),finalDate=(Date)atributes.get(1);
        this.startConn();
        this.setQuery(this.getConn().createStatement());



        return null;
    }
}
