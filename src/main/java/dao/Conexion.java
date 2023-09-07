package dao;

import java.sql.*;

public class Conexion {

    public static Connection conectar() throws ClassNotFoundException, SQLException {       
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/libreria", "root","");
    }
}
