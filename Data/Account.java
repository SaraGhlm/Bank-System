/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javabankproject.Data;

import java.util.Arrays;

/**
 *
 * @author Novin Pendar
 */
public class Account 
{
    private long code ;
    private AccountType type ;
    private Branch branch ;
    private User owner ;
    private float balance ;
    private int transLength = 1 ;
    private Transaction transaction [] = new Transaction[transLength+1] ;
    
    /**
     * 
     * @param code
     * @param type
     * @param branch
     * @param owner
     * @param balance 
     */
    public Account (long code, AccountType type, Branch branch, User owner)
    {
        this.code = code ;
        this.type = type ;
        this.branch = branch ;
        this.owner = owner ;
        balance = 0 ;
    }
    
    public long getCode ()
    {
        return code ;
    }
    
    public AccountType getType ()
    {
        return type ;
    }
    
    public Branch getBranch ()
    {
        return branch ;
    }
    
    public User getOwner ()
    {
        return owner ;
    }
    
    public float getBalance ()
    {
        return balance ;
    }
    
    public void setBalance ( float money )
    {
        balance = money ;
    }
    
    public Transaction [] getTransaction ()
    {
        return transaction ;
    }
    
    public void addTransaction (TransactionType type, float money)
    {
        Transaction trans = new Transaction(type, money) ;
        
        transaction[transLength-1] = trans ;
        
        Transaction transTemp [] = Arrays.copyOf(transaction, transLength+1);
        transaction = transTemp ;
        
        transLength ++ ;
    }
}
