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
        String statement="SELECT * FROM Objetos WHERE Ca_Cod_Contiene = ?";
        this.startConn();
        this.setP_query(this.getConn().prepareStatement(statement));
        this.getP_query().setString(1,(String)atributes.get(0));
        this.setResult(this.getP_query().executeQuery());
        DefaultTableModel ret = this.resultToTable(this.getResult());
        this.getConn().close();
        title = "Los objetos cargados en la caja "+ B_Code+" son: ";
        return ret;
    }



}
