/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dhaval
 */
public class ManageCompany extends SimpleTagSupport
{

    Session session = null;
    Transaction transaction = null;
    JspWriter out = null;
    HttpServletRequest request;
    PageContext pageContext;

    /**
     * Called by the container to invoke this tag. The implementation of this method is provided by the tag library developer, and handles all tag processing, body iteration, etc.
     *
     * @throws javax.servlet.jsp.JspException
     * @throws java.io.IOException
     */
    @Override
    public void doTag() throws JspException, IOException
    {
	pageContext = (PageContext) getJspContext();
	request = (HttpServletRequest) pageContext.getRequest();

	out = getJspContext().getOut();

	try
	{
	    session = hibernate.HibernateUtil.getSessionFactory().openSession();
	    transaction = session.beginTransaction();

	    if (request.getParameter("action") != null)
	    {
		if (request.getParameter("name") == null)
		    fetch();
		else
		    update();
	    }
	    else
	    {
		if (request.getParameter("name") != null)
		    register();
	    }
	}
	catch (NumberFormatException | ParseException | HibernateException e)
	{
	    pageContext.getServletContext().log("Error at ManageCompany", e);

	    if (transaction != null)
		transaction.rollback();
	}
	finally
	{

	    if (session != null)
		session.close();
	}
    }

    private void update() throws HibernateException, NumberFormatException, ParseException, IOException
    {
	int cid = Integer.parseInt(request.getParameter("cid"));
	String name = request.getParameter("name");
	String description = request.getParameter("description");
	String venue = request.getParameter("venue");
	int minPackage = Integer.parseInt(request.getParameter("minPackage"));
	int maxPackage = Integer.parseInt(request.getParameter("maxPackage"));

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date placementDate = new Date(sdf.parse(request.getParameter("event_date")).getTime());
	Date lastDate = new Date(sdf.parse(request.getParameter("last_date")).getTime());

	Company company = new Company(cid, minPackage, maxPackage, name, description, venue, lastDate, placementDate);

	if (!lastDate.before(placementDate))
	{
	    request.setAttribute("company", company);
	    request.setAttribute("errorMessage", "<hr>Last date for registration must be smaller than the date of placement.");
	    return;
	}

	session.update(company);
	transaction.commit();

	out.print("<p style=\"text-align: center; margin-top: 100px; font-size: xx-large\">");
	out.print("Successfully updated.");
	out.print("</p>");

    }

    private void fetch() throws HibernateException, NumberFormatException
    {
	int cid = Integer.parseInt(request.getParameter("cid"));

	session = hibernate.HibernateUtil.getSessionFactory().openSession();
	transaction = session.beginTransaction();
	Company company = (Company) session.get(Company.class, cid);
	transaction.commit();

	if(company == null)
	{
		request.setAttribute("errorMessage", "<hr> Invalid Company ID");
	}
	
	request.setAttribute("company", company);
    }

    private void register() throws HibernateException, NumberFormatException, ParseException, IOException
    {
	String name = request.getParameter("name");
	String description = request.getParameter("description");
	String venue = request.getParameter("venue");
	int minPackage = Integer.parseInt(request.getParameter("minPackage"));
	int maxPackage = Integer.parseInt(request.getParameter("maxPackage"));

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date placementDate = new Date(sdf.parse(request.getParameter("event_date")).getTime());
	Date lastDate = new Date(sdf.parse(request.getParameter("last_date")).getTime());

	session = hibernate.HibernateUtil.getSessionFactory().openSession();
	transaction = session.beginTransaction();

	Company company = new Company(minPackage, maxPackage, name, description, venue, lastDate, placementDate);

	int id = (Integer) session.save(company);

	transaction.commit();

	out.print("Company registered successfully with ID = " + id);

    }
}
