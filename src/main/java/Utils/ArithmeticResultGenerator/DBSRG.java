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
        String[] ids={"P_Dni","Cu_Cod","O_Cod","Ca_Cood"};
        String statement="SELECT COUNT(?) FROM ?";
        ArrayList <Object> ret = new ArrayList<>();
        try{
        this.startConn();
        this.setP_query(this.getConn().prepareStatement(statement));
        for (int i=0;i<4;i++){
            this.getP_query().setString(1,entities[i]);
            this.getP_query().setString(2,ids[i]);
            this.setResult(this.getP_query().executeQuery());
            this.getResult().next();
            ret.add(i,this.getResult().getInt(1));
        }
        this.getConn().close();
        }
        catch(SQLException e){
        }
        return ret;
    }
}
