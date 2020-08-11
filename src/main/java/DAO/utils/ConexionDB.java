package DAO.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {


    private static final String url = "jdbc:mysql://localhost:3306/tp-integrador-died-2020?useSSL=false";
    private static final String user = "root";
    private static final String pass = "";

    private static boolean _TABLAS_CREADAS = false;

    private static final String TABLA_CREATE_CAMION =
            "CREATE TABLE  IF NOT EXISTS `tp-integrador-died-2020`.`camion` ( " +
                    "		  `ID` INT NOT NULL AUTO_INCREMENT, " +
                    "		  `PATENTE` VARCHAR(14) NULL, " +
                    "		  `MARCA` VARCHAR(45) NULL, " +
                    "		  `MODELO` VARCHAR(45) NULL, " +
                    "		  `KM` VARCHAR(45) NULL, " +
                    "		  `COSTO_KM` DECIMAL(12,2) NULL, " +
                    "		  `COSTO_HORA` DECIMAL(12,2) NULL, " +
                    "		  `FECHA_COMPRA` DATETIME NULL, " +
                    "		  PRIMARY KEY (`ID`)) ";


    private ConexionDB() {
        // no se pueden crear instancias de esta clase
    }

    private static void verificarCrearTablas() {
        if (!_TABLAS_CREADAS) {
            Connection conn = ConexionDB.crearConexion();
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                boolean tablaCamionCreada = stmt.execute(TABLA_CREATE_CAMION);
                _TABLAS_CREADAS = tablaCamionCreada;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Connection crearConexion() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public static Connection getConexion() {
        verificarCrearTablas();
        return crearConexion();
    }

}
