/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author Dhaval
 */
@Entity
@Table(name = "user_master")
public class User implements Serializable
{

    @Id
    private String username;

    @Lob
    private byte[] password;

    private boolean student = false;

    private boolean administrator = false;

    private boolean recruiter = false;

    public String getUsername()
    {
	return username;
    }

    public void setUsername(String username)
    {
	this.username = username;
    }

    public boolean isStudent()
    {
	return student;
    }

    public void setStudent(boolean student)
    {
	this.student = student;
    }

    public boolean isAdministrator()
    {
	return administrator;
    }

    public void setAdministrator(boolean administrator)
    {
	this.administrator = administrator;
    }

    public boolean isRecruiter()
    {
	return recruiter;
    }

    public void setRecruiter(boolean recruiter)
    {
	this.recruiter = recruiter;
    }

    public byte[] getPassword()
    {
	return password;
    }

    public void setPassword(byte[] password)
    {
	this.password = password;
    }
}
