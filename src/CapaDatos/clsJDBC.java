/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

import java.sql.*;

/**
 *
 * @author Manuelf
 */
public class clsJDBC {

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    private String driver, url, user, password;
    private Connection con;
    private Statement sent = null;

    //Constructor
    public clsJDBC() {
        this.driver = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5433/ArellanoValladolidManuelfP4";
        this.user = "postgres";
        //this.password = "Manu732903";
        this.password = "123456";
        this.con = null;
    }

    //Conexión con la base de datos
    public void conectar() throws Exception {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new Exception("Error al conectarse a la Base de Datos -->" + ex.getMessage());
        }
    }

    //Desconectarse
    public void desconectar() throws Exception {
        try {
            getCon().close();
        } catch (SQLException ex) {
            throw new Exception("Error al desconectarse de la Base de Datos -->" + ex.getMessage());
        }
    }

    //Ejecutar una consulta de tipo SELECT
    public ResultSet consultarBD(String strSQL) throws Exception {
        ResultSet rs = null;
        try {
            conectar();
            sent = getCon().createStatement();
            rs = sent.executeQuery(strSQL); //ejecuta la variable ingresada
            return rs;
        } catch (Exception e) {
            throw new Exception("Error al ejecutar consulta -->" + e.getMessage());
        } finally {
            if (getCon() != null) {
                desconectar();
            }
        }
    }

    //Unidad 2: Ejecutar una instrucción para hacer insert, update y delete
    public void ejecutarBD(String strSQL) throws Exception {
        try {
            conectar();
            sent = getCon().createStatement();
            sent.executeUpdate(strSQL);
        } catch (Exception e) {
            throw new Exception("Error al ejecutar instruccion -->" + e.getMessage() + " " + strSQL);
        } finally {
            if (getCon() != null) {
                desconectar();
            }
        }
    }
}
