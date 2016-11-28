/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tags;

import company.Company;
import company.CompanyList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Shreya
 */
public class AddCompamies extends SimpleTagSupport
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
	PageContext pageContext = (PageContext) getJspContext();
	JspWriter out = pageContext.getOut();

	HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
	int page;

	try
	{
	    page = Integer.parseInt(request.getParameter("page"));
	}
	catch (Exception e)
	{
	    page = 1;
	}

	page--;

	for (int i = page * 5, j = 0; j < 5; j++, i++)
	{
	    if (CompanyList.getCompaniesList().size() <= i)
	    {
		break;
	    }
	    Company company = CompanyList.getCompaniesList().get(i);

	    out.println("<div class=\"blog-item\">\n"
		    + "                        <div class=\"row\">\n"
		    + "                            <div class=\"col-xs-12 col-sm-2 text-center\">\n"
		    + "                                <div class=\"entry-meta\">\n"
		    + "                                    <span id=\"publish_date\">07  NOV</span>\n"
		    + "                                    <span><i class=\"fa fa-user\"></i> <a href=\"#\">John Doe</a></span>\n"
		    + "                                    <span><i class=\"fa fa-comment\"></i> <a href=\"blog-item.html#comments\">2 Comments</a></span>\n"
		    + "                                    <span><i class=\"fa fa-heart\"></i><a href=\"#\">56 Likes</a></span>\n"
		    + "                                </div>\n"
		    + "                            </div>\n"
		    + "                                \n"
		    + "                            <div class=\"col-xs-12 col-sm-10 blog-content\">\n"
		    + "                                \n"
		    + "                                <h2 style=\"color: #d43133\">"
		    + company.getName() + "</h2> <h3>" + company.getDescription()
		    + "                                </h3><a class=\"btn btn-primary readmore\" href=\"blog-item.html\">");
	    if (request.getSession().getAttribute("userType").equals("student"))
	    {
		out.print("Register ");
	    }
	    else
	    {
		out.print("Edit Details ");
	    }

	    out.print("<i class=\"fa fa-angle-right\"></i></a>\n"
		    + "                            </div>\n"
		    + "                        </div>    \n"
		    + "                    </div><!--/.blog-item-->\n"
		    + "                    ");
	}
    }
}
