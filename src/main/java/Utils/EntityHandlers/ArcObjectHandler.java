/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils.EntityHandlers;

import Models.ArcObject;
import Models.CeramicObject;
import Models.LithicObject;
import Utils.DBConnector;
import java.sql.SQLException;

/**
 *
 * @author franc
 */
public class ArcObjectHandler extends DBConnector {
    
    public void addObject(ArcObject newObject)throws SQLException{
        this.startConn();
        this.setP_query(this.getConn().prepareStatement("INSERT INTO Objetos VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"));
            this.getP_query().setString(1,newObject.getId());
            this.getP_query().setString(2, newObject.getName());
            this.getP_query().setString(3,newObject.getExtractionType());
            this.getP_query().setFloat(4, newObject.getHeight());
            this.getP_query().setFloat(5, newObject.getLength());
            this.getP_query().setFloat(6,newObject.getWitdth());
            this.getP_query().setFloat(7, newObject.getWeight());
            this.getP_query().setInt(8, newObject.getAmount());
            this.getP_query().setDate(9, newObject.getDateFound());
            this.getP_query().setString(10,newObject.getDescription());
            this.getP_query().setString(11,newObject.getOrigin());
            this.getP_query().setString(12, newObject.getqId());
            this.getP_query().setString(13,newObject.getbId());
            this.getP_query().setString(14,newObject.getrId());
            String auxString;
            if (newObject instanceof  LithicObject){
                 auxString = "L";
            }
            else{
                auxString= "C";
            }
            this.getP_query().setString(15,auxString);
            this.getP_query().executeUpdate();
            
            if(newObject instanceof LithicObject){
            LithicObject newLithicObject =(LithicObject)newObject;
            this.setP_query(this.getConn().prepareStatement("INSERT INTO Liticos VALUES(?,?)"));
            this.getP_query().setString(1, newLithicObject.getId());
            this.getP_query().setInt(2,newLithicObject.getCreationYear());
            this.getP_query().executeUpdate();
            }
            else{
                CeramicObject newCeramicObject= (CeramicObject)newObject;
                this.setP_query(this.getConn().prepareStatement("INSERT INTO Ceramicos VALUES(?,?)"));
                this.getP_query().setString(1, newCeramicObject.getId());
                this.getP_query().setString(2, newCeramicObject.getColor());
                this.getP_query().executeUpdate();
   
            }
            this.getConn().close();        
    }
    
    
}
