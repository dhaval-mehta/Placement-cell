/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import users.Student;
import users.Student.CourseType;

/**
 *
 * @author Dhaval
 */
@Entity
@Table(name = "company_master")
public class Company implements Serializable
{

    public Company(int cid, int minPackage, int maxPackage, String name, String description, String venue, Date lastDate, Date placementDate)
    {
	this.cid = cid;
	this.minPackage = minPackage;
	this.maxPackage = maxPackage;
	this.name = name;
	this.description = description;
	this.venue = venue;
	this.lastDate = lastDate;
	this.placementDate = placementDate;
    }

    public Company(int minPackage, int maxPackage, String name, String description, String venue, Date lastDate, Date placementDate)
    {
	this.minPackage = minPackage;
	this.maxPackage = maxPackage;
	this.name = name;
	this.description = description;
	this.venue = venue;
	this.lastDate = lastDate;
	this.placementDate = placementDate;
    }

    public Company()
    {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;

    @Column(name = "min_package")
    private int minPackage;

    @Column(name = "max_package")
    private int maxPackage;

    private String name;
    private String description;
    private String venue;

    @Column(name = "last_date_for_registration")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastDate;

    @Column(name = "placement_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date placementDate;

    @Column(name = "created_on", updatable = false)
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date createdOn = new Date();

    @Column(name = "last_update")
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date updatedOn = new Date();

//    @ElementCollection(targetClass = Student.class)
//    @Enumerated(EnumType.ORDINAL)
//    @CollectionTable(name = "company_eligible_courses", joinColumns = @JoinColumn(name = "cid"))
//    @Column(name = "course_name")
//    private ArrayList<CourseType> eligibleCourseList = new ArrayList<>();
    public int getCid()
    {
	return cid;
    }

    public void setCid(int cid)
    {
	this.cid = cid;
    }

    public int getMinPackage()
    {
	return minPackage;
    }

    public void setMinPackage(int minPackage)
    {
	this.minPackage = minPackage;
    }

    public int getMaxPackage()
    {
	return maxPackage;
    }

    public void setMaxPackage(int maxPackage)
    {
	this.maxPackage = maxPackage;
    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public String getDescription()
    {
	return description;
    }

    public void setDescription(String description)
    {
	this.description = description;
    }

    public String getVenue()
    {
	return venue;
    }

    public void setVenue(String venue)
    {
	this.venue = venue;
    }

    public Date getLastDate()
    {
	return lastDate;
    }

    public void setLastDate(Date lastDate)
    {
	this.lastDate = lastDate;
    }

    public Date getCreatedOn()
    {
	return createdOn;
    }

    public void setCreatedOn(Date createdOn)
    {
	this.createdOn = createdOn;
    }

    public Date getUpdatedOn()
    {
	return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn)
    {
	this.updatedOn = updatedOn;
    }

    public Date getPlacementDate()
    {
	return placementDate;
    }

    public void setPlacementDate(Date placementDate)
    {
	this.placementDate = placementDate;
    }

//    public ArrayList<CourseType> getEligibleCourseList()
//    {
//	return eligibleCourseList;
//    }
//
//    public void setEligibleCourseList(ArrayList<CourseType> eligibleCourseList)
//    {
//	this.eligibleCourseList = eligibleCourseList;
//    }
}
