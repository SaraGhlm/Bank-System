/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javabankproject.Data;

import java.util.Date;

/**
 *
 * @author Novin Pendar
 */
public class ExpirationDate 
{
    private int month ;
    private int year ;
    private Date date ;
    
    public ExpirationDate ()
    {
        date = new Date () ;
        month = date.getMonth() + 1 ;
        year = date.getYear() + 1900 + 2 ;
    }
    
    public int get_month ()
    {
        return month ;
    }
    
    public int get_year ()
    {
        return year ;
    }
}
