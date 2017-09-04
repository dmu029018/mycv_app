package controller;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import helpers.RedirectUtils;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        RequestDispatcher rd;
        
        try
        {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            
            if(user.authentication())
            {
                request.setAttribute("notification", "User successfully logged in");
                 
                user = User.findBy("email", email).get(0);
                
                //SESION
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);

                //rd = request.getRequestDispatcher("control-panel.jsp");
            }
            else
            {
                request.setAttribute("notification", "Invalid credentials");
                //rd = request.getRequestDispatcher("index.jsp");
            }
            
            RedirectUtils.redirect(request, response, "control-panel");
            
        }
        catch(Exception ex)
        {
            RedirectUtils.redirectToNotLogged(request, response);
            //rd = request.getRequestDispatcher("index.jsp");
        }
        
        //rd.forward(request, response);
    }

    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}