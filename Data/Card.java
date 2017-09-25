/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javabankproject.Data;

/**
 *
 * @author Novin Pendar
 */
public class Card 
{
    private long number ;
    private Account account ;
    private int password ;
    private ExpirationDate date ;
    
    public Card ( long number , Account account , int password )
    {
        this.number = number ;
        this.account = account ;
        this.password = password ;
        date = new ExpirationDate () ;
    }
    
    public long getNumber ()
    {
        return number ;
    }
    
    public Account getAccount ()
    {
        return account ;
    }
    
    public int getPassword ()
    {
        return password ;
    }
    
    public ExpirationDate getDate ()
    {
        return date ;
    }
}
