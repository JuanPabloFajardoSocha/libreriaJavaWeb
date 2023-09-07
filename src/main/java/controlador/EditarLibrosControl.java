package controlador;

import dao.LibroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Libros;

@WebServlet(name = "EditarLibrosControl", urlPatterns =
{
    "/EditarLibrosControl"
})
public class EditarLibrosControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditarLibrosControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarLibrosControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entro a get de editar");
        LibroDao libroDao = new LibroDao();
        try
        {

            for (Libros libro : libroDao.getLibroByIsbn(request.getParameter("id")))
            {
                request.setAttribute("isbn", libro.getIsbn());
                request.setAttribute("titulo", libro.getTitulo());
                request.setAttribute("descripcion", libro.getDescripcion());
                request.setAttribute("nombre_autor", libro.getNombreAutor());
                request.setAttribute("publicacion", libro.getPublicacion());
                System.out.println("Fecha publicacion: " + libro.getPublicacion());
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                SimpleDateFormat formatoFinal = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaDate = (Date) formato.parse(libro.getFechaRegistro());
                String fechaFinal = formatoFinal.format(fechaDate);
                request.setAttribute("fecha_registro", fechaFinal);                
               
                request.setAttribute("codigo_categoria", libro.getCodigoCategoria());
                System.out.println(libro.getCodigoCategoria());
                request.setAttribute("nit_editorial  ", libro.getNitEditorial());

            }

            request.setAttribute("categorias", libroDao.categorias());
            request.setAttribute("editoriales", libroDao.editoriales());

        } catch (ClassNotFoundException | SQLException | ParseException ex)
        {
            Logger.getLogger(EditarLibrosControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getRequestDispatcher("editarLibro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try
        {
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
            
            if (LibroDao.actualizar(libros))
            {
                request.setAttribute("mensaje", "Registro Exitoso!!");
            } else
            {
                request.setAttribute("mensaje", "Falla al registrar los datos");
            }
            
            
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(EditarLibrosControl.class.getName()).log(Level.SEVERE, null, ex);
        }
         request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
