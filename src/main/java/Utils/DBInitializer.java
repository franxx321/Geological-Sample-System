/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author franc
 */
public class DBInitializer extends DBConnector {


    private String correctDate(String statement) {
        String prefix = statement.substring(0, statement.indexOf('-') - 2);
        String day = statement.substring(statement.indexOf('-') - 2, statement.indexOf('-'));
        String month = statement.substring(statement.indexOf('-') + 1, statement.indexOf('-') + 3);
        String year = statement.substring(statement.indexOf('-') + 4, statement.indexOf('-') + 8);
        String suffix = statement.substring(statement.indexOf('-') + 8);
        return prefix + year + "-" + month + "-" + day + suffix;
    }

    public void initializeDB() {
        ArrayList<String> instructions = new ArrayList();
        try {
            String auxString;
            File insertFile = new File("textfiles\\insert.txt");
            FileReader fileReader = new FileReader(insertFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((auxString = bufferedReader.readLine()) != null) {

                if ((!auxString.startsWith("--")) && (!auxString.equals(""))) {
                    int a = auxString.indexOf('-');
                    if (auxString.contains("--Insert Tabla Personas")) {
                        System.out.println("llegamos al problema");
                    }
                    if (a != -1) {
                        auxString = this.correctDate(auxString);
                    }
                    instructions.add(auxString);
                }
            }
        } catch (IOException e) {
            System.out.println("Fallo leida de archivo" + e.getMessage());

        }


        try {
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
                    + "P_Email VARCHAR(45),"
                    + "P_Telefono VARCHAR(30)"
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
                    + "O_Fecharegistro DATE,"
                    + "O_Descripcion VARCHAR(30),"
                    + "O_Origen VARCHAR(30),"
                    + "Cu_Cod_Asocia VARCHAR(30),"
                    + "Ca_Cod_Contiene VARCHAR(30),"
                    + "P_Dni_Ingresa INTEGER,"
                    + "O_Es VARCHAR(1),"
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


            this.setQuery(this.getConn().createStatement());
            this.setResult(this.getQuery().executeQuery("SELECT COUNT(O_Cod) FROM Objetos WHERE O_Cod = 'OBJ1'"));
            this.getResult().next();
            int b = this.getResult().getInt(1);
            if (b == 0) {
                for (String statement : instructions) {
                    this.setQuery(this.getConn().createStatement());
                    this.getQuery().execute(statement);
                }
                this.setQuery(this.getConn().createStatement());
                this.getQuery().execute("INSERT INTO Personas  (P_Nombre ,P_Apellido,P_Email,P_Dni,P_Telefono ) " +
                        "VALUES('Rodolphe','Rominov','rrominov@sciencedaily.com',25544555,'7135986253')");

                this.setQuery(this.getConn().createStatement());
                this.setResult(this.getQuery().executeQuery("SELECT P_Dni " +
                        "FROM Personas " +
                        "WHERE P_Nombre ='Benji' AND P_Apellido ='Colchett'"));
                this.getResult().next();
                String p_Dni = this.getResult().getString(1);
                //TOASK dejamos esta opcion o no eliminamos al Benji este
               /* this.setQuery(this.getConn().createStatement());
                this.setResult(this.getQuery().executeQuery("SELECT O_Cod " +
                        "FROM Objetos " +
                        "WHERE P_Dni_Ingresa = p_Dni"));
                while (this.getResult().next()){
                    this.setP_query(this.getConn().prepareStatement("DELETE FROM Objetos " +
                            "WHERE O_Cod = ?"));
                    this.getP_query().setString(1,this.getResult().getString(1));
                }*/
                this.setP_query(this.getConn().prepareStatement("DELETE FROM Personas " +
                        "WHERE P_Dni= ?"));
                this.getP_query().setString(1, p_Dni);
                this.getP_query().executeUpdate();

            }
            this.getConn().close();
        } catch (SQLException e) {
            System.out.println("Falllo!" + e.getMessage());

        }
    }

}
