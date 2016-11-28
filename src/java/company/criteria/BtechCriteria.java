/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.criteria;

import company.Company;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Dhaval
 */
@Entity
@Table(name = "btech_criteria")
public class BtechCriteria implements Serializable
{

    @Id
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "username", cascade = CascadeType.ALL)
    private Company company;

    @Column(name = "ssc_percentage")
    private double sscPercentage;

    @Column(name = "hsc_percentage")
    private double hscPercentage;

    @Column(name = "diploma_cpi")
    private double diplomaCpi;

    @Column(name = "btech_cpi")
    private double BTechCpi;

    public Company getCompany()
    {
	return company;
    }

    public void setCompany(Company company)
    {
	this.company = company;
    }

    public double getSscPercentage()
    {
	return sscPercentage;
    }

    public void setSscPercentage(double sscPercentage)
    {
	this.sscPercentage = sscPercentage;
    }

    public double getHscPercentage()
    {
	return hscPercentage;
    }

    public void setHscPercentage(double hscPercentage)
    {
	this.hscPercentage = hscPercentage;
    }

    public double getDiplomaCpi()
    {
	return diplomaCpi;
    }

    public void setDiplomaCpi(double diplomaCpi)
    {
	this.diplomaCpi = diplomaCpi;
    }

    public double getBTechCpi()
    {
	return BTechCpi;
    }

    public void setBTechCpi(double BTechCpi)
    {
	this.BTechCpi = BTechCpi;
    }

}
