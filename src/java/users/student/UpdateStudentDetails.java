/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.student;

import academic_info.BTech_Student_Academic_Info;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import users.Student;

/**
 *
 * @author Dhaval
 */
@WebServlet(name = "UpdateStudentDetails", urlPatterns =
{
    "/admin/student_management/UpdateStudentDetails"
})
public class UpdateStudentDetails extends HttpServlet
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

	Session session = null;
	Transaction transaction = null;

	String username = request.getParameter("username");
	String message = "";

	try
	{
	    session = hibernate.HibernateUtil.getSessionFactory().openSession();
	    transaction = session.beginTransaction();

	    Student student = (Student) session.get(Student.class, username);
	    student.setUsername(username);
	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String gender = request.getParameter("gender").toUpperCase();
	    long mobileNo1 = Long.parseLong(request.getParameter("mobile1"));
	    long mobileNo2 = Long.parseLong(request.getParameter("mobile2"));
	    String city = request.getParameter("city");
	    String address1 = request.getParameter("address1");
	    String address2 = request.getParameter("address2");
	    String address3 = request.getParameter("address3");
	    int pincode = Integer.parseInt(request.getParameter("pincode"));

	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    java.sql.Date dob = new java.sql.Date(sdf.parse(request.getParameter("dob")).getTime());

	    student.setName(name);
	    student.setEmail(email);
	    student.setDob(dob);
	    student.setGender(Student.Gender.valueOf(gender));
	    student.setMobileNo1(mobileNo1);
	    student.setMobileNo2(mobileNo2);
	    student.setAddress1(address1);
	    student.setAddress2(address2);
	    student.setAddress3(address3);
	    student.setCity(city);
	    student.setPincode(pincode);

	    StringTokenizer st = new StringTokenizer(request.getParameter("interest"), ",");
	    student.getFieldOfInterest().clear();

	    while (st.hasMoreElements())
	    {
		student.getFieldOfInterest().add(st.nextToken());
	    }

	    session.update(student);
	    request.setAttribute("student", student);
	    updateAcademicInfo(student, request, session);

	    transaction.commit();

	    message = "Details updated successfully.";

	}
	catch (NumberFormatException | ParseException | HibernateException e)
	{
	    if (transaction != null)
		transaction.rollback();

	    log(getServletInfo(), e);

	    message = "Error : " + e.getLocalizedMessage();
	}
	finally
	{
	    if (session != null)
		session.close();

	    request.setAttribute("message", message);
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

    private void updateAcademicInfo(Student student, HttpServletRequest request, Session session)
    {
	switch (student.getCourse())
	{
	    case BTech:
		update_BTech_Student_Academic_Info(student.getUsername(), request, session);
		break;

	    default:
		throw new RuntimeException("Student academic infomation not found");
	}
    }

    private void update_BTech_Student_Academic_Info(String username, HttpServletRequest request, Session session)
    {
	BTech_Student_Academic_Info academic_Info = (BTech_Student_Academic_Info) session.get(BTech_Student_Academic_Info.class, username);
	academic_Info.setUsername(username);

	academic_Info.setSsc_board(request.getParameter("ssc_board"));
	academic_Info.setSsc_percentage(Double.parseDouble(request.getParameter("ssc_percentege")));

	boolean d2d = Boolean.parseBoolean(request.getParameter("is_d2d"));
	academic_Info.setD2d(d2d);

	if (d2d)
	{
	    double diploma_cpi = (Double.parseDouble(request.getParameter("diploma_cpi")));
	    academic_Info.setDiploma_cpi(diploma_cpi);
	    academic_Info.setHsc_board(null);
	    academic_Info.setHsc_percentage(0);
	}
	else
	{
	    academic_Info.setDiploma_cpi(0);
	    academic_Info.setHsc_board(request.getParameter("hsc_board"));
	    academic_Info.setHsc_percentage(Double.parseDouble(request.getParameter("hsc_percentage")));
	}

	academic_Info.setBtech_sem1_spi(Double.parseDouble(request.getParameter("btech_sem1_spi")));
	academic_Info.setBtech_sem2_spi(Double.parseDouble(request.getParameter("btech_sem2_spi")));
	academic_Info.setBtech_sem3_spi(Double.parseDouble(request.getParameter("btech_sem3_spi")));
	academic_Info.setBtech_sem4_spi(Double.parseDouble(request.getParameter("btech_sem4_spi")));
	academic_Info.setBtech_sem5_spi(Double.parseDouble(request.getParameter("btech_sem5_spi")));
	academic_Info.setBtech_sem6_spi(Double.parseDouble(request.getParameter("btech_sem6_spi")));
	academic_Info.setBtech_sem7_spi(Double.parseDouble(request.getParameter("btech_sem7_spi")));
	academic_Info.setBtech_sem8_spi(Double.parseDouble(request.getParameter("btech_sem8_spi")));

	request.setAttribute("academic_Info", academic_Info);
	session.update(academic_Info);
    }

}
