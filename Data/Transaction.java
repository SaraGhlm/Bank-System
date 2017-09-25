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
public class Transaction 
{
    private Date date ;
    private TransactionType type ;
    private float amount ;
    
    public Transaction (TransactionType type ,float amount )
    {
        date = new Date () ;
        this.type = type ;
        this.amount = amount ;
    }
    
    public Date getDate ()
    {
        return date ;
    }
    
    public TransactionType getType ()
    {
        return type ;
    }
    
    public float getAmount ()
    {
        return amount ;
    }
}
