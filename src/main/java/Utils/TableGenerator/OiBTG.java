package Utils.TableGenerator;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class OiBTG extends TableGenerator {
    /*CDESC Objects in Box Table Generator, implements table generator.
     *  Given a box Code Generates a table with the detail of all the objects stored in said box  */
    @Override
    public DefaultTableModel generateTable(String title, List<Object> atributes) throws SQLException {
        //TOASK que siginifica obtener el detalle
        String B_Code =(String)atributes.get(0);
        String statement="SELECT ";
        this.startConn();
        return null;
    }



}
