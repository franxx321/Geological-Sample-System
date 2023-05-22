package Utils.ArithmeticResultGenerator;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WSRG extends ArithmeticResultGenerator {
    /*CDESC Weight Statistics Result Generator, extendes ArithmeticResultGenerator.
        Calculates average, maximum and minimum weight of objects.
     */
    @Override
    public List<Object> generateResults() throws SQLException {
        ArrayList <Object> ret = new ArrayList<>();
        this.startConn();
        this.setQuery(this.getConn().createStatement());
        this.setResult(this.getQuery().executeQuery("SELECT AVG(O_Peso), MAX(O_Peso), MIN(O_Peso) FROM Objetos"));
        this.getResult().next();
        float prom = this.getResult().getFloat(1);
        float min = this.getResult().getFloat(2);
        float max= this.getResult().getFloat(3);
        ret.add(0,prom);
        ret.add(1,min);
        ret.add(2,max);
        this.getConn().close();
        return ret;
    }
}

