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
        DefaultTableModel ret;
        Date initialDate=(Date)atributes.get(0),finalDate=(Date)atributes.get(1);
        String statement= "SELECT O_Cod, O_Nombre FROM Objetos WHERE O_Fecharegistro> ? AND O_Fecharegistro < ?";
        this.startConn();
        this.setP_query(this.getConn().prepareStatement(statement));
        this.getP_query().setDate(1,initialDate);
        this.getP_query().setDate(2,finalDate);
        this.setResult(this.getP_query().executeQuery());
        ret= this.resultToTable(this.getResult());
        this.getConn().close();
        title="Los objetos encontrados entre "+initialDate.toString()+" y " + finalDate.toString() + " son: ";
        return ret;
    }
}
