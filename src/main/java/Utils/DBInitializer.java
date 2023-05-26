/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.SQLException;

/**
 *
 * @author franc
 */
public class DBInitializer extends DBConnector{
    
    public void initializeDB(){
        try{
            this.startConn();
            this.setQuery(this.getConn().createStatement());
            this.getQuery().executeQuery("CREATE TABLE IF NOT EXIST Objetos("
                    + "O_Cod TEXT NOT NULL PRIMARY KEY,"
                    + "O_Nombre TEXT,"
                    + "O_Tipoextraccion TEXT,"
                    + "O_Alto FLOAT,"
                    + "O_Largo FLOAT,"
                    + "O_Espesor FLOAT,"
                    + "O_Peso FLOAT,"
                    + "O_Cantidad INTEGER,"
                    + "O_Fecharegisto DATE,"
                    + "O_Descripcion TEXT,"
                    + "O_Origen TEXT,"
                    + "Cu_Cod_Asocia TEXT,"
                    + "Ca_Cod_Contine TEXT,"
                    + "P_Dni_Ingresa INTEGER,"
                    + "FOREIGN KEY(P_Dni_Ingresa) REFERENCES Personas(P_Dni),"
                    + "FOREIGN KEY(Ca_Cod_Contiene)REFERENCES Cajas(Ca_Cod),"
                    + "FOREIGN KEY(Cu_Cod_Asocia)REFERENCES CUADRICULA(Cu_Cod)"
                    + ")");
            this.setQuery(this.getConn().createStatement());
            this.getQuery().executeQuery("CREATE TABLE IF NOT EXISTS Personas("
                    + "P_Dni INTEGER NOT NULL PRIMARY KEY,"
                    + "P_Nombre TEXT NOT NULL,"
                    + "P_ Apellido TEXT NOT NULL,"
                    + "P_Email TEXT"
                    + ")");
            this.setQuery(this.getConn().createStatement());
            this.getQuery().executeQuery("CREATE TABLE IF NOT EXISTS Liticos ("
                    + "O_Cod TEXT NOT NULL PRIMARY KEY,"
                    + "L_FechaCreacion, DATE"
                    + "FOREIGN KEY(O_Cod) REFENCES Objetos(O_Cod)"
                    + ")");
            this.setQuery(this.getConn().createStatement());
            this.getQuery().executeQuery("CREATE TABLE IF NOT EXISTS Ceramicos("
                    + "O_Cod TEXT NOT NULL PRIMARY KEY,"
                    + "C_Color TEXT,"
                    + "FOREIGN KEY(O_Cod) REFERENCES Objetos(O_Cod)"
                    + ")");
            this.setQuery(this.getConn().createStatement());
            this.getQuery().executeQuery("CREATE TABLE IF NOT EXISTS Cajas("
                    + "Ca_Cod TEXT NOT NULL PRIMARY KEY,"
                    + "Ca_Fecha DATE"
                    + "Ca_Lugar TEXT"
                    + ")");
            this.setQuery(this.getConn().createStatement());
            this.getQuery().executeQuery("CREATE TABLE IF NOT EXISTS Sitios("
                    + "S_Cod TEXT NOT NULL PRIMARY KEY,"
                    + "S_Localidad TEXT"
                    + ")");
            this.setQuery(this.getConn().createStatement());
            this.getQuery().executeQuery("CREATE TABLE IF NOT EXISTS Cuadriculas("
                    + "Cu_Cod TEXT NOT NULL PRIMARY KEY,"
                    + "S_Cod_Dividido TEXT,"
                    + "FOREIGN KEY(S_Cod_Dividido) REFERENCES Sitios(S_Cod)"
                    + ")");
        }
        catch(SQLException e){
            
        }
    }
    
}
