package controlador;

import dao.LibroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Libros;

@WebServlet(name = "LibrosControl", urlPatterns =
{
    "/LibrosControl"
})
public class LibrosControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ENTRO A GET");

        try
        {
            LibroDao libroDao = new LibroDao();
            request.setAttribute("categorias", LibroDao.categorias());
            request.setAttribute("editoriales", LibroDao.editoriales());

        } catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e);
        }

        LocalDate fechaActual = LocalDate.now();
        request.setAttribute("fechaActual", fechaActual);
        request.getRequestDispatcher("registroLibro.jsp").forward(request,
                response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entro a post");
        Libros libros = new Libros();

        String isbn = request.getParameter("isbn");
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String nombreAutor = request.getParameter("nombreAutor");
        String publicacion = request.getParameter("publicacion");
        String fechaRegistro = request.getParameter("fechaRegistro");
        String codigoCategoria = request.getParameter("codigoCategoria");
        String nitEditorial = request.getParameter("nitEditorial");
       

        libros.setIsbn(isbn);
        libros.setTitulo(titulo);
        libros.setDescripcion(descripcion);
        libros.setNombreAutor(nombreAutor);
        libros.setPublicacion(publicacion);
        libros.setFechaRegistro(fechaRegistro);
        libros.setCodigoCategoria(Integer.parseInt(codigoCategoria));
        libros.setNitEditorial(nitEditorial);

        if (LibroDao.registrar(libros))
        {
            request.setAttribute("mensaje", "Registro Exitoso!!");
        } else
        {
            request.setAttribute("mensaje", "Falla al registrar los datos");
        }

        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
