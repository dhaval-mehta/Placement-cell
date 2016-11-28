
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import users.User;

/**
 *
 * @author Dhaval
 */
@WebServlet(name = "ChangePassword", urlPatterns =
{
    "/users/ChangePassword"
})
public class ChangePassword extends HttpServlet
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
	String username;

	if (request.getAttribute("username") != null)
	    username = (String) request.getAttribute("username");
	else
	    return;

	Session session = null;
	Transaction transaction = null;

	try
	{
	    session = hibernate.HibernateUtil.getSessionFactory().openSession();
	    transaction = session.beginTransaction();

	    User user = (User) session.get(User.class, username);

	    String password = request.getParameter("password");

	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));

	    if (Arrays.equals(hashedPassword, user.getPassword()))
	    {
		user.setPassword(hashedPassword);
		session.update(user);
	    }
	    else
		request.setAttribute("message", "Passwords doesn't match");

	    transaction.commit();
	    session.close();
	}
	catch (NoSuchAlgorithmException e)
	{
	    response.getWriter().print("Cannot generate hash for password.");
	}
	catch (HibernateException e)
	{
	    if (transaction != null)
		transaction.rollback();

	    throw new RuntimeException("Cannot fatch user Details", e);
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
