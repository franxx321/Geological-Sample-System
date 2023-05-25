/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils.EntityHandlers;

import Utils.*;
import java.sql.SQLException;

/**
 *
 * @author joako
 */

public class BoxHandler extends DBConnector{
    public int deleteBox(String cod)throws SQLException{
        this.startConn();
        this.setP_query(this.getConn().prepareStatement("SELECT COUNT(Ca_Cod_Contiene) FROM Objetos WHERE Ca_Cod_Contiene = ? GROUP BY (Ca_Cod_Contiene)"));
        this.getP_query().setString(1, cod);
        this.setResult(this.getP_query().executeQuery());
        this.getResult().next();
        if(this.getResult().getInt(1)!=0){
            return -3;
        }else{
            this.setP_query(this.getConn().prepareStatement("DELETE FROM Cajas WHERE Ca_Cod == cod"));
            return this.getP_query().executeUpdate();
        }
    }
}
