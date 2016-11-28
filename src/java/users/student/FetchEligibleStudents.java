/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.student;

import academic_info.BTech_Student_Academic_Info;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pdfGenerator.PdfGenerator;

/**
 *
 * @author Dhaval
 */
@WebServlet(name = "FetchEligibleStudents", urlPatterns =
{
    "/admin/placement_management/FetchEligibleStudents"
})
public class FetchEligibleStudents extends HttpServlet
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
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();

	try
	{

	    FetchBTechStudents(request, response);
	}
	catch (Exception ex)
	{
	    log("Error at " + getServletInfo(), ex);
	}

    }

    private static void FetchBTechStudents(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

	Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
	Transaction transaction = session.beginTransaction();

	String hql = "From BTech_Student_Academic_Info as info where"
		+ " info.ssc_percentage >= :ssc_percentage and "
		+ " (info.hsc_percentage >= :hsc_percentage or "
		+ " info.diploma_cpi >= :diploma_cpi) and"
		+ " info.btech_cpi >= :btech_cpi";

	Query query = session.createQuery(hql);

	query.setParameter("ssc_percentage", Double.parseDouble(request.getParameter("ssc_percentage")));
	query.setParameter("hsc_percentage", Double.parseDouble(request.getParameter("hsc_percentage")));
	query.setParameter("diploma_cpi", Double.parseDouble(request.getParameter("diploma_cpi")));
	query.setParameter("btech_cpi", Double.parseDouble(request.getParameter("btech_cpi")));

	List<BTech_Student_Academic_Info> students = query.list();

	ArrayList<ArrayList<String>> table = new ArrayList<>();

	ArrayList<String> header = new ArrayList<>();

	header.add("Student ID");
	header.add("name");
	header.add("SSC Percentage");
	header.add("is D2d");
	header.add("HSC Percentage");
	header.add("Diploma CPI");
	header.add("BTech CPI");

	table.add(header);

	String sql = "select name from student_master where username = :username";
	SQLQuery query1 = session.createSQLQuery(sql);

	for (BTech_Student_Academic_Info student : students)
	{
	    ArrayList<String> list = new ArrayList<>();

	    query1.setParameter("username", student.getUsername());
	    List list1 = query1.list();
	    list.add(student.getUsername());
	    list.add((String) list1.get(0));
	    list.add(String.valueOf(student.getSsc_percentage()));
	    list.add(String.valueOf(student.isD2d()));
	    list.add(String.valueOf(student.getHsc_percentage()));
	    list.add(String.valueOf(student.getDiploma_cpi()));
	    list.add(String.valueOf(student.getBtech_cpi()));

	    table.add(list);
	}

	transaction.commit();
	session.close();

	String filePath = request.getServletContext().getRealPath("") + File.separator + "files" + File.separator;
	File file = new File(filePath);
	file.mkdir();
	String fileName = "eligible students.pdf";
	PdfGenerator pdfGenerator = new PdfGenerator(filePath + fileName);
	pdfGenerator.createPdf(table);
	response.sendRedirect("/files/" + fileName);
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
