/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import department.Department;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Dhaval
 */
@Entity
@Table(name = "student_master")
@Inheritance(strategy = InheritanceType.JOINED)
public class Student implements Serializable
{

    public enum CourseType
    {
	BTech,
	MTech
    }

    public enum Gender
    {
	MALE, FEMALE
    }

    @Id
    private String username;

    @Column(name = "roll_no")
    private String rollNo;

    @Column(name = "admission_type")
    private String admissionType;

    @Column(name = "birth_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String name;
    private String email;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private int pincode;
    private long mobileNo1;
    private long mobileNo2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Department department;

    @Column(name = "created_on", updatable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created_on = new Date();

    @JoinTable(name = "fields_of_interest", joinColumns =
    {
	@JoinColumn(name = "username")
    })
    @Column(name = "name")
    @ElementCollection
    private List<String> fieldOfInterest = new ArrayList<>();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "student_type")
    private CourseType course;

    public String getUsername()
    {
	return username;
    }

    public void setUsername(String username)
    {
	this.username = username;
    }

    public String getRollNo()
    {
	return rollNo;
    }

    public void setRollNo(String rollNo)
    {
	this.rollNo = rollNo;
    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public String getAdmissionType()
    {
	return admissionType;
    }

    public void setAdmissionType(String admissionType)
    {
	this.admissionType = admissionType;
    }

    public Date getDob()
    {
	return dob;
    }

    public void setDob(Date dob)
    {
	this.dob = dob;
    }

    public String getEmail()
    {
	return email;
    }

    public void setEmail(String email)
    {
	this.email = email;
    }

    public String getAddress1()
    {
	return address1;
    }

    public void setAddress1(String address1)
    {
	this.address1 = address1;
    }

    public String getAddress2()
    {
	return address2;
    }

    public void setAddress2(String address2)
    {
	this.address2 = address2;
    }

    public String getAddress3()
    {
	return address3;
    }

    public void setAddress3(String address3)
    {
	this.address3 = address3;
    }

    public String getCity()
    {
	return city;
    }

    public void setCity(String city)
    {
	this.city = city;
    }

    public int getPincode()
    {
	return pincode;
    }

    public void setPincode(int pincode)
    {
	this.pincode = pincode;
    }

    public List<String> getFieldOfInterest()
    {
	return fieldOfInterest;
    }

    public void setFieldOfInterest(List<String> fieldOfInterest)
    {
	this.fieldOfInterest = fieldOfInterest;
    }

    public Date getCreated_on()
    {
	return created_on;
    }

    public void setCreated_on(Date created_on)
    {
	this.created_on = created_on;
    }

    public long getMobileNo1()
    {
	return mobileNo1;
    }

    public void setMobileNo1(long mobileNo1)
    {
	this.mobileNo1 = mobileNo1;
    }

    public long getMobileNo2()
    {
	return mobileNo2;
    }

    public void setMobileNo2(long mobileNo2)
    {
	this.mobileNo2 = mobileNo2;
    }

    public Gender getGender()
    {
	return gender;
    }

    public void setGender(Gender gender)
    {
	this.gender = gender;
    }

    public Department getDepartment()
    {
	return department;
    }

    public void setDepartment(Department department)
    {
	this.department = department;
    }

    public String printFieldsofInterest()
    {
	StringBuilder sb = new StringBuilder();

	for (String fieldName : getFieldOfInterest())
	{
	    sb.append(fieldName);
	    sb.append(",");
	}

	sb.deleteCharAt(sb.length() - 1);

	return sb.toString();
    }

    public CourseType getCourse()
    {
	return course;
    }

    public void setCourse(CourseType course)
    {
	this.course = course;
    }
}
