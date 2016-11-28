package tags;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Dhaval
 */
public class AddNavigation extends SimpleTagSupport
{

    /**
     * Called by the container to invoke this tag. The implementation of this method is provided by the tag library developer, and handles all tag processing, body iteration, etc.
     *
     * @throws javax.servlet.jsp.JspException
     * @throws java.io.IOException
     */
    @Override
    public void doTag() throws JspException, IOException
    {
	JspWriter out = getJspContext().getOut();
	PageContext pageContext = (PageContext) getJspContext();

	HttpSession session = pageContext.getSession();

	if (session.getAttribute("username") != null)
	{
	    String userType = (String) session.getAttribute("userType");

	    //System.err.println(((HttpServletRequest) pageContext.getRequest()).getRequestURL());
	    if (userType != null)
	    {
		if (userType.equalsIgnoreCase("student"))
		{
		    out.println("<li><a href='/students/updateDetails.jsp'>View / Update Details </a></li>");
		    out.println("<li><a href='/students/viewEvents.jsp'>View Upcoming Events</a></li>");
		}
		else if (userType.equalsIgnoreCase("administrator"))
		{
		    out.println("<li><a href='/admin/index.jsp'> Home </a></li>");
		    out.println("<li class=\"dropdown\">"
			    + "<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Student Management <i class=\"fa fa-angle-down\"></i></a>\n"
			    + "<ul class=\"dropdown-menu\">\n"
			    + "<li><a href='/admin/student_management/regiester.jsp'>Regiester Students</a></li>\n"
			    + "<li><a href='/admin/student_management/updateDetails.jsp'>View / Update Student Details </a></li>\n"
			    + "<li><a href='/admin/student_management/ChangeStudentPassword.jsp'>Reset Student's Password </a></li>\n"
			    + "</ul>");

		    out.println("<li class=\"dropdown\">"
			    + "<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Company Management <i class=\"fa fa-angle-down\"></i></a>\n"
			    + "<ul class=\"dropdown-menu\">\n"
			    + "<li><a href='/admin/company_management/CompanyRegistration.jsp'>Regiester Company</a></li>\n"
			    + "<li><a href='/admin/company_management/updateCompanyDetails.jsp'>Update Company Details</a></li>\n"
			    + "<li><a href='/admin/company_management/ViewCompanies.jsp'>View Upcoming Compnies</a></li>\n"
			    + "</ul>");

		    out.println("<li class=\"dropdown\">"
			    + "<a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Placement Management <i class=\"fa fa-angle-down\"></i></a>\n"
			    + "<ul class=\"dropdown-menu\">\n"
			    + "<li><a href='/admin/placement_management/Get_Eligible_Students_List.jsp'>Find Eligible Students</a></li>\n"
			    + "<li><a href='/admin/placement_management/generateReport.jsp'>Generate Placement Report</a></li>\n"
			    + "<li><a href='/admin/placement_management/InsertPlacementDetails.jsp'>Insert Placement Details</a></li>\n"
			    + "</ul>");
		}

	    }
	    else
	    {
		throw new RuntimeException("Username is set but type is not set");
	    }
	}
	else
	{
	    out.println("<li><a href='/login.jsp'>Login</a></li>");
	}

	if (session.getAttribute("username") != null)
	{
	    out.println("<li><a href='/logout'>logout</a></li>");
	}
    }

}
