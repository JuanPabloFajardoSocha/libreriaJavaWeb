package controlador;

import dao.CategoriaDao;
import dao.EditorialDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import modelo.Categoria;

import modelo.Editorial;

@WebServlet(name = "EditorialControl", urlPatterns =
{
    "/EditorialControl"
})
public class EditorialControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditorialControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditorialControl at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EditorialDao editorialDao = new EditorialDao();
        String accion = request.getParameter("accion").toString();
        if (accion.equals("eliminar"))
        {

            if (editorialDao.eliminarEditorial(request.getParameter("nit")))
            {
                request.setAttribute("mensaje", "Datos eliminados exitosamente");

            } else
            {
                request.setAttribute("mensaje", "Error al eliminar registro");
            }
            request.getRequestDispatcher("listaEditoriales.jsp").forward(request, response);

        } else if (accion.equals("editar"))
        {
            Editorial editorial = editorialDao.listarByNit(request.getParameter("nit"));
            request.setAttribute("nit", editorial.getNit());
            request.setAttribute("nombre", editorial.getNombre());
            request.setAttribute("telefono", editorial.getTelefono());
            request.setAttribute("direccion", editorial.getDireccion());
            request.setAttribute("email", editorial.getEmail());
            request.setAttribute("sitioweb", editorial.getSitioWeb());
            request.getRequestDispatcher("editarEditorial.jsp").forward(request, response);

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
                EditorialDao editorialDao = new EditorialDao();
                Editorial editorial = new Editorial();
                editorial.setNit(request.getParameter("nit"));
                editorial.setNombre(request.getParameter("nombre"));
                editorial.setTelefono(request.getParameter("telefono"));
                editorial.setDireccion(request.getParameter("direccion"));
                editorial.setEmail(request.getParameter("email"));
                editorial.setSitioWeb(request.getParameter("sitioweb"));

                if (editorialDao.actualizarEditorial(editorial))
                {
                    request.setAttribute("mensaje", "Registro actualizado con exito");
                } else
                {
                    request.setAttribute("mensaje", "Falla al actualizar registro");
                }
                request.getRequestDispatcher("listaEditoriales.jsp").forward(request, response);
            }
        }
        String mensaje = "";
        Editorial editorial = new Editorial();
        String nit = request.getParameter("nit");
        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");
        String email = request.getParameter("email");
        String sitioWeb = request.getParameter("sitioWeb");

        editorial.setNit(nit);
        editorial.setNombre(nombre);
        editorial.setTelefono(telefono);
        editorial.setDireccion(direccion);
        editorial.setEmail(email);
        editorial.setSitioWeb(sitioWeb);
        if (EditorialDao.registrar(editorial))
        {
            mensaje = "Editorial registrada con exito!!";
            request.setAttribute("mensaje", mensaje);
        } else
        {
            mensaje = "Fallo al resgistrar los datos!!";
            request.setAttribute("mensaje", mensaje);
        }

        request.getRequestDispatcher("registroEditorial.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
