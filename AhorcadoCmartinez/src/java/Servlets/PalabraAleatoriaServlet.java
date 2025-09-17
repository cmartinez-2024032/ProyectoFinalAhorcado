package Servlets;

import Modelo.Palabra;
import Modelo.PalabraDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PalabraAleatoriaServlet", urlPatterns = {"/PalabraAleatoria"})
public class PalabraAleatoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PalabraDAO dao = new PalabraDAO();
        Palabra palabra = dao.obtenerPalabraAleatoria();

        if (palabra != null) {
            HttpSession session = request.getSession();
            session.setAttribute("palabra", palabra.getPalabra());
            session.setAttribute("pista1", palabra.getPista1());
            session.setAttribute("pista2", palabra.getPista2());
            session.setAttribute("pista3", palabra.getPista3());

            response.sendRedirect("ahorcado.jsp");
        } else {
            response.getWriter().println("No se pudo obtener una palabra de la base de datos.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
