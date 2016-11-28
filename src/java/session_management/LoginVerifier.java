package session_management;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import users.User;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Dhaval
 */
@WebServlet(urlPatterns =
{
    "/Authentication"
})
public class LoginVerifier extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException
    {
	Session session = null;
	Transaction transaction = null;

	try
	{

	    SessionFactory factory = hibernate.HibernateUtil.getSessionFactory();
	    session = factory.openSession();
	    transaction = session.getTransaction();
	    transaction.begin();

	    String username = request.getParameter("username");
	    User user = (User) session.get(users.User.class, username);
	    transaction.commit();

	    String password = request.getParameter("password");

	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));

	    if (user != null && Arrays.equals(user.getPassword(), hashedPassword))
	    {
		request.getSession().setAttribute("username", user.getUsername());

		if (user.isStudent())
		{
		    request.getSession().setAttribute("userType", "student");
		    response.sendRedirect("/student/index.jsp");
		}
		else if (user.isAdministrator())
		{
		    request.getSession().setAttribute("userType", "administrator");
		    response.sendRedirect("/admin/index.jsp");
		}
	    }
	    else
		response.sendRedirect("login.jsp?message=Invalid Credentials");
	}
	catch (IOException | NoSuchAlgorithmException | RuntimeException e)
	{
	    request.setAttribute("message", "Some error occured while processing request");

	    log("Error in authentication servlert", e);

	    if (transaction != null)
		transaction.rollback();

	    response.getWriter().println("Error while processing request"
		    + "Error Message : " + e.getLocalizedMessage());
	}
	finally
	{
	    if (session != null)
		session.close();
	}

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
	    throws ServletException, IOException
    {
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
	    throws ServletException, IOException
    {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
	return "Short description";
    }// </editor-fold>

}
