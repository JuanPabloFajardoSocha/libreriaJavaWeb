
package controlador;

import dao.LibroDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;


@WebServlet(name = "ControlListarLibros", urlPatterns =
{
    "/ControlListarLibros"
})
public class ControlListarLibros extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        doGet(request, response);
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        System.out.println("ENTRO A GET");

        try
        {
            LibroDao libroDao = new LibroDao();
            request.setAttribute("libros", LibroDao.listar());
           

        } catch (ClassNotFoundException | SQLException e)
        {
            throw new RuntimeException(e);
        }       
        
        request.getRequestDispatcher("listarLibros.jsp").forward(request,
                response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
