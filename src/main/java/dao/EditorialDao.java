package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Editorial;

public class EditorialDao {

    public static boolean registrar(Editorial editorial) {
        boolean estado = false;
        try
        {
            String consulta = "INSERT INTO editoriales (nit, nombre, telefono, direccion, email, sitioweb) VALUES(?,?,?,?,?,?)";
            Connection con = Conexion.conectar();
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, editorial.getNit());
            st.setString(2, editorial.getNombre());
            st.setString(3, editorial.getTelefono());
            st.setString(4, editorial.getDireccion());
            st.setString(5, editorial.getEmail());
            st.setString(6, editorial.getSitioWeb());
            st.execute();
            estado = true;

        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(EditorialDao.class.getName()).log(Level.SEVERE, null, ex);
            estado = false;
        }
        return estado;
    }

    public boolean actualizarEditorial(Editorial editorial) {

        String consulta = "UPDATE editoriales SET nombre=?,telefono=?,direccion=?,email=?,sitioweb=? WHERE nit=?";
        boolean estado = false;
        try
        {
            Connection con = Conexion.conectar();
            PreparedStatement st = con.prepareStatement(consulta);           
            st.setString(1, editorial.getNombre());
            st.setString(2, editorial.getTelefono());
            st.setString(3, editorial.getDireccion());
            st.setString(4, editorial.getEmail());
            st.setString(5, editorial.getSitioWeb());
             st.setString(6, editorial.getNit());
            st.execute();
            estado=true;
            
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(EditorialDao.class.getName()).log(Level.SEVERE, null, ex);
            estado=false;
        }
        return estado;
    }

    public static Editorial listarByNit(String nit) {
        Editorial editorial = new Editorial();
        String consulta = "SELECT * FROM editoriales WHERE nit=?";
        try
        {
            Connection con = Conexion.conectar();
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, nit);
            ResultSet rs = st.executeQuery();

            while (rs.next())
            {
                editorial.setNit(rs.getString("nit"));
                editorial.setNombre(rs.getString("nombre"));
                editorial.setTelefono(rs.getString("telefono"));
                editorial.setDireccion(rs.getString("direccion"));
                editorial.setEmail(rs.getString("email"));
                editorial.setSitioWeb(rs.getString("sitioweb"));

            }

        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(EditorialDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return editorial;
    }

    public boolean eliminarEditorial(String nit) {
        String consulta = "DELETE FROM editoriales WHERE nit=?";
        Connection con;
        boolean estado = false;
        try
        {
            con = Conexion.conectar();
            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setString(1, nit);
            pst.execute();
            estado = true;

        } catch (ClassNotFoundException | SQLException ex)
        {
            estado = false;
            Logger.getLogger(EditorialDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return estado;
    }

}
