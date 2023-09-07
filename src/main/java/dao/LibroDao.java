package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Categoria;
import modelo.Editorial;
import modelo.Libros;

public class LibroDao {

    public static boolean registrar(Libros libros) {
        boolean estado = false;
        try
        {

            String consulta = "INSERT INTO libros (isbn, titulo, descripcion, nombre_autor, publicacion, fecha_registro, codigo_categoria,nit_editorial) VALUES(?,?,?,?,?,?,?,?)";
            Connection con = Conexion.conectar();
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, libros.getIsbn());
            st.setString(2, libros.getTitulo());
            st.setString(3, libros.getDescripcion());
            st.setString(4, libros.getNombreAutor());
            st.setString(5, libros.getPublicacion());
            st.setString(6, libros.getFechaRegistro());
            st.setInt(7, libros.getCodigoCategoria());
            st.setString(8, libros.getNitEditorial());
            st.execute();

            estado = true;

        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(LibroDao.class.getName()).log(Level.SEVERE, null, ex);

        }
        return estado;
    }

    public static boolean actualizar(Libros libros) throws ClassNotFoundException, SQLException {
        String consulta = "UPDATE libros SET titulo=?,"
                + "descripcion=?, "
                + "nombre_autor=?, "
                + "publicacion=?, "
                + "fecha_registro=?,"
                + "codigo_categoria =?,"
                + "nit_editorial =? WHERE isbn=?";
        Connection con = Conexion.conectar();
        PreparedStatement st = con.prepareStatement(consulta);

        st.setString(1, libros.getTitulo());
        st.setString(2, libros.getDescripcion());
        st.setString(3, libros.getNombreAutor());
        st.setString(4, libros.getPublicacion());
        st.setString(5, libros.getFechaRegistro());
        st.setInt(6, libros.getCodigoCategoria());
        st.setString(7, libros.getNitEditorial());
        st.setString(8, libros.getIsbn());

        if (st.executeUpdate() > 0)
        {
            return true;
        }

        return false;

    }

    public static boolean eliminar(String isbn) throws ClassNotFoundException, SQLException {
        String consulta = "DELETE FROM libros WHERE isbn=?";
        Connection con = Conexion.conectar();
        PreparedStatement st = con.prepareStatement(consulta);

        st.setString(1, isbn);

        if (st.execute())
        {
            return true;
        }

        return false;

    }

    public static List<Libros> listar() throws ClassNotFoundException, SQLException {
        List<Libros> lista = new ArrayList<>();

        String consulta = "SELECT libros.isbn, libros.titulo, libros.descripcion, "
                + "libros.nombre_autor, libros.publicacion, libros.fecha_registro, "
                + "categorias.nombre,editoriales.nombre AS nombreEditorial"
                + " FROM libros,categorias,editoriales WHERE"
                + " categorias.codigo=libros.codigo_categoria "
                + "AND editoriales.nit=libros.nit_editorial;";
        Connection con = Conexion.conectar();
        PreparedStatement st = con.prepareStatement(consulta);
        ResultSet rs = st.executeQuery();

        while (rs.next())
        {
            Libros libros = new Libros();
            libros.setIsbn(rs.getString("isbn"));
            libros.setTitulo(rs.getString("titulo"));
            libros.setDescripcion(rs.getString("descripcion"));
            libros.setNombreAutor(rs.getString("nombre_autor"));
            libros.setPublicacion(rs.getString("publicacion"));
            libros.setFechaRegistro(rs.getString("fecha_registro"));
            libros.setCodigoCategoriaText(rs.getString("nombre"));
            libros.setNitEditorial(rs.getString("nombreEditorial"));
            lista.add(libros);
        }
        return lista;
    }

    public static List<Libros> getLibroByIsbn(String isbn) throws ClassNotFoundException, SQLException {
        List<Libros> lista = new ArrayList<>();

        String consulta = "SELECT * FROM libros WHERE isbn=? ";
        Connection con = Conexion.conectar();
        PreparedStatement st = con.prepareStatement(consulta);
        st.setString(1, isbn);
        ResultSet rs = st.executeQuery();

        while (rs.next())
        {
            Libros libros = new Libros();
            libros.setIsbn(rs.getString("isbn"));
            libros.setTitulo(rs.getString("titulo"));
            libros.setDescripcion(rs.getString("descripcion"));
            libros.setNombreAutor(rs.getString("nombre_autor"));
            libros.setPublicacion(rs.getString("publicacion"));
            libros.setFechaRegistro(rs.getString("fecha_registro"));
            libros.setCodigoCategoria(rs.getInt("codigo_categoria"));
            libros.setNitEditorial(rs.getString("nit_editorial"));
            lista.add(libros);
        }
        return lista;
    }

    public static List<Categoria> categorias() throws ClassNotFoundException, SQLException {
        List<Categoria> lista = new ArrayList<>();

        String consulta = "SELECT * FROM categorias";
        Connection con = Conexion.conectar();
        PreparedStatement st = con.prepareStatement(consulta);
        ResultSet rs = st.executeQuery();

        while (rs.next())
        {
            Categoria categoria = new Categoria();
            categoria.setCodigo(rs.getInt("codigo"));
            categoria.setNombre(rs.getString("nombre"));

            lista.add(categoria);
        }
        return lista;
    }

    public static List<Editorial> editoriales() throws ClassNotFoundException, SQLException {
        List<Editorial> lista = new ArrayList<>();
        String consulta = "SELECT * FROM editoriales";
        Connection con = Conexion.conectar();
        PreparedStatement st = con.prepareStatement(consulta);
        ResultSet rs = st.executeQuery();

        while (rs.next())
        {
            Editorial editorial = new Editorial();
            editorial.setNit(rs.getString("nit"));
            editorial.setNombre(rs.getString("nombre"));
            editorial.setTelefono(rs.getString("telefono"));
            editorial.setDireccion(rs.getString("direccion"));
            editorial.setEmail(rs.getString("email"));
            editorial.setSitioWeb(rs.getString("sitioweb"));

            lista.add(editorial);
        }
        return lista;
    }

}
