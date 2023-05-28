package Utils.ArithmeticResultGenerator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBSRG extends ArithmeticResultGenerator {
    /*CDESC Data Base Statistics Result Generator, extends ArithmeticResultGenerator.
        Finds every amount that is added to the Data Base and returns a list of objects.
     */


    @Override
    public List<Object> generateResults() throws SQLException {
        String[] entities={"Personas","Cuadriculas","Objetos","Cajas"};
        String[] ids={"P_Dni","Cu_Cod","O_Cod","Ca_Cod"};
        String statement="SELECT COUNT(P_Dni) FROM Personas";
        ArrayList <Object> ret = new ArrayList<>();
        try{
        this.startConn();
        for (int i=0;i<4;i++){
            this.setQuery(this.getConn().createStatement());
            String auxString = "COUNT("+ids[i]+")";
            this.setResult(this.getQuery().executeQuery("SELECT " +auxString+ " FROM "+entities[i]));
            this.getResult().next();
            ret.add(i,this.getResult().getInt(1));
        }
        this.getConn().close();
        }
        catch(SQLException e){
            System.out.println("fallo!" +e.getMessage());
        }
        return ret;
    }
}
