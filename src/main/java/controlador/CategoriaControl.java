package controlador;

import dao.CategoriaDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Categoria;

public class CategoriaControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoriaControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoriaControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoriaDao categoriaDao = new CategoriaDao();
        String accion = request.getParameter("accion").toString();
        if (accion.equals("eliminar"))
        {

            if (categoriaDao.eliminarCategoria(Integer.parseInt(request.getParameter("codigo"))))
            {
                request.setAttribute("mensaje", "Datos eliminados exitosamente");

            } else
            {
                request.setAttribute("mensaje", "Error al eliminar registro");
            }
            request.getRequestDispatcher("listaCategorias.jsp").forward(request, response);
        } else if (accion.equals("editar"))
        {
            Categoria categoria = categoriaDao.listarByCodigo(Integer.parseInt(request.getParameter("codigo")));
            request.setAttribute("codigo", categoria.getCodigo());
            request.setAttribute("nombre", categoria.getNombre());
            request.getRequestDispatcher("editarCategoria.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String formulario = request.getParameter("formulario");
        if (formulario != null)
        {

            if (formulario.equals("form1"))
            {
                CategoriaDao categoriaDao = new CategoriaDao();
                Categoria categoria = new Categoria();
                categoria.setCodigo(Integer.parseInt(request.getParameter("codigo")));
                categoria.setNombre(request.getParameter("nombre"));

                if (categoriaDao.actualizarCategoria(categoria))
                {
                    request.setAttribute("mensaje", "Registro actualizado con exito");
                } else
                {
                    request.setAttribute("mensaje", "Falla al actualizar registro");
                }
                request.getRequestDispatcher("listaCategorias.jsp").forward(request, response);

            }

        }

        try
        {
            String nombre = request.getParameter("nombre");
            Categoria categotia = new Categoria();
            categotia.setNombre(nombre);
            if (CategoriaDao.registrar(categotia))
            {
                request.setAttribute("mensaje", "La categoria se registro exitosamente!!");
            } else
            {
                request.setAttribute("mensaje", "La categoria no se registro!!");
            }
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(CategoriaControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("registroCategoria.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
