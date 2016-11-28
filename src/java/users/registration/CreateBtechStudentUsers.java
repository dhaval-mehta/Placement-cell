/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.registration;

import academic_info.BTech_Student_Academic_Info;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.StringTokenizer;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.Session;
import org.hibernate.Transaction;
import users.Student;
import users.Student.Gender;
import users.User;
import department.Department;
import java.util.ArrayList;
import java.util.Date;
import pdfGenerator.PdfGenerator;
import users.Student.CourseType;

/**
 *
 * @author Dhaval
 */
@WebServlet(name = "CreateBtechStudentUsers", urlPatterns =
{
    "/admin/student_management/CreateBtechStudentUsers"
})
public class CreateBtechStudentUsers extends HttpServlet
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

	int count = 0;

	String message;
	Session session = null;
	Transaction transaction = null;

	ArrayList< ArrayList<String>> credentialTable = new ArrayList<>();

	ArrayList<String> header = new ArrayList<>();

	header.add("Username");
	header.add("Password");

	credentialTable.add(header);

	try
	{
	    session = hibernate.HibernateUtil.getSessionFactory().openSession();
	    transaction = session.beginTransaction();

	    /**
	     * opening file
	     */
	    String path = (String) request.getAttribute("filePath");
	    FileInputStream userSheet = new FileInputStream(new File(path));
	    HSSFWorkbook workbook = new HSSFWorkbook(userSheet);
	    HSSFSheet spreadSheet = workbook.getSheetAt(0);

	    Iterator<Row> rowIterator = spreadSheet.rowIterator();

	    /**
	     * Skipping First row
	     */
	    if (rowIterator.hasNext())
		rowIterator.next();

	    /**
	     * Reading file row by row
	     */
	    while (rowIterator.hasNext())
	    {
		Row row = rowIterator.next();

		if (isEmptyRow(row))
		    continue;

		String username = String.valueOf((int) row.getCell(2).getNumericCellValue());

		ArrayList<String> credential = registerUser(username, session);
		insert_Student_Details(username, row, session);
		insert_BTech_Academic_info(username, row, session);

		credentialTable.add(credential);
		count++;
	    }

	    String filePath = getServletContext().getRealPath("") + File.separator + "files" + File.separator;
	    File file = new File(filePath);
	    file.mkdir();
	    String fileName = "BTech student credential list.pdf";
	    PdfGenerator pdfGenerator = new PdfGenerator(filePath + fileName);
	    pdfGenerator.createPdf(credentialTable);

	    log("file created at : " + filePath + fileName);

	    transaction.commit();

	    response.sendRedirect("/files/" + fileName);

	}
	catch (IOException | NoSuchAlgorithmException | RuntimeException | DocumentException e)
	{

	    log(getServletInfo(), e);

	    if (transaction != null)
		transaction.rollback();

	    message = "Error : " + e.getLocalizedMessage();

	    if (e.getCause() != null)
		message += "<br> Cause : " + e.getCause().getLocalizedMessage();

	    request.setAttribute("message", message);

	    RequestDispatcher rd = request.getRequestDispatcher("/admin/student_management/regiester.jsp");
	    rd.forward(request, response);
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

    private ArrayList<String> registerUser(String username, Session session) throws RuntimeException, NoSuchAlgorithmException
    {
	String password = randomAlphaNumericString(10);

	MessageDigest digest = MessageDigest.getInstance("SHA-256");
	byte[] hashedPassword = digest.digest(password.getBytes(StandardCharsets.UTF_8));

	User user = new User();
	user.setUsername(username);
	user.setPassword(hashedPassword);
	user.setStudent(true);

	session.save(user);

	ArrayList<String> credential = new ArrayList<>();
	credential.add(username);
	credential.add(password);

	return credential;
    }

    private void insert_Student_Details(String username, Row row, Session session) throws RuntimeException
    {
	Student student = new Student();
	student.setUsername(username);

	student.setRollNo(row.getCell(1).getStringCellValue());
	student.setName(row.getCell(3).getStringCellValue());
	student.setAdmissionType(row.getCell(4).getStringCellValue());

	String gender = row.getCell(6).toString();
	student.setGender(Gender.valueOf(gender));

	Date dob = row.getCell(7).getDateCellValue();
	student.setDob(dob);

	student.setEmail(row.getCell(25).toString());
	student.setMobileNo1((long) row.getCell(26).getNumericCellValue());
	student.setMobileNo2((long) row.getCell(27).getNumericCellValue());
	student.setAddress1((row.getCell(28) == null) ? null : row.getCell(28).toString());
	student.setAddress2((row.getCell(29) == null) ? null : row.getCell(29).toString());
	student.setAddress3((row.getCell(30) == null) ? null : row.getCell(30).toString());
	student.setCity((row.getCell(31) == null) ? null : row.getCell(31).toString());
	student.setPincode(((row.getCell(32) == null) ? null : (int) row.getCell(32).getNumericCellValue()));

	StringTokenizer st = new StringTokenizer(row.getCell(33).getStringCellValue(), ",");

	Department department = new Department();
	department.setId(1);
	department.setName("CE");
	student.setDepartment(department);

	student.setCourse(CourseType.BTech);

	while (st.hasMoreTokens())
	    student.getFieldOfInterest().add(st.nextToken());

	session.save(student);
    }

    private void insert_BTech_Academic_info(String username, Row row, Session session) throws RuntimeException
    {
	BTech_Student_Academic_Info academic_Info = new BTech_Student_Academic_Info();
	academic_Info.setUsername(username);

	/**
	 * Inserting SSC result
	 */
	academic_Info.setSsc_percentage(row.getCell(8).getNumericCellValue());
	academic_Info.setSsc_board(row.getCell(9).getStringCellValue());

	/**
	 * Inserting Diploma result
	 */
	boolean d2d = (row.getCell(12).getNumericCellValue() == 1);
	academic_Info.setD2d(d2d);
	double diploma_cpi = d2d ? row.getCell(13).getNumericCellValue() : 0;
	academic_Info.setDiploma_cpi(diploma_cpi);

	/**
	 * Inserting HSC result
	 */
	double hsc_percentage = d2d ? 0 : row.getCell(10).getNumericCellValue();
	academic_Info.setHsc_percentage(hsc_percentage);
	academic_Info.setHsc_board(row.getCell(11).toString());

	/**
	 * inserting BTech result
	 */
	academic_Info.setBtech_sem1_spi(row.getCell(14).getNumericCellValue());
	academic_Info.setBtech_sem2_spi(row.getCell(15).getNumericCellValue());
	academic_Info.setBtech_sem3_spi(row.getCell(16).getNumericCellValue());
	academic_Info.setBtech_sem4_spi(row.getCell(17).getNumericCellValue());
	academic_Info.setBtech_sem5_spi(row.getCell(18).getNumericCellValue());
	academic_Info.setBtech_sem6_spi(row.getCell(19).getNumericCellValue());
//	academic_Info.setBtech_sem7_spi(row.getCell(20).getNumericCellValue());
//	academic_Info.setBtech_sem8_spi(row.getCell(21).getNumericCellValue());

	academic_Info.getBtech_cpi();

	session.save(academic_Info);

    }

    private boolean isEmptyRow(Row row)
    {
	boolean isRowEmpty = true;

	if (row == null)
	{
	    return true;
	}

	for (int j = 0; j < row.getLastCellNum(); j++)
	{
	    if (row.getCell(j) == null)
	    {
		continue;
	    }

	    if (!row.getCell(j).toString().trim().equals(""))
	    {
		isRowEmpty = false;
		break;
	    }
	}

	return isRowEmpty;
    }

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomAlphaNumericString(int count)
    {
	StringBuilder builder = new StringBuilder();
	while (count-- != 0)
	{
	    int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
	    builder.append(ALPHA_NUMERIC_STRING.charAt(character));
	}
	return builder.toString();
    }

}
