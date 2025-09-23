package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Validar")
public class Validar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("txtCorreo");
        String contra = request.getParameter("txtContrasena");

        UsuarioDAO dao = new UsuarioDAO();
        Usuario u = dao.verificarLogin(usuario, contra);

        if (u != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", u);
            request.getRequestDispatcher("ahorcado.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
