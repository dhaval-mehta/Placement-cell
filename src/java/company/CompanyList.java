/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company;

import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Shreya
 */
public class CompanyList
{

    static ArrayList<Company> companyList = null;

    static
    {
	updateList();
    }

    public static ArrayList<Company> getCompaniesList()
    {
	if (companyList == null)
	    updateList();

	return companyList;
    }

    public static void updateList()
    {
	try
	{
	    String hql = "from Company";
	    Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
	    Query query = session.createQuery(hql);
	    Transaction transaction = session.beginTransaction();

	    companyList = (ArrayList<Company>) query.list();
	    transaction.commit();
	    session.close();
	}
	catch (HibernateException e)
	{
	}
    }
}
