/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package academic_info;

import java.io.Serializable;
import java.lang.reflect.Field;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Dhaval
 */
@Entity
@Table(name = "btech_student_academic_info")
public class BTech_Student_Academic_Info implements Serializable
{

    @Id
    private String username;

    private String ssc_board;
    private double ssc_percentage;

    @Column(name = "is_d2d")
    private boolean d2d;

    private double diploma_cpi;

    private String hsc_board;
    private double hsc_percentage;

    private double btech_sem1_spi;
    private double btech_sem2_spi;
    private double btech_sem3_spi;
    private double btech_sem4_spi;
    private double btech_sem5_spi;
    private double btech_sem6_spi;
    private double btech_sem7_spi;
    private double btech_sem8_spi;

    private double btech_cpi;

    public String getUsername()
    {
	return username;
    }

    public void setUsername(String username)
    {
	this.username = username;
    }

    public String getSsc_board()
    {
	return ssc_board;
    }

    public void setSsc_board(String ssc_board)
    {
	this.ssc_board = ssc_board;
    }

    public double getSsc_percentage()
    {
	return ssc_percentage;
    }

    public void setSsc_percentage(double ssc_percentage)
    {
	this.ssc_percentage = ssc_percentage;
    }

    public String getHsc_board()
    {
	return hsc_board;
    }

    public void setHsc_board(String hsc_board)
    {
	this.hsc_board = hsc_board;
    }

    public double getHsc_percentage()
    {
	return hsc_percentage;
    }

    public void setHsc_percentage(double hsc_percentage)
    {
	this.hsc_percentage = hsc_percentage;
    }

    public double getBtech_sem1_spi()
    {
	return btech_sem1_spi;
    }

    public void setBtech_sem1_spi(double btech_sem1_spi)
    {
	this.btech_sem1_spi = btech_sem1_spi;
    }

    public double getBtech_sem2_spi()
    {
	return btech_sem2_spi;
    }

    public void setBtech_sem2_spi(double btech_sem2_spi)
    {
	this.btech_sem2_spi = btech_sem2_spi;
    }

    public double getBtech_sem3_spi()
    {
	return btech_sem3_spi;
    }

    public void setBtech_sem3_spi(double btech_sem3_spi)
    {
	this.btech_sem3_spi = btech_sem3_spi;
    }

    public double getBtech_sem4_spi()
    {
	return btech_sem4_spi;
    }

    public void setBtech_sem4_spi(double btech_sem4_spi)
    {
	this.btech_sem4_spi = btech_sem4_spi;
    }

    public double getBtech_sem5_spi()
    {
	return btech_sem5_spi;
    }

    public void setBtech_sem5_spi(double btech_sem5_spi)
    {
	this.btech_sem5_spi = btech_sem5_spi;
    }

    public double getBtech_sem6_spi()
    {
	return btech_sem6_spi;
    }

    public void setBtech_sem6_spi(double btech_sem6_spi)
    {
	this.btech_sem6_spi = btech_sem6_spi;
    }

    public double getBtech_sem7_spi()
    {
	return btech_sem7_spi;
    }

    public void setBtech_sem7_spi(double btech_sem7_spi)
    {
	this.btech_sem7_spi = btech_sem7_spi;
    }

    public double getBtech_sem8_spi()
    {
	return btech_sem8_spi;
    }

    public void setBtech_sem8_spi(double btech_sem8_spi)
    {
	this.btech_sem8_spi = btech_sem8_spi;
    }

    public boolean isD2d()
    {
	return d2d;
    }

    public void setD2d(boolean d2d)
    {
	this.d2d = d2d;
    }

    public double getDiploma_cpi()
    {
	return diploma_cpi;
    }

    public void setDiploma_cpi(double diploma_cpi)
    {
	this.diploma_cpi = diploma_cpi;
    }

    public double getBtech_cpi()
    {
	int count = 0;
	double total = 0;

	for (int i = 1; i <= 8; i++)
	{
	    try
	    {
		Field field = BTech_Student_Academic_Info.class.getDeclaredField("btech_sem" + i + "_spi");
		double spi = field.getDouble(this);

		if (spi == 0)
		    break;
		else
		{
		    count++;
		    total += spi;
		}
	    }
	    catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex)
	    {
		ex.printStackTrace();
	    }
	}

	btech_cpi = total / count;

	return btech_cpi;
    }

    public void setBtech_cpi(double btech_cpi)
    {
	this.btech_cpi = btech_cpi;
    }

}
