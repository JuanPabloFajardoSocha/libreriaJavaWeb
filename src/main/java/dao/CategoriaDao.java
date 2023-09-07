package dao;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Categoria;

public class CategoriaDao {

    public static boolean registrar(Categoria categoria) throws ClassNotFoundException, SQLException {
        String consulta = "INSERT INTO categorias (nombre) VALUES(?)";
        Connection con = Conexion.conectar();
        PreparedStatement st = con.prepareStatement(consulta);
        st.setString(1, categoria.getNombre());
        if (st.executeUpdate() > 0)
        {
            return true;
        }

        return false;

    }

    public static Categoria listarByCodigo(int codigo) {
        
        Categoria categoria = new Categoria();
        String consulta = "SELECT * FROM categorias WHERE codigo=?";
        try
        {

            Connection con = Conexion.conectar();
            PreparedStatement st = con.prepareStatement(consulta);
            st.setInt(1, codigo);
            ResultSet rs = st.executeQuery();

            while (rs.next())
            {
                
                categoria.setCodigo(rs.getInt("codigo"));
                categoria.setNombre(rs.getString("nombre"));
                
            }

        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoria;
    }

    public boolean eliminarCategoria(int codigo) {
        boolean estado = false;
        try
        {
            String consulta = "DELETE FROM categorias WHERE codigo=?";
            Connection con = Conexion.conectar();
            PreparedStatement stm = con.prepareStatement(consulta);
            stm.setInt(1, codigo);
            stm.execute();
            estado = true;

        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
            estado = false;
        }
        return estado;
    }

    public boolean actualizarCategoria(Categoria categoria) {
        boolean estado = false;

        String consulta = "UPDATE categorias SET nombre=? WHERE codigo=?";
        try
        {
            Connection con = Conexion.conectar();
            PreparedStatement stm = con.prepareStatement(consulta);
            stm.setString(1, categoria.getNombre());
            stm.setInt(2, categoria.getCodigo());
            stm.execute();
            estado = true;

        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(CategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
            estado = false;
        }
        return estado;
    }
}
