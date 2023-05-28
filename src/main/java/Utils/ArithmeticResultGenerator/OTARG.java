package Utils.ArithmeticResultGenerator;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

public class OTARG extends ArithmeticResultGenerator{
    /*CDESC Object Type Amount Result Generator, extends ArithmeticResultGenerator.
        Returns amount of different types of objects.
     */

    @Override
    public List<Object> generateResults() throws SQLException {
        ArrayList<Object> ret = new ArrayList<>();
        try{
        this.startConn();
        this.setQuery(this.getConn().createStatement());
        this.setResult(this.getQuery().executeQuery("SELECT COUNT(O_Cod) FROM Liticos"));
        this.getResult().next();
        int lit = this.getResult().getInt(1);
        ret.add(0,lit);
        this.setQuery(this.getConn().createStatement());
        this.setResult(this.getQuery().executeQuery("SELECT COUNT(O_Cod) FROM Ceramicos"));
        this.getResult().next();
        int cer = this.getResult().getInt(1);
        ret.add(1, cer);
        this.getConn().close();}
        catch (SQLException e){

        }
        return ret;
    }
}
