/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author franc
 */
public class DBInitializer extends DBConnector {
    
    public void initializeDB(){
        ArrayList <String> instructions =new ArrayList();
        try{
        String auxString;
        File insertFile= new File("textfiles\\insert.txt");
        FileReader fileReader = new FileReader(insertFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        while((auxString=bufferedReader.readLine())!=null){
                if(auxString.contains("--Insert tabla Cajas")){
                    System.out.println("Llegamos al punto de error");
                }
                if((!auxString.contains("--"))&&(!auxString.equals(""))){
                    instructions.add(auxString);
                }
        }
        }
        catch(IOException e){
            System.out.println("Fallo leida de archivo" +e.getMessage());
        
        }
        
        
        try{
            this.startConn();
            this.setQuery(this.getConn().createStatement());
            this.getQuery().execute("CREATE TABLE IF NOT EXISTS Sitios("
                    + "S_Cod VARCHAR(30) NOT NULL PRIMARY KEY,"
                    + "S_Localidad VARCHAR(30)"
                    + ")");
            this.setQuery(this.getConn().createStatement());
            this.getQuery().execute("CREATE TABLE IF NOT EXISTS Cuadriculas("
                    + "Cu_Cod VARCHAR(30) NOT NULL PRIMARY KEY,"
                    + "S_Cod_Dividido VARCHAR(30),"
                    + "FOREIGN KEY(S_Cod_Dividido) REFERENCES Sitios(S_Cod)"
                    + ")");
            this.setQuery(this.getConn().createStatement());
            this.getQuery().execute("CREATE TABLE IF NOT EXISTS Personas("
                    + "P_Dni INTEGER NOT NULL PRIMARY KEY,"
                    + "P_Nombre VARCHAR(30) NOT NULL,"
                    + "P_Apellido VARCHAR(30) NOT NULL,"
                    + "P_Email VARCHAR(30)"
                    + ")");
            this.setQuery(this.getConn().createStatement());
            this.getQuery().execute("CREATE TABLE IF NOT EXISTS Cajas("
                    + "Ca_Cod VARCHAR(30) NOT NULL PRIMARY KEY,"
                    + "Ca_Fecha DATE,"
                    + "Ca_Lugar VARCHAR(30)"
                    + ")");
            this.setQuery(this.getConn().createStatement());
            this.getQuery().execute("CREATE TABLE IF NOT EXISTS Objetos("
                    + "O_Cod VARCHAR(15) NOT NULL PRIMARY KEY,"
                    + "O_Nombre VARCHAR(30),"
                    + "O_Tipoextraccion VARCHAR(30),"
                    + "O_Alto FLOAT,"
                    + "O_Largo FLOAT,"
                    + "O_Espesor FLOAT,"
                    + "O_Peso FLOAT,"
                    + "O_Cantidad INTEGER,"
                    + "O_Fecharegisto DATE,"
                    + "O_Descripcion VARCHAR(30),"
                    + "O_Origen VARCHAR(30),"
                    + "Cu_Cod_Asocia VARCHAR(30),"
                    + "Ca_Cod_Contiene VARCHAR(30),"
                    + "P_Dni_Ingresa INTEGER,"
                    + "FOREIGN KEY(P_Dni_Ingresa) REFERENCES Personas(P_Dni),"
                    + "FOREIGN KEY(Ca_Cod_Contiene)REFERENCES Cajas(Ca_Cod),"
                    + "FOREIGN KEY(Cu_Cod_Asocia)REFERENCES CUADRICULAS(Cu_Cod)"
                    + ")");

            this.setQuery(this.getConn().createStatement());
            this.getQuery().execute("CREATE TABLE IF NOT EXISTS Liticos ("
                    + "O_Cod VARCHAR(30) NOT NULL PRIMARY KEY,"
                    + "L_FechaCreacion INTEGER,"
                    + "FOREIGN KEY(O_Cod) REFERENCES Objetos(O_Cod)"
                    + ")");
            this.setQuery(this.getConn().createStatement());
            this.getQuery().execute("CREATE TABLE IF NOT EXISTS Ceramicos("
                    + "O_Cod VARCHAR(30) NOT NULL PRIMARY KEY,"
                    + "C_Color VARCHAR(30),"
                    + "FOREIGN KEY(O_Cod) REFERENCES Objetos(O_Cod)"
                    + ")");
            for(String statement: instructions){
                this.setQuery(this.getConn().createStatement());
                this.getQuery().execute(statement);
            }
            
        }
        catch(SQLException e){
            System.out.println("Falllo!"+e.getMessage());
            
        }
    }
    
}
